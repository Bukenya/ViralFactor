package com.viralfactor;

import java.util.Random;

import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.viralfactor.ui.PositiveCanvas;

public class Tablet extends Sprite {
	SceneManager manager;
	public Tablet(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		//mPhysicsHandler = new PhysicsHandler(this);
		//registerUpdateHandler(mPhysicsHandler);
	}

	//private PhysicsHandler mPhysicsHandler;
	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
			final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		switch (pSceneTouchEvent.getAction()) {
		case TouchEvent.ACTION_DOWN:
			this.setX(pSceneTouchEvent.getX() - this.getWidth() / 2);
			this.setY(pSceneTouchEvent.getY() - this.getHeight() / 2);
			this.setScale(1.25f);
			break;
		case TouchEvent.ACTION_UP:

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
			this.setScale(1.0f);
			GameManager.getInstance().incrementScore(5);
			PositiveCanvas.sceneManager.updateSceneScores((int) this.getX(),
					(int) this.getY(),PositiveCanvas.sceneManager.gameScene);
			
			// should be called when a tablet has been tapped
		
			setVisible(false);
			
			manager.detachSprite(this);
			//detachSelf();
			

			break;
		}
		return true;
	}
	

}
