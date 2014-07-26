package com.viralfactor;

import org.andengine.ui.activity.BaseGameActivity;

public class GameManager {
	private static final int INITIAL_SCORE = 0;
	private static final int INITIAL_FOODS_COUNT = 0;
	private static final int INITIAL_LIVES_COUNT = 10;
	private static GameManager INSTANCE;
	private int mCurrentScore = 0;
	private int foodsTakenNumber;
	private int livesNumber = 10;
	private boolean isGameActive = true;
	public float maxGameTime = 60f;
	private int numberOfTabletsTaken = 1;
	private BaseGameActivity activity;

	public float getMaxGameTime() {
		return maxGameTime;
	}

	public void setMaxGameTime(float maxGameTime) {
		this.maxGameTime = maxGameTime;
	}

	// The constructor does not do anything for this singleton
	GameManager() {
	}

	public static GameManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GameManager();
		}
		return INSTANCE;
	}

	// get the current score
	public int getCurrentScore() {
		return this.mCurrentScore;
	}

	// 
	public int getFoodsTakenCount() {
		return this.foodsTakenNumber;
	}

	// increase the current score, most likely when a player takes the right
	// food

	public void incrementScore(int pIncrementBy) {
		mCurrentScore += pIncrementBy;
	}

	// Decrease the score every time a player taps a wrong food sprite
	public void decrementScore(int pDecrementBy) {
		mCurrentScore -= pDecrementBy;
	}

	// Resetting the game simply means we must revert back to initial values.
	public void resetGame() {
		this.mCurrentScore = GameManager.INITIAL_SCORE;
		this.foodsTakenNumber = GameManager.INITIAL_FOODS_COUNT;
		this.livesNumber = GameManager.INITIAL_LIVES_COUNT;
	}

	public int getLivesNumber() {
		return this.livesNumber;
	}

	public void increasePlayerLife(int livesNumber) {
		this.livesNumber += livesNumber;
	}

	public void decreasePlayerLife(int n) {
		this.livesNumber -= n;
	}

	public boolean isGameActive() {
		return isGameActive;
	}

	public void setGameActive(boolean isGameActive) {
		this.isGameActive = isGameActive;
	}

	public void increaseTabs(int num) {
		numberOfTabletsTaken += num;
	}

	public int getTabletsTaken() {
		return numberOfTabletsTaken;
	}

	public void setCurrentActivity(BaseGameActivity act) {
		activity = act;
	}

	public BaseGameActivity getCurrentActivity() {
		return activity;
	}

}
