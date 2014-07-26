package com.viralfactor.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.viralfactor.R;

public class Instructions extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instructions);
		// Typeface face = Typeface.createFromAsset(getAssets(),
		// "font/earth aircraft universe.ttf");
		// TextView inst = (TextView) findViewById(R.id.howto);
		// inst.setTypeface(face);

		TextView instTitle = (TextView) findViewById(R.id.instructionTitle);
		TextView instHowToPlay = (TextView) findViewById(R.id.howtotext);
		TextView progressTitle = (TextView) findViewById(R.id.gameProgressTitleText);
		TextView progress = (TextView) findViewById(R.id.howtoprogress);

		String ins_title = "GAME INSTRUCTIONS";
		// set the title of the game instructions
		instTitle.setText(ins_title);

		String howtoplay = "How to Play\nTap the foods as they are medically recommended."
				+ "\nWhen you tap a dangerous food, then your life will reduce by 1"
				+ "\nKeep taking the right foods to earn your self points and "
				+ "to obtain bonus lives";
		instHowToPlay.setText(howtoplay);

		String progressTitleText = "GAME PROGRESSION";
		progressTitle.setText(progressTitleText);

		String progressText = "Obtain a score of 100 to progress to the next level";
		progress.setText(progressText);

	}
}
