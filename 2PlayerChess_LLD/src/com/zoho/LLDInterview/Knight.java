package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Coin {

	public Knight(boolean white,String coinName)
	{
		super(white,coinName);
		
	}
	
	@Override
	public boolean canMove(Board board, Square start, Square end) {
		
		//check for similar color
		if(end.getCoin()!=null && start.getCoin().isWhite() == end.getCoin().isWhite())
			return false;

		return true;
	}
	
	// To get next possible moves
	@Override
	public List<Square> getPossibleSquares(Board board,int x, int y) {
		
		List<Square> possibleSquares = new ArrayList<Square>();
		
		//next possible moves
		int x_moves[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int y_moves[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
		
		int i =0;
		while(i<8) {
			if((x+x_moves[i]) <0 || (x+x_moves[i])>=8 || (y+y_moves[i]) <0 || (y+y_moves[i])>=8) {
				i++;
				continue;
			}
			
			if(canMove(board,board.getSquare(x, y),board.getSquare(x+x_moves[i], y+y_moves[i]))) {
				possibleSquares.add(board.getSquare(x+x_moves[i],y+y_moves[i]));
			}
			
			i++;
		}
		
		return possibleSquares;
	}


	//To get Coins that can be captured.
	@Override
	public List<Square> getCaptureSquares(Board board,int x, int y) {
		
		List<Square> captureSquares = new ArrayList<Square>();
		
		//next possible moves
		int x_moves[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int y_moves[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
		
		int i =0;
		while(i<8) {
			if((x+x_moves[i]) <0 || (x+x_moves[i])>=8 || (y+y_moves[i]) <0 || (y+y_moves[i])>=8) {
				i++;
				continue;
			}
			
			if(board.getSquare(x+x_moves[i], y+y_moves[i]).getCoin()!=null && canMove(board,board.getSquare(x, y),board.getSquare(x+x_moves[i], y+y_moves[i]))) {
				captureSquares.add(board.getSquare(x+x_moves[i],y+y_moves[i]));
			}
			
			i++;
		}
		
		return captureSquares;
	}


}