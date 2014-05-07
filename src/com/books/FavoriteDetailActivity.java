package com.books;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class FavoriteDetailActivity extends Activity {

	private TextView titel;
	private TextView autor;
	private TextView isbn;
	private TextView inhalt;
	private ImageView bild;
	

	private SQLiteDatabase mDatenbank;
	private DatenbankManager mHelper;
	
	private String leseTitel;
	private String leseAutor;
	private String leseISBN;
	private Integer leseID;
	private String _id;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_detail);
		mHelper = new DatenbankManager (this);
		
		Intent startFavoriteDetail = getIntent();
		leseTitel = startFavoriteDetail.getExtras().getString("titel");
		leseAutor = startFavoriteDetail.getExtras().getString("autor");
		leseISBN = startFavoriteDetail.getExtras().getString("isbn");
		leseID = startFavoriteDetail.getExtras().getInt("_id");
		
		
		titel=(TextView)findViewById(R.id.FavoriteDetailActivity_Titel);	// TextViews mit Inhalten befüllen
		titel.setText(leseTitel);
		autor=(TextView)findViewById(R.id.FavoriteDetailActivity_Autor);
		autor.setText(leseAutor);
		isbn=(TextView)findViewById(R.id.FavoriteDetailActivity_ISBN);
		isbn.setText(leseISBN);
		//	inhalt;			inhalt muss per get aus der Books api geladen werden
		//	 bild;			bild muss entweder aus SQL oder aus einem Dateiverzeichnnis geladen werden		
		
//		Cursor cursor = mDatenbank.query("klassen", new String[] {"_id", "titel", "autor", "isbn"}, null, null, null, null, null); // müsste unnötig sein!
//		if (cursor != null){
//			if (cursor.moveToPosition(leseID)){
//				int column = cursor.getColumnIndex("_id");
//				leseID = cursor.getString(column);
//			}// endif moveToPosition
//		}//endif != null		

		
		Button loeschen = (Button) findViewById(R.id.FavoriteDetailActivity_Loeschen);
		loeschen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { // Button um Favoriteneintrag aus der
											// Datenbank zu löschen
				// TODO Auto-generated method stub
				// Lösche Daten aus Datenbank und schließe Activity
						_id = (Integer.toString(leseID));
						String where = "_id = ?";
						String[] whereArgs = {_id};
						
						mDatenbank.delete("klassen", where, whereArgs);
						Toast.makeText(getApplicationContext(), "Eintrag aus Favoriten gelöscht", Toast.LENGTH_SHORT).show();
						finish(); 	// hier wird die aktuelle Activity geschlossen

		}
		});

		Button buecherei = (Button) findViewById(R.id.FavoriteDetailActivity_Buecherei);
		buecherei.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { // Butten, um auf die nächste Bücherei
											// Maps Activity zu kommen
				// TODO Auto-generated method stub
				Intent maps = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(maps);

			}
		});
		
		
//		if (savedInstanceState == null) { // if statement wird nicht benötigt
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

	public void onResume(){
		super.onResume();
		mDatenbank = mHelper.getReadableDatabase();
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorite_detail, menu);
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
//	public static class PlaceholderFragment extends Fragment {	// PlaceholderFragment  wird nicht benötigt
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_favorite_detail,
//					container, false);
//			return rootView;
//		}
//	}

}
