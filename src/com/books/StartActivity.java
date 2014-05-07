package com.books;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;


public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		Button Suche = (Button) findViewById(R.id.StartActivity_Suche); // Button
																		// für
																		// Suche,
																		// um
																		// auf
																		// die
																		// Suchmaske
																		// (SearchActivity)
																		// zu
																		// kommen
		Suche.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent suche = new Intent(getApplicationContext(),
						SearchActivity.class);
				startActivity(suche);
			}
		});

		Button Buecherei = (Button) findViewById(R.id.StartActivity_Buecherei); // Button
																				// für
																				// nächste
																				// Bücherei,
																				// um
																				// auf
																				// LibraryMapActivity
																				// zu
																				// kommen
		Buecherei.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent buecherei = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(buecherei);

			}
		});

		Button Favoriten = (Button) findViewById(R.id.StartActivity_Favoriten); // Button
																				// für
																				// die
																				// Favoriten,
																				// um
																				// auf
																				// Favoritenansicht
																				// (FavoriteActivity)
																				// zu
																				// kommen
		Favoriten.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent favoriten = new Intent(getApplicationContext(),
						FavoriteActivity.class);
				startActivity(favoriten);
			}
		});
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.menu, menu);
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
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_start,
//					container, false);
//			return rootView;
//		}
//	}

}
