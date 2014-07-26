package com.viralfactor.ui;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import com.viralfactor.GameManager;
import com.viralfactor.PlayerSettingsManager;
import com.viralfactor.ResourceManager;
import com.viralfactor.SceneManager;
import com.viralfactor.SoundManager;

public class NegativeCanvas extends BaseGameActivity {
	protected static final int CAMERA_WIDTH = 800;
	protected static final int CAMERA_HEIGHT = 480;
	BitmapTextureAtlas playerTexture;
	ITextureRegion playerTextureRegion;
	PhysicsWorld physicsWorld;
	Sprite sprite;
	Scene scene;
	public static SceneManager sceneManager;
	public static ResourceManager resManager;
	PlayerSettingsManager playerManager;
	Camera mCamera;
	SoundManager sound;

	@Override
	public EngineOptions onCreateEngineOptions() {
		mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions options = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(
						CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
		// options.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		options.getAudioOptions().setNeedsMusic(true);
		options.getAudioOptions().setNeedsSound(true);

		options.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

		return options;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {

		sceneManager = new SceneManager(this, mEngine, mCamera);
		// sceneManager.loadSplashResources();
		resManager = new ResourceManager(this, mEngine, mCamera);
		resManager.loadGameResources();

		GameManager.getInstance().setCurrentActivity(this);
		// soundManager.loadGameSounds();
		// sound = new SoundManager(this, mEngine);
		// sound.loadGameSounds();
		// loadGfx();
		playerManager = new PlayerSettingsManager();
		playerManager.init(this);
		pOnCreateResourcesCallback.onCreateResourcesFinished();

	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// soundManager.playGameMusic();

		// scene = new Scene();
		// scene.setBackground(new Background(Color.GREEN));
		// sound.playGameMusic();
		pOnCreateSceneCallback.onCreateSceneFinished(sceneManager
				.createSafeGameScene());
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {

		pOnPopulateSceneCallback.onPopulateSceneFinished();

	}

	/*
	 * Music objects which loop continuously should be played in onResumeGame()
	 * of the activity life cycle
	 */
	@Override
	public synchronized void onResumeGame() {
		if (ResourceManager.gameMusic != null
				&& !ResourceManager.gameMusic.isPlaying()
				&& playerManager.isMusicEnabled()) {
			ResourceManager.gameMusic.play();
		}
		super.onResumeGame();
	}

	/*
	 * Music objects which loop continuously should be paused in onPauseGame()
	 * of the activity life cycle
	 */
	@Override
	public synchronized void onPauseGame() {
		if (ResourceManager.gameMusic != null
				&& ResourceManager.gameMusic.isPlaying()
				&& playerManager.isMusicEnabled()) {
			ResourceManager.gameMusic.pause();
		}
		super.onPauseGame();
	}


	/*
	 * To be called when the back button is pressed
	 */
	@Override
	public void onBackPressed() {

	}

	/*
	 * method to be called when this activity has been destroyed
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
	}

}
