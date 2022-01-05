package com.g_shriram.chess;

import java.util.List;

public class ChessGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board board = new Board();
		
		board.printBoard();
		
		List<Square> possibleSquares = board.getSquare(2,0).getPossibleSquares(board);
		
		
		
		for(int i=0;i<possibleSquares.size();i++) {
			Square square = possibleSquares.get(i);
			System.out.println(String.valueOf(square.getX()) + "   " + String.valueOf(square.getY()));
		}
		
List<Square> captureSquares = board.getSquare(2,0).getCaptureSquares(board);
		
		
		
		for(int i=0;i<captureSquares.size();i++) {
			Square square = captureSquares.get(i);
			System.out.println(String.valueOf(square.getX()) + "   " + String.valueOf(square.getY()) + "   " + square.getCoin().getCoinName());
		}
		
	}

}
