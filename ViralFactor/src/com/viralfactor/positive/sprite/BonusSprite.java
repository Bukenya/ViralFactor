package com.viralfactor.positive.sprite;

import java.util.Random;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.viralfactor.GameManager;
import com.viralfactor.ui.PositiveCanvas;

public class BonusSprite extends Sprite {

	public BonusSprite(float pX, float pY, ITextureRegion pTextureRegion,
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
			PositiveCanvas.sceneManager.playCollisionSound();
			this.setScale(1.25f);
			break;
		case TouchEvent.ACTION_UP:
			this.setScale(1.0f);

			GameManager.getInstance().increasePlayerLife(1);
			PositiveCanvas.sceneManager.updatePlayerLives(
					(int) PositiveCanvas.sceneManager.livesText.getX(),
					(int) PositiveCanvas.sceneManager.livesText.getY(),
					PositiveCanvas.sceneManager.gameScene);

			Random r = new Random();
			int[] xPos = new int[6];
			xPos[0] = -100;
			xPos[1] = -50;
			xPos[2] = (int) (0 - this.getWidth());
			xPos[3] = (int) (800 + this.getWidth());
			xPos[4] = 850;
			xPos[5] = 900;

			int[] yPos = new int[6];
			yPos[0] = -100;
			yPos[1] = -50;
			yPos[2] = (int) (0 - this.getHeight());
			yPos[3] = (int) (480 + this.getHeight());
			yPos[4] = 530;
			yPos[5] = 580;

			int x = xPos[r.nextInt(xPos.length)];
			int y = yPos[r.nextInt(yPos.length)];
			MoveModifier move = new MoveModifier(1f, this.getX(), x,
					this.getY(), y);
			this.registerEntityModifier(move);

			setVisible(false);
			this.setIgnoreUpdate(true);

			PositiveCanvas.sceneManager.detachSprite(this);
			// for the game sprite sounds
			PositiveCanvas.sceneManager.stopCollisionSound();
			break;
		}
		return true;
	}

}
