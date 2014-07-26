package com.viralfactor;

import android.content.Context;
import android.content.SharedPreferences;

public class PlayerSettingsManager {

	private static final String PREFS_NAME = "GAME_USERDATA";

	private static final String MUSIC_KEY = "musicKey";
	private static final String SOUND_KEY = "soundKey";
	private static final String VIBRATION_KEY = "vibrationKey";

	private SharedPreferences mSettings;
	private SharedPreferences.Editor mEditor;

	// keep track of whether or not sound, vibration, music are enabled
	private boolean mSoundEnabled;
	private boolean mMusicEnabled;
	private boolean mVibrationEnabled;

	public synchronized void init(Context pContext) {
		if (mSettings == null) {

			mSettings = pContext.getSharedPreferences(PREFS_NAME,
					Context.MODE_PRIVATE);
			mEditor = mSettings.edit();
			mMusicEnabled = mSettings.getBoolean(MUSIC_KEY, true);
			mSoundEnabled = mSettings.getBoolean(SOUND_KEY, true);
			mVibrationEnabled = mSettings.getBoolean(VIBRATION_KEY, true);

		}
	}

	/* retrieve the player sound set value */
	public synchronized boolean isSoundEnabled() {
		return mSoundEnabled;
	}

	public synchronized void setSoundEnabled(boolean val) {
		mEditor.putBoolean(SOUND_KEY, val);
		// save the changes to the shared preferences
		mEditor.commit();
	}

	/* retrieve the player music set value */
	public synchronized boolean isMusicEnabled() {
		return mMusicEnabled;
	}

	public synchronized void setMusicEnabled(boolean val) {
		mEditor.putBoolean(MUSIC_KEY, val);
		// save the changes to the shared preferences
		mEditor.commit();
	}

	/* retrieve the player vibration set value */
	public synchronized boolean isVibrationEnabled() {
		return mVibrationEnabled;
	}

	public synchronized void setVibrationEnabled(boolean val) {
		mEditor.putBoolean(VIBRATION_KEY, val);
		// save the changes to the shared preferences
		mEditor.commit();
	}

	public synchronized void setPlayerSettings(boolean sound, boolean music,
			boolean vibration) {
		mEditor.putBoolean(SOUND_KEY, sound);
		mEditor.putBoolean(MUSIC_KEY, music);
		mEditor.putBoolean(VIBRATION_KEY, vibration);
		mEditor.commit();

	}

}
