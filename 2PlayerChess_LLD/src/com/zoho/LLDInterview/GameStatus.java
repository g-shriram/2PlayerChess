package com.zoho.LLDInterview;

public class GameStatus {
	
	public boolean active;
	public Player playerWon;
	
	public boolean stalemate;
	public boolean resignation;
	
	public GameStatus() {
		this.active = true;
		this.playerWon =null;
		this.resignation = false;
		this.stalemate = false;
	}
	
	public boolean isActive() {
		return this.active;
	}

	public Player getPlayerWon() {
		return playerWon;
	}

	public void setPlayerWon(Player playerWon) {
		this.playerWon = playerWon;
	}

	public boolean isStalemate() {
		return stalemate;
	}

	public void setStalemate(boolean stalemate) {
		this.stalemate = stalemate;
	}

	public boolean isResignation() {
		return resignation;
	}

	public void setResignation(boolean resignation) {
		this.resignation = resignation;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
