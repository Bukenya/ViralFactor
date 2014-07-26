package com.viralfactor.safe.sprite;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.viralfactor.GameManager;
import com.viralfactor.ui.NegativeCanvas;

public class CondomSprite extends Sprite {
	
	public CondomSprite(float pX, float pY, ITextureRegion pTextureRegion,
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
			//this.setX(pSceneTouchEvent.getX() - this.getWidth() / 2);
			//this.setY(pSceneTouchEvent.getY() - this.getHeight() / 2);
			NegativeCanvas.sceneManager.playCollisionSound();
			this.setScale(1.25f);
			break;
		case TouchEvent.ACTION_UP:
			this.setScale(1.0f);
			GameManager.getInstance().incrementScore(5);
			NegativeCanvas.sceneManager.updateSceneScores((int) this.getX(),
					(int) this.getY(),NegativeCanvas.sceneManager.safeCanvas);
//			GameManager.getInstance().decreasePlayerLife(1);
//			NegativeCanvas.sceneManager.updateSceneScores((int) this.getX(),
//					(int) this.getY());
			
			// should be called when a tablet has been tapped
			
			setVisible(false);
			//detachSelf();
			this.setIgnoreUpdate(true);
			NegativeCanvas.sceneManager.detachSprite(this);
			NegativeCanvas.sceneManager.stopCollisionSound();

			break;
		}
		return true;
	}
	

}
