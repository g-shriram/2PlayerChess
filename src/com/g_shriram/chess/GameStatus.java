package com.g_shriram.chess;

public class GameStatus {
	public boolean ACTIVE;
	public Player playerWon;
	
	public boolean STALEMATE;
	public boolean RESIGNATION;
	
	public boolean isActive() {
		return this.ACTIVE;
	}
	
	public Player getPlayerWon() {
		return this.playerWon;
	}
	
	public void setPlayerWon(Player playerWon) {
		this.playerWon = playerWon;
	}
	
}
