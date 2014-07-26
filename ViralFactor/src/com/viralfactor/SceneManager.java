package com.viralfactor;

import java.util.ArrayList;
import java.util.Random;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.modifier.IModifier;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import com.viralfactor.gamedata.Score;
import com.viralfactor.gamedata.ScoreManager;
import com.viralfactor.positive.sprite.AlcoholSprite;
import com.viralfactor.positive.sprite.BonusSprite;
import com.viralfactor.positive.sprite.CigarreteSprite;
import com.viralfactor.positive.sprite.GarlicSprite;
import com.viralfactor.positive.sprite.PineappleSprite;
import com.viralfactor.positive.sprite.SoyaSprite;
import com.viralfactor.positive.sprite.TabletSprite;
import com.viralfactor.positive.sprite.WaterSprite;
import com.viralfactor.safe.sprite.AlcoholSpriteSafe;
import com.viralfactor.safe.sprite.BlueLadySprite;
import com.viralfactor.safe.sprite.ChickenSpriteSafe;
import com.viralfactor.safe.sprite.CondomSprite;
import com.viralfactor.safe.sprite.RazorBladeSprite;
import com.viralfactor.safe.sprite.RedLadySprite;
import com.viralfactor.safe.sprite.WaterSpriteS;
import com.viralfactor.ui.GamePreferences;
import com.viralfactor.ui.HighScoreView;
import com.viralfactor.ui.Instructions;

/**
 * Created by polly on 4/21/14.
 */
public class SceneManager {
	private AllScenes currentScene;
	private static BaseGameActivity activity;
	private Engine engine;
	private Camera camera;
	// private SoundManager sound;

	private Scene splashScene;
	public Scene gameScene;
	public Scene menuScene;
	public Scene safeCanvas;
	public Scene gameover;
	public Scene winScene;
	// private static final int FONT_SIZE = 28;
	private static final int BTN_SPACE = 60;
	public Text scoreText;
	public Text livesText;
	public Text gameTimeText;

	protected MenuScene mMenuScene;

	// variables for the game sounds and music
	// public Sound mGameTapSound;
	// public Music gameMusic;
	// public Sound badFoodSound;
	private Vibrator v;
	public ScoreManager datasource;

	public enum AllScenes {
		SPLASH, MENU, GAME, GAMEOVER, SAFE
	}

	public SceneManager(BaseGameActivity act, Engine eng, Camera cam) {
		activity = act;
		this.engine = eng;
		this.camera = cam;
	}

	public Scene createSplashScene() {
		splashScene = new Scene();
		splashScene.setBackground(new Background(1, 1, 1));
		Sprite icon = new Sprite(0, 0, ResourceManager.splashTR,
				ResourceManager.getInstance().getEngine()
						.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				// to place code for fading the sprite
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setScale(2.0f);
					break;
				case TouchEvent.ACTION_UP:
					this.setScale(1.0f);
					break;
				case TouchEvent.ACTION_MOVE:
					this.setPosition(pSceneTouchEvent.getX() - this.getWidth()
							/ 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
					break;
				}
				return true;
			}

		};
		icon.setPosition(
				(ResourceManager.getInstance().getCamera().getWidth() - icon
						.getWidth()) / 2, (ResourceManager.getInstance()
						.getCamera().getHeight() - icon.getHeight()) / 2);
		splashScene.registerTouchArea(icon);

