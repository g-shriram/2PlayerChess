package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Coin {
	
	private boolean isMoved = false;

	public Rook(boolean white,String coinName)
	{
		super(white,coinName);
		
	}
	
	@Override
	public boolean canMove(Board board, Square start, Square end) {
		
		//check for similar color
		if(end.getCoin()!=null && start.getCoin().isWhite() == end.getCoin().isWhite())
			return false;
			
		
		
		if(start.getX() == end.getX()) {
			
			//for right direction
			if(start.getY()>end.getY()) {
				for(int i=start.getY()-1;i>end.getY();i--) {
					if(board.getSquare(start.getX(), i).getCoin() != null) {
						return false;
					}
				}
			}
			
			//for left direction
			else {
				for(int i=start.getY()+1;i<end.getY();i++) {
					if(board.getSquare(start.getX(), i).getCoin() != null) {
						return false;
					}
				}
			}
		}
		
		if(start.getY() == end.getY()) {
			
			//for direction up
			if(start.getX()>end.getX()) {
				for(int i=start.getX()-1;i>end.getX();i--) {
					if(board.getSquare(i,start.getY()).getCoin() != null) {
						return false;
					}
				}
			}
			
			//for direction down
			else {
				for(int i=start.getX()+1;i<end.getX();i++) {
					if(board.getSquare(i,start.getY()).getCoin() != null) {
						return false;
					}
				}				
			}
		}
		
		return true;
	}



	// To get next possible moves
	@Override
	public List<Square> getPossibleSquares(Board board,int x, int y) {
		
		List<Square> possibleSquares = new ArrayList<Square>();
		
		//check all squares in direction along axes from current square.
		
		for(int i=x+1;i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(i, y))) {
				possibleSquares.add(board.getSquare(i, y));
			}
			else
				break;
				
		}
		
		for(int i=x-1;i>=0;i--) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(i, y))) {
				possibleSquares.add(board.getSquare(i, y));
			}
			else
				break;
		}
		
		for(int i=y+1;i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x, i))) {
				possibleSquares.add(board.getSquare(x, i));
			}
			else
				break;
		}
		
		for(int i=y-1;i>=0;i--) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x, i))) {
				possibleSquares.add(board.getSquare(x, i));
			}
			else
				break;
		}
		
		
		
		return possibleSquares;
	}


	//To get Coins that can be captured.
	@Override
	public List<Square> getCaptureSquares(Board board,int x, int y) {
		
		List<Square> captureSquares = new ArrayList<Square>();
		
		//check all squares in direction along axes from current square.
		
		for(int i=x+1;i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(i, y)) && board.getSquare(i,y).getCoin()!=null) {
				captureSquares.add(board.getSquare(i, y));
			}

		}
		
		for(int i=x-1;i>=0;i--) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(i, y))  && board.getSquare(i,y).getCoin()!=null) {
				captureSquares.add(board.getSquare(i, y));
			}

		}
		
		for(int i=y+1;i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x, i)) && board.getSquare(x,i).getCoin()!=null) {
				captureSquares.add(board.getSquare(x, i));
			}

		}
		
		for(int i=x-1;i>=0;i--) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x, i)) && board.getSquare(x,i).getCoin()!=null) {
				captureSquares.add(board.getSquare(x, i));
			}

		}
		
		return captureSquares;
	}

	public boolean isMoved() {
		return isMoved;
	}



	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}


}
