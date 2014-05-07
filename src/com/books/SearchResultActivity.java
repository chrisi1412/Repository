package com.books;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SearchResultActivity extends ListActivity {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	public String url;
	
	// JSON Node names
	private static final String TAG_ITEMS = "items";
	private static final String TAG_VOLUMEINFO = "volumeInfo";
	private static final String TAG_TITLE = "title";
	private static final String TAG_AUTHORS = "authors";
	private static final String TAG_DESCRIPTION = "description";
 	
	
	public String search_phrase;
	
	
	// contacts JSONArray
	JSONArray items = null;
	
	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);

		Intent i = getIntent();
		String searchPhrase = i.getExtras().getString("searchfor");
		
		if(searchPhrase.equals("")){
			TextView tv = (TextView)findViewById(R.id.SearchResultActivity_Suche);
			tv.setText(getString(R.string.SearchResultActivity_emptyValue));
		}else{
		
		TextView tv = (TextView)findViewById(R.id.SearchResultActivity_Suche);
		tv.setText(searchPhrase);	
		
		contactList = new ArrayList<HashMap<String, String>>();	         
		
		ListView lv = getListView();

		
		// URL to get contacts JSON
		String searchurl = "https://www.googleapis.com/books/v1/volumes?q="+ searchPhrase;
		url = searchurl.replaceAll("[ ]", "%20");
		
		
		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String authors = ((TextView) view.findViewById(R.id.SearchResultActivity_Autor))
				.getText().toString();
				String title = ((TextView) view.findViewById(R.id.SearchResultActivity_Titel))
						.getText().toString();
				String description = ((TextView) view.findViewById(R.id.SearchResultActivity_Inhalt))
						.getText().toString();	
				
				// Starting single contact activity
				Intent in = new Intent(getApplicationContext(),
						SearchResultDetailActivity.class);
				in.putExtra(TAG_AUTHORS, authors);
				in.putExtra(TAG_TITLE, title);
				in.putExtra(TAG_DESCRIPTION, description);
				startActivity(in);
			}
		});
	
		// Calling async task to get json
		new GetContacts().execute();
	}
		
		
		
		
	}
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(SearchResultActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			Adapter ad = new Adapter();

			// Making a request to url and getting response
			String jsonStr = ad.makeServiceCall(url, Adapter.GET);
			
			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);
					
					// Getting JSON Array node
					items = jsonObj.getJSONArray(TAG_ITEMS);
					
					// looping through All Contacts
					//for (int i = 0; i < contacts.length(); i++) {		            						            	
					for (int i = 0; i < 5; i++) {	
						JSONObject it = items.getJSONObject(i);										
						
						JSONObject volumeInfo = it.getJSONObject(TAG_VOLUMEINFO);
						String author = volumeInfo.getString("authors").replaceAll("[^a-zA-Z, ]", "");
						String authors = author.replace("," , ", ");			
						String title = volumeInfo.getString(TAG_TITLE);	
						
						String description = "";
						if (volumeInfo.has(TAG_DESCRIPTION) == true) {
							description = volumeInfo.getString(TAG_DESCRIPTION);
						}
						else {
							description = "Leider keine Beschreibung verfuegbar";
						}
						
						String identifier = volumeInfo.getString("industryIdentifiers").replaceAll("[0-9]", "");
												
						// tmp hashmap for single contact
						HashMap<String, String> item = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						item.put(TAG_AUTHORS, authors);
						item.put(TAG_TITLE, title);
						item.put(TAG_DESCRIPTION, description);				

						// adding contact to contact list
						contactList.add(item);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			/*** Updating parsed JSON data into ListView */
			ListAdapter adapter = new SimpleAdapter(
					SearchResultActivity.this, contactList,
					R.layout.activity_search_result_listitem, new String[] { TAG_AUTHORS, TAG_TITLE, TAG_DESCRIPTION }, new int[] { R.id.SearchResultActivity_Autor, R.id.SearchResultActivity_Titel, R.id.SearchResultActivity_Inhalt });

			setListAdapter(adapter);
		}

	}

}