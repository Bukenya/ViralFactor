package com.viralfactor;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Vibrator;

import com.viralfactor.gamedata.ScoreManager;

public class ResourceManager {

	public static BaseGameActivity activity;
	public static Engine engine;
	public static Camera camera;
	public Context context;
	public static ResourceManager INSTANCE;
	// public SoundManager sound;

	public static Scene splashScene;
	public static Scene gameScene;
	public static Scene menuScene;
	public static Scene safeCanvas;
	public static Scene gameover;
	public static Scene winScene;
	public static Font mFont;
	public static final int FONT_SIZE = 28;
	public static final int BTN_SPACE = 60;
	public static Text scoreText;
	public static Text livesText;
	public static Text gameTimeText;

	public static BitmapTextureAtlas tabletTextureAtlas;
	public static BitmapTextureAtlas mBackgroundTexture;
	public static BitmapTextureAtlas splashTA;
	public static BitmapTextureAtlas condomTextureAtlas;
	public static BitmapTextureAtlas chickenTextureAtlas;
	public static BitmapTextureAtlas waterTextureAtlas;
	public static BitmapTextureAtlas garlicTextureAtlas;
	public static BitmapTextureAtlas pineappleTextureAtlas;
	public static BitmapTextureAtlas alcoholTextureAtlas;
	public static BitmapTextureAtlas winTextureAtlas;
	public static BitmapTextureAtlas lossTextureAtlas;
	public static BitmapTextureAtlas cigarreteTextureAtlas;
	public static BitmapTextureAtlas razorTextureAtlas;
	public static BitmapTextureAtlas blueTextureAtlas;
	public static BitmapTextureAtlas redTextureAtlas;
	public static BitmapTextureAtlas pauseTextureAtlas;
	public static BitmapTextureAtlas soyaTextureAtlas;
	public static BitmapTextureAtlas bonusTextureAtlas;

	// Texture regions for both the safe and positive living canvases
	public static ITextureRegion alcoholTextureRegion;
	public static ITextureRegion waterTextureRegion;
	public static ITextureRegion pineappleTextureRegion;
	public static ITextureRegion garlicTextureRegion;
	public static ITextureRegion winTextureRegion;
	public static ITextureRegion lossTextureRegion;
	public static ITextureRegion condomTextureRegion;
	public static ITextureRegion chickenTextureRegion;
	public static ITextureRegion tabletTextureRegion;
	public static TextureRegion mBgTexture;
	public static ITextureRegion splashTR;
	public static TextureRegion razorTextureRegion;
	public static TextureRegion redTextureRegion;
	public static ITextureRegion blueTextureRegion;
	public static ITextureRegion cigarreteTextureRegion;
	public static ITextureRegion pauseTextureRegion;
	public static ITextureRegion soyaTextureRegion;
	public static ITextureRegion bonusTextureRegion;

	// load menu textures
	public static BitmapTextureAtlas playBitmapTextureAtlas;
	public static BitmapTextureAtlas optionsBitmapTextureAtlas;
	public static BitmapTextureAtlas scoresBitmapTextureAtlas;
	public static BitmapTextureAtlas exitBitmapTextureAtlas;
	public static BitmapTextureAtlas mMenuBgTextureAtlas;
	public static BitmapTextureAtlas instructionsTextureAtlas;

	public static MenuScene mMenuScene;

	public static ITextureRegion scoresTextureRegion;
	// public ITextureRegion mFaceTextureRegion;
	protected static ITextureRegion optionsTextureRegion;
	protected static ITextureRegion quitTextureRegion;
	protected static ITextureRegion newGameTextureRegion;
	protected static ITextureRegion instructionsTextureRegion;

	// variables for the game sounds and music
	public static Sound mGameTapSound;
	public static Music gameMusic;
	public static Sound badFoodSound;
	public Vibrator v;
	public ScoreManager datasource;

	public ResourceManager(BaseGameActivity act, Engine eng, Camera cam) {
		activity = act;
		engine = eng;
		camera = cam;
	}

	public void loadSplashResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTA = new BitmapTextureAtlas(activity.getTextureManager(), 256,
				256);

		splashTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				splashTA, activity, "splash12.png", 0, 0);

		splashTA.load();
	}

	public void loadGameResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		SoundFactory.setAssetBasePath("sfx/");
		MusicFactory.setAssetBasePath("sfx/");
		condomTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);
		chickenTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);
		tabletTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);
		waterTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);
		garlicTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 32, 32);
		pineappleTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);
		alcoholTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 32, 64);
		blueTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 256);
		redTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				256, 256);
		razorTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256);
		winTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				1024, 512);
		lossTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				1024, 512);
		pauseTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);
		cigarreteTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);
		soyaTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(),
				32, 16);
		bonusTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 64, 64);

		// create the texture regions of the different Sprites
		condomTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(condomTextureAtlas, activity, "condom2f.png",
						0, 0);
		chickenTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(chickenTextureAtlas, activity,
						"chichen_final.png", 0, 0);
		tabletTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(tabletTextureAtlas, activity, "tab.png", 0, 0);
		garlicTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(garlicTextureAtlas, activity, "garlicf.png",
						0, 0);
		pineappleTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(pineappleTextureAtlas, activity, "pinef.png",
						0, 0);
		waterTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(waterTextureAtlas, activity, "water.png", 0, 0);
		alcoholTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(alcoholTextureAtlas, activity, "waragi.png",
						0, 0);
		blueTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(blueTextureAtlas, activity, "blue3.png", 0, 0);
		redTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(redTextureAtlas, activity, "red2.png", 0, 0);
		razorTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(razorTextureAtlas, activity, "razor.png", 0, 0);
		winTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(winTextureAtlas, activity, "win.png", 0, 0);
		lossTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(lossTextureAtlas, activity, "loss.png", 0, 0);
		cigarreteTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(cigarreteTextureAtlas, activity,
						"cigaret5.png", 0, 0);
		soyaTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(soyaTextureAtlas, activity, "sayaf.png", 0, 0);
		bonusTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(bonusTextureAtlas, activity, "health.png", 0,
						0);
		pauseTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(pauseTextureAtlas, activity, "pause.png", 0, 0);

		mBackgroundTexture = new BitmapTextureAtlas(engine.getTextureManager(),
				(int) camera.getWidth(), (int) camera.getHeight(),
				TextureOptions.DEFAULT);
		mBgTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mBackgroundTexture, activity, "game_background.jpg", 0, 0);

		// scoreFontTexture = new BitmapTextureAtlas(engine.getTextureManager(),
		// 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		// FontFactory.setAssetBasePath("font/");
		// ResourceManager.mFont = FontFactory.createFromAsset(scoreFontTexture,
		// engine.getTextureManager(),
		// "Droid.ttf", FONT_SIZE, true, Color.BLACK);
		ResourceManager.mFont = FontFactory.create(engine.getFontManager(),
				engine.getTextureManager(), 256, 256,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), FONT_SIZE);

		// Load our "sound.mp3" file into a Sound object
		try {
			mGameTapSound = SoundFactory.createSoundFromAsset(
					engine.getSoundManager(), activity, "tap.mp3");
			badFoodSound = SoundFactory.createSoundFromAsset(
					engine.getSoundManager(), activity, "plast.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Load our "music.mp3" file into a music object
		try {
			gameMusic = MusicFactory.createMusicFromAsset(
					engine.getMusicManager(), activity, "welcome.mp3");
		} catch (IOException e) {
			e.printStackTrace();
		}

		ResourceManager.mFont.load();
		ResourceManager.mBackgroundTexture.load();
		condomTextureAtlas.load();
		chickenTextureAtlas.load();
		tabletTextureAtlas.load();
		pineappleTextureAtlas.load();
		waterTextureAtlas.load();
		alcoholTextureAtlas.load();
		garlicTextureAtlas.load();
		blueTextureAtlas.load();
		redTextureAtlas.load();
		razorTextureAtlas.load();
		winTextureAtlas.load();
		lossTextureAtlas.load();
		pauseTextureAtlas.load();
		cigarreteTextureAtlas.load();
		soyaTextureAtlas.load();
		bonusTextureAtlas.load();

	}

	public void loadMenuResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		ResourceManager.playBitmapTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		ResourceManager.optionsBitmapTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		ResourceManager.scoresBitmapTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		ResourceManager.instructionsTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		ResourceManager.exitBitmapTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);

		// load the button texture regions
		mMenuBgTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), (int) camera.getWidth(),
				(int) camera.getHeight(), TextureOptions.DEFAULT);

		mBgTexture = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				mMenuBgTextureAtlas, activity, "image.jpg", 0, 0);
		ResourceManager.newGameTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(ResourceManager.playBitmapTextureAtlas,
						activity, "new_game.PNG", 0, 0);
		ResourceManager.optionsTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(ResourceManager.optionsBitmapTextureAtlas,
						activity, "settings1.png", 0, 0);
		ResourceManager.quitTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(ResourceManager.exitBitmapTextureAtlas,
						activity, "exit.PNG", 0, 50);
		ResourceManager.scoresTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(ResourceManager.scoresBitmapTextureAtlas,
						activity, "menu_reset.png", 0, 0);
		ResourceManager.instructionsTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(ResourceManager.instructionsTextureAtlas,
						activity, "instructions.PNG", 0, 0);

		ResourceManager.optionsBitmapTextureAtlas.load();
		ResourceManager.playBitmapTextureAtlas.load();
		ResourceManager.scoresBitmapTextureAtlas.load();
		ResourceManager.instructionsTextureAtlas.load();
		ResourceManager.exitBitmapTextureAtlas.load();
		ResourceManager.mMenuBgTextureAtlas.load();
	}

	/*
	 * ResourceManager default constructor
	 */
	public ResourceManager() {
	}

	/*
	 * public method that uses a singleton pattern to return the instance of
	 * this current class
	 */
	public static ResourceManager getInstance() {
		INSTANCE = new ResourceManager();
		return INSTANCE;
	}

	public Camera getCamera() {
		return camera;
	}

	public Engine getEngine() {
		return engine;
	}
	public static Context getContext() {
		return getInstance().context;
	}
}
