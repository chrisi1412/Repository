package com.books;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class SearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		Button scannerButton = (Button)findViewById( R.id.searchActivity_scanner );
		scannerButton.setOnClickListener( new View.OnClickListener(){
			
			@Override
        	public void onClick(View v){
				Intent i = new Intent(getApplicationContext(), ScannerActivity.class);
				startActivityForResult(i, 0);
			}
		});
		
		Button searchButton = (Button)findViewById( R.id.searchActivity_search );
		searchButton.setOnClickListener( new View.OnClickListener(){
			
			@Override
        	public void onClick(View v){
				EditText search_keyword = (EditText)findViewById(R.id.searchActivity_input_field);
				String search_phrase = search_keyword.getText().toString();
				
				Intent i = new Intent(getApplicationContext(), SearchResultActivity.class);
				i.putExtra("searchfor", search_phrase);
				startActivity(i);
			}
		});

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data){
	    	
	    	super.onActivityResult(requestCode, resultCode, data);
	    	
			String returnValue = data.getExtras().getString("scanresult");
	    	
	    	//TextView tv1 = (TextView)findViewById( R.id.searchActivity_keyword);
	    	//tv1.setText(returnValue);
	    	
			
	    	Intent i = new Intent(getApplicationContext(), SearchResultActivity.class);
	    	
	    	i.putExtra("searchfor", returnValue);
	    	
	    	startActivity(i);
	    }
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_search,
					container, false);
			return rootView;
		}
	}

}
