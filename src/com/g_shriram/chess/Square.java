package com.g_shriram.chess;

import java.util.List;

public class Square {
	private Coin coin;
	int x;
	int y;

	
	public Square(int x,int y,Coin coin) {
		this.coin = coin;
		this.x = x;
		this.y = y;

	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Coin getCoin() {
		return this.coin;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public List<Square> getPossibleSquares(Board board){
		if(this.getCoin()!=null)
			return getCoin().getPossibleSquares(board,this.x, this.y);
		else
			return null;
	}
	
	public List<Square> getCaptureSquares(Board board){
		if(this.getCoin()!=null)
			return getCoin().getCaptureSquares(board,this.x, this.y);
		else
			return null;
	}
	
}
