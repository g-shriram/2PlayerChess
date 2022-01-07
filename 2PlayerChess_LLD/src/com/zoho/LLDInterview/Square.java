package com.zoho.LLDInterview;

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
	
	public boolean isPossibleToCapture( List<Square> possibleCapture,int i,int j) {
		
		for(Square square:possibleCapture) {
			if(square.getX() == i && square.getY() == j)
				return true;
		}
		
		return false;
	}
	
	public boolean isPossibleMove(List<Square> possibleMoves,int i,int j) {

		for(Square square:possibleMoves) {
			if(square.getX() == i && square.getY() == j)
				return true;
		}
		
		return false;
	}
	
	public void printPossibleSquares(Board board) {
		System.out.println("Next possible Moves : [ (CUR) - Your current spot\t(*) - Possible Move\t(W_X/B_X) - Coins can be captured.]\n");
		
		List<Square> possibleSquares = this.getPossibleSquares(board);
		List<Square> captureSquares = this.getCaptureSquares(board);
		
		System.out.print("  ");
		
		//print column header
		for(int i=0;i<8;i++) {
			System.out.print( "   |  " + (char)('a'+i));
		}
		
		System.out.print("   |\n");
		
		System.out.println("--------------------------------------------------------------");		
	
		for(int i=7;i>=0;i--) {
			
			//print row header
			System.out.print("  " + String.valueOf(i+1) + "  |");
			for(int j=0;j<8;j++) {
				if(i==this.getX() && j == this.getY()) {
					System.out.print(" CUR  |"  );
				}
				else if(isPossibleToCapture(captureSquares,i,j)) {
					System.out.print("*"+ board.getSquare(i,j).getCoin().getCoinName() + "* |"  );
				}
				else if(isPossibleMove(possibleSquares,i,j)) {
					System.out.print("   *  |");
				}
				else
				System.out.print("      |");
			}
			
			System.out.print("\n");
			System.out.println("--------------------------------------------------------------");	
		}
		
		System.out.print("\nPossible Moves:");
		
		for(Square square:possibleSquares) {
			System.out.print("  "+new Helper().coordinatesToString(square.getX(), square.getY()));
		}
		
		System.out.print("\nCoins can be captured:");
		
		for(Square square:captureSquares) {
			System.out.print("  "+square.getCoin().getCoinName()+"("+new Helper().coordinatesToString(square.getX(), square.getY())+")");
		}
		
		System.out.println('\n');
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
}
