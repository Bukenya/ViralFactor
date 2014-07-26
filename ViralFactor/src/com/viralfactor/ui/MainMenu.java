package com.viralfactor.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viralfactor.R;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_menu);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		Button newGame = (Button) findViewById(R.id.newGame_btn);
		Button instructions = (Button) findViewById(R.id.instructions_btn);
		Button scores = (Button) findViewById(R.id.highScores_btn);
		Button exit = (Button) findViewById(R.id.exit_btn);
		Button options = (Button) findViewById(R.id.options_btn);

		newGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainMenu.this, GameType.class);
				startActivity(i);
			}
		});

		instructions.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainMenu.this, Instructions.class);
				startActivity(i);
			}
		});
		exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.exit(0);
				// Intent i = new Intent(MainMenu.this, MenuClass.class);
				// startActivity(i);
			}
		});
		scores.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainMenu.this, HighScoreView.class);
				startActivity(i);
			}
		});
		options.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainMenu.this, GamePreferences.class);
				startActivity(i);

			}
		});

	}

}
