package com.viralfactor.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.viralfactor.PlayerSettingsManager;
import com.viralfactor.R;

public class GamePreferences extends Activity {
	CheckBox gameSounds;
	CheckBox gameMusic;
	CheckBox vibration;
	PlayerSettingsManager playerSettingsManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferences);
		Button saveBtn = (Button) findViewById(R.id.saveConfigBtn);
		gameSounds = (CheckBox) findViewById(R.id.gameSounds);
		gameMusic = (CheckBox) findViewById(R.id.gameMusic);
		vibration = (CheckBox) findViewById(R.id.vibration);

		playerSettingsManager = new PlayerSettingsManager();
		playerSettingsManager.init(this);

		saveBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// Toast.makeText(this, "New Game touched",
				// Toast.LENGTH_SHORT).show();
				boolean music_value = gameSounds.isChecked();
				boolean sound_value = gameMusic.isChecked();
				boolean vibration_value = vibration.isChecked();
				playerSettingsManager.setPlayerSettings(sound_value,
						music_value, vibration_value);
				Toast.makeText(getApplicationContext(), "Configuration Saved",
						Toast.LENGTH_SHORT).show();

			}
		});

	}

}
