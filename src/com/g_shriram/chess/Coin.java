package com.g_shriram.chess;

import java.util.ArrayList;
import java.util.List;

public abstract class Coin {
	
	private boolean killed = false;
	private boolean white = false;
	private String coinName = "";

	
	public Coin(boolean white,String coinName) {
		this.white = white;
		this.coinName = coinName;
	}
	
	public String getCoinName() {
		return this.coinName;
	}
	
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	public boolean isWhite() {
		return this.white == true;
	}
	
	public void setWhite(boolean white) {
		this.white = white;
	}
	
	public boolean isKilled() {
		return this.killed == true;
	}
	
	
	public void setKilled(boolean killed) {
		this.killed = killed;
	}
	
	
	public abstract List<Square> getPossibleSquares(Board board,int x,int y);
	
	public abstract List<Square> getCaptureSquares(Board board,int x,int y);
	
	public abstract boolean canMove(Board board,Square start,Square end);

}
