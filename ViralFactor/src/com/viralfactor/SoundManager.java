package com.viralfactor;

import java.io.IOException;

import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;

public class SoundManager {
	private static Sound mGameTapSound;
	private static Sound mGamePlaySound;
	private static Sound win;
	private static BaseGameActivity activity;
	private static Engine engine;

	// SoundManager INSTANCE;

	public SoundManager(BaseGameActivity activ, Engine eng) {
		SoundManager.activity = activ;
		SoundManager.engine = eng;
		// loadGameSounds();
	}

	public void loadGameSounds() {
		try {
			SoundFactory.setAssetBasePath("sfx/");
			mGameTapSound = SoundFactory.createSoundFromAsset(
					engine.getSoundManager(), activity, "tap.mp3");
			mGamePlaySound = SoundFactory.createSoundFromAsset(
					engine.getSoundManager(), activity, "welcome.mp3");
		} catch (final IOException e) {
			Debug.e(e);
		}

	}

	public void playGameMusic() {
		mGamePlaySound.play();
	}

	public void playGameCollisionMusic() {
		mGameTapSound.play();
	}

	public static float getMusicVolume() {
		return mGamePlaySound.getVolume();
	}

	public static void setMusicVolume(final float pVolume) {
		mGamePlaySound.setVolume(pVolume);
	}

	public static void playWin(final float pRate, final float pVolume) {
		playSound(win, pRate, pVolume);
	}
	private static void playSound(final Sound pSound, final float pRate,
			final float pVolume) {
		pSound.setRate(pRate);
		pSound.setVolume(pVolume);
		pSound.play();
	}
	// public static SoundManager getInstance()
	// {
	// SoundManager instance = new SoundManager(activity);
	// return instance;
	//
	// }

}
