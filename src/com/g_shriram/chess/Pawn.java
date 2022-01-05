package com.g_shriram.chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Coin {

	public Pawn(boolean white,String coinName)
	{
		super(white,coinName);
		
	}
	@Override
	public boolean canMove(Board board, Square start, Square end) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public List<Square> getPossibleSquares(Board board,int x, int y) {
		List<Square> possibleSquares = new ArrayList<Square>();
		return possibleSquares;
	}



	@Override
	public List<Square> getCaptureSquares(Board board,int x, int y) {
		List<Square> captureSquares = new ArrayList<Square>();
		return captureSquares;
	}


	
}