		// register the on touch event on the canvas
		splashScene.setTouchAreaBindingOnActionDownEnabled(true);
		splashScene.attachChild(icon);
		return splashScene;
	}

	public Scene createGameScene() {
		// sound.playGameMusic();
		gameScene = new Scene();

		gameScene.setBackground(backGroundSprite());

		scoreText = new Text(650, 10, ResourceManager.mFont, "Score: "
				+ GameManager.getInstance().getCurrentScore(), new TextOptions(
				HorizontalAlign.RIGHT), ResourceManager.getInstance()
				.getEngine().getVertexBufferObjectManager());
		livesText = new Text(50, 10, ResourceManager.mFont, "Lives: "
				+ GameManager.getInstance().getLivesNumber(), new TextOptions(
				HorizontalAlign.RIGHT), ResourceManager.getInstance()
				.getEngine().getVertexBufferObjectManager());

		gameTimeText = new Text(ResourceManager.getInstance().getCamera()
				.getWidth() / 2, 10, ResourceManager.mFont, "60s",
				new TextOptions(HorizontalAlign.RIGHT), ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		// Sprite pause = new Sprite(
		// (camera.getWidth() - pauseTextureRegion.getWidth()) - 10,
		// (camera.getHeight() - pauseTextureRegion.getHeight()) - 10,
		// pauseTextureRegion, engine.getVertexBufferObjectManager());

		ButtonSprite pause = new ButtonSprite(ResourceManager.getInstance()
				.getCamera().getWidth()
				- ResourceManager.pauseTextureRegion.getWidth() - 10,
				ResourceManager.getInstance().getCamera().getHeight()
						- ResourceManager.pauseTextureRegion.getHeight() - 10,
				ResourceManager.pauseTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());

		// gameScene.attachChild(gameTimeText);
		gameScene.attachChild(scoreText);
		gameScene.attachChild(livesText);
		gameScene.attachChild(pause);
		// while (GameManager.getInstance().isGameActive()) {
		gameTimer(gameScene);
		releaseObjects();
		// }
		return gameScene;

	}

	public Scene createMenuScene() {
		this.mMenuScene = new MenuScene(camera);
		this.mMenuScene.setBackground(backGroundSprite());
		mMenuScene.setTouchAreaBindingOnActionDownEnabled(true);

		// this.mMenuScene.addMenuItem(quitMenuItem);
		mMenuScene.setTouchAreaBindingOnActionDownEnabled(true);

		ButtonSprite newGameSprite = new ButtonSprite(200, 100,
				ResourceManager.newGameTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					SceneManager.activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// Toast.makeText(activity.getApplicationContext(),
							// "New Game touched", Toast.LENGTH_SHORT)
							// .show();
							// Intent i = new Intent(activity
							// .getApplicationContext(), GameType.class);
							// activity.startActivity(i);
							mMenuScene.setVisible(false);
							attachChild(createGameScene());
						}
					});
				}
				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		ButtonSprite optionsSprite = new ButtonSprite(
				250 + ResourceManager.scoresTextureRegion.getWidth(), 100
						+ ResourceManager.newGameTextureRegion.getHeight()
						+ BTN_SPACE, ResourceManager.optionsTextureRegion,
				ResourceManager.getInstance().getEngine()
						.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					SceneManager.activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// Toast.makeText(activity.getApplicationContext(),
							// "Settings button touched",
							// Toast.LENGTH_SHORT).show();
							Intent i = new Intent(activity
									.getApplicationContext(),
									GamePreferences.class);
							activity.startActivity(i);
						}
					});
				}
				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		ButtonSprite instructionsSprite = new ButtonSprite(
				250 + ResourceManager.newGameTextureRegion.getWidth(), 100,
				ResourceManager.instructionsTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					SceneManager.activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// Toast.makeText(activity.getApplicationContext(),
							// "Display game instructions",
							// Toast.LENGTH_SHORT).show();
							Intent i = new Intent(activity
									.getApplicationContext(),
									Instructions.class);
							activity.startActivity(i);
						}
					});
				}
				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		ButtonSprite scoresSprite = new ButtonSprite(200, 100
				+ ResourceManager.newGameTextureRegion.getHeight() + BTN_SPACE,
				ResourceManager.scoresTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					SceneManager.activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// Toast.makeText(activity.getApplicationContext(),
							// "Supposed to display highscores",
							// Toast.LENGTH_SHORT).show();
							Intent i = new Intent(activity
									.getApplicationContext(),
									HighScoreView.class);
							activity.startActivity(i);
						}
					});
				}
				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};
		ButtonSprite exitSprite = new ButtonSprite((ResourceManager
				.getInstance().getCamera().getWidth() / 2) - 50, 65
				+ ResourceManager.newGameTextureRegion.getHeight() + BTN_SPACE
				+ 30 + ResourceManager.scoresTextureRegion.getHeight()
				+ ResourceManager.instructionsTextureRegion.getHeight(),
				ResourceManager.quitTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager()) {

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {

				if (pSceneTouchEvent.isActionDown()) {
					SceneManager.activity.runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// Toast.makeText(activity.getApplicationContext(),
							// "Supposed to exit the app",
							// Toast.LENGTH_SHORT).show();
							System.exit(-1);
						}
					});
				}
				/*
				 * In order to allow the ButtonSprite to swap tiled texture
				 * region index on our buttonSprite object, we must return the
				 * super method
				 */
				return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX,
						pTouchAreaLocalY);
			}
		};

		this.mMenuScene.registerTouchArea(optionsSprite);
		this.mMenuScene.registerTouchArea(newGameSprite);

		this.mMenuScene.attachChild(optionsSprite);
		this.mMenuScene.attachChild(newGameSprite);
		this.mMenuScene.registerTouchArea(scoresSprite);
		this.mMenuScene.attachChild(scoresSprite);

		this.mMenuScene.registerTouchArea(instructionsSprite);
		this.mMenuScene.attachChild(instructionsSprite);

		this.mMenuScene.registerTouchArea(exitSprite);
		this.mMenuScene.attachChild(exitSprite);

		this.mMenuScene.buildAnimations();

		// this.mMenuScene.setBackgroundEnabled(false);

		return mMenuScene;
	}

	// create the safe living scene
	public Scene createSafeGameScene() {
		// sound.playGameMusic();
		safeCanvas = new Scene();

		safeCanvas.setBackground(backGroundSprite());

		scoreText = new Text(650, 10, ResourceManager.mFont, "Score: "
				+ GameManager.getInstance().getCurrentScore(), new TextOptions(
				HorizontalAlign.RIGHT), ResourceManager.getInstance()
				.getEngine().getVertexBufferObjectManager());
		livesText = new Text(50, 10, ResourceManager.mFont, "Lives: "
				+ GameManager.getInstance().getLivesNumber(), new TextOptions(
				HorizontalAlign.RIGHT), ResourceManager.getInstance()
				.getEngine().getVertexBufferObjectManager());

		gameTimeText = new Text(ResourceManager.getInstance().getCamera()
				.getWidth() / 2, 10, ResourceManager.mFont, "60s",
				new TextOptions(HorizontalAlign.RIGHT), ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());

		ButtonSprite pause = new ButtonSprite(ResourceManager.getInstance()
				.getCamera().getWidth()
				- ResourceManager.pauseTextureRegion.getWidth() - 10,
				ResourceManager.getInstance().getCamera().getHeight()
						- ResourceManager.pauseTextureRegion.getHeight() - 10,
				ResourceManager.pauseTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		// safeCanvas.attachChild(gameTimeText);
		safeCanvas.attachChild(scoreText);
		safeCanvas.attachChild(livesText);
		safeCanvas.attachChild(pause);

		// while (GameManager.getInstance().isGameActive()) {
		releaseSafe();
		// }
		return safeCanvas;

	}

	private void releaseSafeSprites() {

		Random r = new Random();
		final Sprite s = safeGameSprites().get(
				r.nextInt(safeGameSprites().size() - 1));
		float speed = (new Random().nextFloat() * 10) + 10;
		// float speed = ((float)(new Random().nextInt()));
		// int rnd = (int)(Math.random() * (high – low + 1)) + low;
		MoveModifier m = new MoveModifier(speed, s.getX(), s.getX(), s.getY(),
				-100, new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {

					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						activity.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								s.detachSelf();
							}
						});

						// s.detachSelf();

					}
				});

		safeCanvas.registerTouchArea(s);
		safeCanvas.attachChild(s);
		s.registerEntityModifier(m);
		if ((GameManager.getInstance().getTabletsTaken() % 5 == 0)
				&& (GameManager.getInstance().getTabletsTaken() != 0)) {
			BonusSprite bonus = new BonusSprite(r.nextInt(750), r.nextInt(460),
					ResourceManager.bonusTextureRegion, ResourceManager
							.getInstance().getEngine()
							.getVertexBufferObjectManager());

			safeCanvas.registerTouchArea(bonus);
			safeCanvas.attachChild(bonus);
		}

	}

	public void releaseSafe() {

		TimerHandler myTimer = new TimerHandler(3, true, new ITimerCallback() {
			public void onTimePassed(TimerHandler pTimerHandler) {

				// Utils.log("onUpdate()");
				// gameTimer(gameScene);
				releaseSafeSprites();

			}
		});
		safeCanvas.registerUpdateHandler(myTimer);

	}

	public ArrayList<Sprite> safeGameSprites() {
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		Random r = new Random();
		RazorBladeSprite razorSprite = new RazorBladeSprite(r.nextInt(730),
				500, ResourceManager.razorTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		BlueLadySprite blueLadySprite = new BlueLadySprite(r.nextInt(730), 500,
				ResourceManager.blueTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		RedLadySprite redLadySprite = new RedLadySprite(r.nextInt(730), 500,
				ResourceManager.redTextureRegion, ResourceManager.getInstance()
						.getEngine().getVertexBufferObjectManager());
		ChickenSpriteSafe chickenSprite = new ChickenSpriteSafe(r.nextInt(730),
				500, ResourceManager.chickenTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		AlcoholSpriteSafe alcoholSprite = new AlcoholSpriteSafe(r.nextInt(730),
				500, ResourceManager.alcoholTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		WaterSpriteS waterSprite = new WaterSpriteS(r.nextInt(730), 500,
				ResourceManager.waterTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		// CigarreteSprite ciga = new CigarreteSprite(r.nextInt(730), 500,
		// cigarreteTextureRegion, engine.getVertexBufferObjectManager());
		CondomSprite condom = new CondomSprite(r.nextInt(730), 500,
				ResourceManager.condomTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());

		sprites.add(razorSprite);
		sprites.add(blueLadySprite);
		sprites.add(redLadySprite);
		sprites.add(chickenSprite);
		sprites.add(waterSprite);
		sprites.add(alcoholSprite);
		// sprites.add(ciga);
		sprites.add(condom);
		return sprites;

	}

	public void gameTimer(final Scene scene) {
		scene.registerUpdateHandler(new IUpdateHandler() {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				// float endingTime = GameManager.getInstance().maxGameTime;
				float endingTime = 60f;
				endingTime -= pSecondsElapsed;
				if (endingTime <= 0) {
					// The timer has ended
					gameTimeText.setText("0");
					GameManager.getInstance().setGameActive(false);
					scene.unregisterUpdateHandler(this);
				} else {
					gameTimeText.setText(String.valueOf(Math.round(endingTime)));
				}
			}

			@Override
			public void reset() {
			}
		});
	}

	public void releaseObjects() {
		TimerHandler myTimer = new TimerHandler(3f, true, new ITimerCallback() {
			public void onTimePassed(TimerHandler pTimerHandler) {

				// Utils.log("onUpdate()");
				// gameTimer(gameScene);
				// while (GameManager.getInstance().isGameActive()) {
				release();
				// }
			}
		});
		gameScene.registerUpdateHandler(myTimer);
	}

	private void release() {
		// while (GameManager.getInstance().isGameActive()) {
		Random r = new Random();
		final Sprite s = gameSprites().get(r.nextInt(gameSprites().size() - 1));
		float duration = (new Random().nextFloat() * 10) + 10;
		// float speed = ((float)(new Random().nextInt()));
		// int rnd = (int)(Math.random() * (high – low + 1)) + low;
		MoveModifier m = new MoveModifier(duration, s.getX(), s.getX(),
				s.getY(), -100, new IEntityModifierListener() {

					@Override
					public void onModifierStarted(IModifier<IEntity> pModifier,
							IEntity pItem) {

					}

					@Override
					public void onModifierFinished(
							IModifier<IEntity> pModifier, IEntity pItem) {
						activity.runOnUpdateThread(new Runnable() {
							@Override
							public void run() {
								s.detachSelf();
							}
						});

						// s.detachSelf();

					}
				});

		gameScene.registerTouchArea(s);
		gameScene.attachChild(s);
		s.registerEntityModifier(m);

//		if ((GameManager.getInstance().getTabletsTaken() % 3 == 0)
//				&& (GameManager.getInstance().getTabletsTaken() != 0)) {
//			BonusSprite bonus = new BonusSprite(r.nextInt(750), r.nextInt(460),
//					ResourceManager.bonusTextureRegion, ResourceManager
//							.getInstance().getEngine()
//							.getVertexBufferObjectManager());
//			gameScene.registerTouchArea(bonus);
//			gameScene.attachChild(bonus);
//		}
	}

	public ArrayList<Sprite> gameSprites() {
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		Random r = new Random();
		AlcoholSprite alcoholSprite = new AlcoholSprite(r.nextInt(730), 500,
				ResourceManager.alcoholTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		// CondomSprite condomSprite = new CondomSprite(r.nextInt(730), 500,
		// condomTextureRegion, engine.getVertexBufferObjectManager());
		GarlicSprite garlicSprite = new GarlicSprite(r.nextInt(730), 500,
				ResourceManager.garlicTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		PineappleSprite pineappleSprite = new PineappleSprite(r.nextInt(730),
				500, ResourceManager.pineappleTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		TabletSprite tabletSprite = new TabletSprite(r.nextInt(730), 500,
				ResourceManager.tabletTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		WaterSprite waterSprite = new WaterSprite(r.nextInt(730), 500,
				ResourceManager.waterTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		CigarreteSprite ciga = new CigarreteSprite(r.nextInt(730), 500,
				ResourceManager.cigarreteTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());
		SoyaSprite soya = new SoyaSprite(r.nextInt(730), 500,
				ResourceManager.soyaTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager());

		sprites.add(alcoholSprite);
		// sprites.add(condomSprite);
		sprites.add(tabletSprite);
		sprites.add(garlicSprite);
		sprites.add(waterSprite);
		sprites.add(pineappleSprite);
		sprites.add(ciga);
		sprites.add(soya);
		return sprites;

	}

	public void showGameScreen(Scene scene) {
		scene.attachChild(new Sprite(400, 240,
				ResourceManager.tabletTextureRegion, ResourceManager
						.getInstance().getEngine()
						.getVertexBufferObjectManager()));
	}

	// public Scene createMenuScene() {
	// return menuScene;
	// }

	public AllScenes getCurrentScene() {
		return currentScene;
	}

	public void updatePlayerLives(int x, int y, Scene scene) {
		final Text ref = livesText;
		if (GameManager.getInstance().getLivesNumber() > 0) {
			final Text s = new Text(x, y, ResourceManager.mFont, GameManager
					.getInstance().getLivesNumber() + "", 13, ResourceManager
					.getInstance().getEngine().getVertexBufferObjectManager());
			MoveModifier move = new MoveModifier(1f, x, livesText.getX(), y,
					livesText.getY(), new IEntityModifierListener() {

						@Override
						public void onModifierStarted(
								IModifier<IEntity> pModifier, IEntity pItem) {

						}

						@Override
						public void onModifierFinished(
								IModifier<IEntity> pModifier, IEntity pItem) {
							activity.runOnUpdateThread(new Runnable() {
								@Override
								public void run() {
									s.detachSelf();
								}
							});

							// s.detachSelf();
							ref.setText("Lives: "
									+ GameManager.getInstance()
											.getLivesNumber());
						}
					});
			scene.attachChild(s);
			s.registerEntityModifier(move);
		} else {
			GameManager.getInstance().setGameActive(false);
			datasource = new ScoreManager(activity);
			datasource.addScore(new Score(""
					+ GameManager.getInstance().getCurrentScore()));
			// scene.detachChildren();
			scene.setIgnoreUpdate(true);
			scene.attachChild(makeLossScene());
		}
	}

	public void setCurrentScene(AllScenes currentScene) {
		this.currentScene = currentScene;
		switch (currentScene) {
		case SPLASH:
			engine.setScene(splashScene);
			break;
		case MENU:
			engine.setScene(menuScene);
			break;
		case GAME:
			engine.setScene(gameScene);
			break;
		case GAMEOVER:
			engine.setScene(gameover);
			break;
		case SAFE:
			engine.setScene(safeCanvas);
		default:
			break;
		}
	}

	public Scene makeLossScene() {
		gameover = new Scene();
		gameover.setBackground(backGroundSprite());
		Sprite lossSprite = new Sprite(0, 0, ResourceManager.lossTextureRegion,
				ResourceManager.getInstance().getEngine()
						.getVertexBufferObjectManager());
		lossSprite.setWidth(ResourceManager.getInstance().getCamera()
				.getWidth());
		lossSprite.setHeight(ResourceManager.getInstance().getCamera()
				.getHeight());
		gameover.attachChild(lossSprite);
		return gameover;

	}

	public SpriteBackground backGroundSprite() {
		SpriteBackground bg = new SpriteBackground(new Sprite(0, 0,
				ResourceManager.mBgTexture, ResourceManager.getInstance()
						.getEngine().getVertexBufferObjectManager()));
		return bg;
	}

	public Scene makeWinScene() {
		winScene = new Scene();
		Sprite winSprite = new Sprite(0, 0, ResourceManager.winTextureRegion,
				ResourceManager.getInstance().getEngine()
						.getVertexBufferObjectManager());
		winSprite.attachChild(winSprite);
		return winScene;

	}

	public void updateSceneScores(int x, int y, Scene scene) {
		final Text ref = scoreText;
		if (GameManager.getInstance().getCurrentScore() <= 30) {
			final Text s = new Text(x, y, ResourceManager.mFont, GameManager
					.getInstance().getCurrentScore() + "", 13, ResourceManager
					.getInstance().getEngine().getVertexBufferObjectManager());
			MoveModifier move = new MoveModifier(1f, x, scoreText.getX(), y,
					scoreText.getY(), new IEntityModifierListener() {

						@Override
						public void onModifierStarted(
								IModifier<IEntity> pModifier, IEntity pItem) {

						}

						@Override
						public void onModifierFinished(
								IModifier<IEntity> pModifier, IEntity pItem) {
							activity.runOnUpdateThread(new Runnable() {
								@Override
								public void run() {
									s.detachSelf();
								}
							});

							// s.detachSelf();
							ref.setText("Score: "
									+ GameManager.getInstance()
											.getCurrentScore());
						}
					});
			scene.attachChild(s);
			s.registerEntityModifier(move);
		} else {
			GameManager.getInstance().setGameActive(false);
			datasource = new ScoreManager(activity);
			datasource.addScore(new Score(""
					+ GameManager.getInstance().getCurrentScore()));
			//scene.detachChildren();
			// scene.detachSelf();
			scene.setIgnoreUpdate(true);
			scene.attachChild(makeWinScene());
		}
	}

	public void detachSprite(final Sprite sprite) {
		activity.runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				sprite.detachSelf();
				// sprite.dispose();

			}
		});

	}

	public void playCollisionSound() {
		ResourceManager.mGameTapSound.play();
	}

	public void stopCollisionSound() {
		ResourceManager.mGameTapSound.stop();
	}

	public void playBadFoodSound() {
		ResourceManager.badFoodSound.play();
	}

	public void stopBadFoodSound() {
		ResourceManager.badFoodSound.stop();

	}

	public void startVibrator(int duration) {
		// Get instance of Vibrator from current Context
		v = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 400 milliseconds
		/**
		 * The '-1' here means to vibrate once // '0' would make the pattern
		 * vibrate indefinitely eg v.vibrate(pattern, -1);
		 */
		v.vibrate(duration);
	}

	public void stopVibrator() {
		v.cancel();
	}

	public void isOffscreen(Sprite sprite) {
		if (sprite.getY() > camera.getHeight()) {
			detachSprite(sprite);
		}
	}
}
