package com.viralfactor.ui;

import java.util.List;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.viralfactor.R;
import com.viralfactor.gamedata.ScoreManager;

public class HighScoreView extends ListActivity {

	private ScoreManager datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		datasource = new ScoreManager(this);
		//datasource.open();
		//datasource.addScore(new Score("98"));
		
		List<String> values = datasource.getAllScores();
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_list_item_1, values);
	        setListAdapter(adapter);

	}

	  @Override
	  protected void onResume() {
	     datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }
}
