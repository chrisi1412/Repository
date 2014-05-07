package com.books;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

class DatenbankManager extends SQLiteOpenHelper {

	private static final String DB_NAME = "database.db";
	private static final int DB_VERSION = 1;
	private static final String KLASSEN_CREATE = "CREATE TABLE klassen ("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "titel TEXT NOT NULL, "
			+ "autor TEXT NOT NULL )";
	private static final String KLASSEN_DROP = "DROP TABLE IF EXITSTS klassen";


	public DatenbankManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(KLASSEN_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(KLASSEN_DROP);
		onCreate(db);
	}

}
