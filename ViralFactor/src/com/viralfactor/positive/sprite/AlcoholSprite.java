package com.viralfactor.positive.sprite;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.viralfactor.GameManager;
import com.viralfactor.ui.PositiveCanvas;

public class AlcoholSprite extends Sprite {

	public AlcoholSprite(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// mPhysicsHandler = new PhysicsHandler(this);
		// registerUpdateHandler(mPhysicsHandler);
	}

	// private PhysicsHandler mPhysicsHandler;
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN:
			PositiveCanvas.sceneManager.startVibrator(400);
			PositiveCanvas.sceneManager.playBadFoodSound();
			this.setScale(1.25f);
			break;
		case TouchEvent.ACTION_UP:
			this.setScale(1.0f);
			// GameManager.getInstance().incrementScore(5);
			// PositiveCanvas.sceneManager.updateSceneScores((int) this.getX(),
			// (int) this.getY());
			GameManager.getInstance().decreasePlayerLife(1);
			PositiveCanvas.sceneManager.updatePlayerLives(
					(int) PositiveCanvas.sceneManager.livesText.getX(),
					(int) PositiveCanvas.sceneManager.livesText.getY(),
					PositiveCanvas.sceneManager.gameScene);

			// should be called when a tablet has been tapped

			setVisible(false);
			this.setIgnoreUpdate(true);
			PositiveCanvas.sceneManager.detachSprite(this);
			// for the game sprite sounds
			PositiveCanvas.sceneManager.stopBadFoodSound();
			
			//stop the vibrator 
			PositiveCanvas.sceneManager.stopVibrator();
			break;
		}
		return true;
	}

}
