package com.viralfactor.gamedata;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreManager extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "scores.db";

	// Scores table name
	private static final String TABLE_SCORES = "game_scores";

	// Scores Table Columns names
	private static final String KEY_ID = "id";
	private static final String SCORE_VALUE = "score_value";
	private static final String SCORE_DATE = "score_date";

	public ScoreManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + SCORE_VALUE + " TEXT)";
		db.execSQL(CREATE_SCORES_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);

		// Create tables again
		onCreate(db);
	}

	// Adding new Score
	public void addScore(Score Score) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SCORE_VALUE, Score.getValue()); // Score Name
		// values.put(SCORE_DATE, Score.getScoreDate()); // Score date

		// Inserting Row
		db.insert(TABLE_SCORES, null, values);
		db.close(); // Closing database connection
	}

	// Getting single Score
	public Score getScore(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_SCORES, new String[] { KEY_ID,
				SCORE_VALUE, SCORE_DATE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Score Score = new Score(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		// return Score
		return Score;
	}

	// Getting All Scores
	public List<String> getAllScores() {
		List<String> ScoreList = new ArrayList<String>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SCORES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				String value = cursor.getString(1);
				ScoreList.add(value);
			} while (cursor.moveToNext());
		}

		// return Score list
		return ScoreList;
	}

	// Updating single Score
	public int updateScore(Score Score) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SCORE_VALUE, Score.getValue());
		// values.put(SCORE_DATE, Score.getScoreDate());

		// updating row
		return db.update(TABLE_SCORES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(Score.getID()) });
	}

	// Deleting single Score
	public void deleteScore(Score Score) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SCORES, KEY_ID + " = ?",
				new String[] { String.valueOf(Score.getID()) });
		db.close();
	}

	// Getting Scores Count
	public int getScoresCount() {
		String countQuery = "SELECT  * FROM " + TABLE_SCORES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	public void open() throws SQLException {
		this.getWritableDatabase();
	}
}
