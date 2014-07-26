package com.viralfactor.positive.sprite;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.viralfactor.GameManager;
import com.viralfactor.SceneManager;
import com.viralfactor.ui.PositiveCanvas;

public class PineappleSprite extends Sprite {
	SceneManager manager;

	public PineappleSprite(float pX, float pY, ITextureRegion pTextureRegion,
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
			PositiveCanvas.sceneManager.playBadFoodSound();
			this.setScale(1.25f);
			break;
		case TouchEvent.ACTION_UP:

			this.setScale(1.0f);
			// GameManager.getInstance().decrementScore(3);
			// PositiveCanvas.sceneManager.updateSceneScores((int) this.getX(),
			// (int) this.getY());

			// should be called when a tablet has been tapped
			GameManager.getInstance().decreasePlayerLife(1);
			PositiveCanvas.sceneManager.updatePlayerLives((int) this.getX(),
					(int) this.getY(),PositiveCanvas.sceneManager.gameScene);
			setVisible(false);
			this.setIgnoreUpdate(true);
			PositiveCanvas.sceneManager.detachSprite(this);
			// detachSelf();
			PositiveCanvas.sceneManager.stopBadFoodSound();
			break;
		}
		return true;
	}

}
