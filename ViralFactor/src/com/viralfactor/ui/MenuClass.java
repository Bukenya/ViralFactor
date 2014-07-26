package com.viralfactor.ui;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.ui.activity.BaseGameActivity;

import com.viralfactor.ResourceManager;
import com.viralfactor.SceneManager;

public class MenuClass extends BaseGameActivity implements
		IOnMenuItemClickListener {

	private static final int CAMERA_WIDTH = 800;
	private static final int CAMERA_HEIGHT = 480;

	protected Camera mCamera;
	public static SceneManager sceneManager;
	public static ResourceManager resourceManager;

	// protected Scene mMainScene;

	@Override
	public EngineOptions onCreateEngineOptions() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
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

		// BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		sceneManager = new SceneManager(this, mEngine, mCamera);

		// sceneManager.loadSplashResources();
		ResourceManager.getInstance().loadGameResources();
		ResourceManager.getInstance().loadMenuResources();

		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		pOnCreateSceneCallback.onCreateSceneFinished(sceneManager
				.createMenuScene());

	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {

		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene,
			final IMenuItem pMenuItem, final float pMenuItemLocalX,
			final float pMenuItemLocalY) {
		// switch (pMenuItem.getID()) {
		// case MENU_RESET:
		// /* Restart the animation. */
		// this.mMenuScene.reset();
		//
		// /* Remove the menu and reset it. */
		// this.mMenuScene.clearChildScene();
		// this.mMenuScene.reset();
		// return true;
		// case MENU_QUIT:
		// /* End Activity. */
		// this.finish();
		// return true;
		// default:
		// return false;
		// }
		return false;
	}

	@Override
	public synchronized void onResumeGame() {
		if (ResourceManager.gameMusic != null
				&& !ResourceManager.gameMusic.isPlaying()) {
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
				&& ResourceManager.gameMusic.isPlaying()) {
			ResourceManager.gameMusic.pause();
		}
		super.onPauseGame();
	}

}