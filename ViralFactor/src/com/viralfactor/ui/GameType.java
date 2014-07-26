package com.viralfactor.ui;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.viralfactor.R;

public class GameType extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_type);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		Button positive = (Button)findViewById(R.id.positive_btn);
		Button safe = (Button)findViewById(R.id.safe_btn);
		positive.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(GameType.this, PositiveCanvas.class);
				startActivity(i);
			}
		});
		
		safe.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(GameType.this, NegativeCanvas.class);
				startActivity(i);
			}
		});
		

	}

}
