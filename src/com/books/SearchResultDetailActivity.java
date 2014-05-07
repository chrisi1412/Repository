package com.books;

//Importieren der Bibliotheken
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultDetailActivity extends Activity {

	//	Variablen deklarieren
	private static final String TAG_AUTHORS = "authors";
	private static final String TAG_TITLE = "title";
	private static final String TAG_DESCRIPTION = "description";
	private Button hinzufuegen;
	private SQLiteDatabase mDatenbank;
	private DatenbankManager mHelper;;
	public String title;
	public String authors;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_detail);

        mHelper = new DatenbankManager(this);
        mDatenbank = mHelper.getReadableDatabase();
        
        //	Zum verbinden der verschiedenen Activitys
        Intent in = getIntent();

        //	JSON Werte aus dem vorherigen Intent ziehen
        authors = in.getStringExtra(TAG_AUTHORS);
        title = in.getStringExtra(TAG_TITLE);
        String description = in.getStringExtra(TAG_DESCRIPTION);

        //	Ausgabe der Werte
        TextView lblAuthor = (TextView) findViewById(R.id.SearchResultDetailActivity_AutorLabel);
        TextView lblTitle = (TextView) findViewById(R.id.SearchResultDetailActivity_TitelLabel);
        TextView lblDescription = (TextView) findViewById(R.id.SearchResultDetailActivity_InhaltLabel);   

        lblAuthor.setText(authors);
        lblTitle.setText(title);
        lblDescription.setText(description);
       
		hinzufuegen = (Button)findViewById(R.id.SearchResultDetailActivity_Hinzufuegen);
		hinzufuegen.setOnClickListener(new View.OnClickListener() { // Button
																	// zum
																	// Hinzufüengen
																	// in die
																	// SQL
																	// Datenbank
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ContentValues werte_titel = new ContentValues();
				ContentValues werte_autor = new ContentValues();
				
				werte_titel.put("titel", title);
				werte_autor.put("autor", authors);
				mDatenbank.insert("klassen",null,werte_titel);
				mDatenbank.insert("klassen",null,werte_autor);
				
				Toast.makeText(
						getApplicationContext(),
						title + "in Favoriten gespeichert",
						Toast.LENGTH_SHORT).show();
				
				Intent goToStart = new Intent (getApplicationContext(), StartActivity.class);
				startActivity(goToStart);
			}

		});
        

    }
	
//	protected void onResume(){
//		super.onResume();
//		mDatenbank = mHelper.getReadableDatabase();
//		Toast.makeText(this, "Datenbank geöffnet", Toast.LENGTH_SHORT).show();
//	}
	
	
}