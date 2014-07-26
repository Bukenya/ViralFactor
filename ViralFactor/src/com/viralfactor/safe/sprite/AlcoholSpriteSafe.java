package com.viralfactor.safe.sprite;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.viralfactor.GameManager;
import com.viralfactor.ui.NegativeCanvas;

public class AlcoholSpriteSafe extends Sprite {

	public AlcoholSpriteSafe(float pX, float pY, ITextureRegion pTextureRegion,
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
			// this.setX(pSceneTouchEvent.getX() - this.getWidth() / 2);
			// this.setY(pSceneTouchEvent.getY() - this.getHeight() / 2);

			NegativeCanvas.sceneManager.playBadFoodSound();

			this.setScale(1.25f);
			break;
		case TouchEvent.ACTION_UP:
			this.setScale(1.0f);
			// GameManager.getInstance().incrementScore(5);
			// PositiveCanvas.sceneManager.updateSceneScores((int) this.getX(),
			// (int) this.getY());
			GameManager.getInstance().decreasePlayerLife(1);
			NegativeCanvas.sceneManager.updatePlayerLives(
					(int) NegativeCanvas.sceneManager.livesText.getX(),
					(int) NegativeCanvas.sceneManager.livesText.getY(),
					NegativeCanvas.sceneManager.safeCanvas);

			// should be called when a tablet has been tapped

			setVisible(false);
			this.setIgnoreUpdate(true);
			NegativeCanvas.sceneManager.detachSprite(this);
			// for the game sprite sounds
			NegativeCanvas.sceneManager.stopBadFoodSound();
			break;
		}
		return true;
	}

}
