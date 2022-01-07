package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Coin {

	public Queen(boolean white,String coinName)
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
		
		else if(start.getY() == end.getY()) {
			
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
		
		//for diagonal directions
		else {
			
			if(Math.abs(start.getX() - end.getX()) != Math.abs(start.getY() - end.getY()))
				return false;
			
			else {
				if(start.getX() - end.getX()>0) {
					if(start.getY() - end.getY()>0) {
						for(int i=start.getX()-1,j = start.getY()-1;i>end.getX() && j>end.getY();i--,j--) {
							if(board.getSquare(i,j).getCoin() != null) {
								return false;
							}
						}
					}
					else {
						for(int i=start.getX()-1,j = start.getY()+1;i>end.getX() && j<end.getY();i--,j++) {
							if(board.getSquare(i,j).getCoin() != null) {
								return false;
							}
						}
					}
				}
				else {
					if(start.getY() - end.getY()>0) {
						for(int i=start.getX()+1,j = start.getY()-1;i<end.getX() && j>end.getY();i++,j--) {
							if(board.getSquare(i,j).getCoin() != null) {
								return false;
							}
						}
					}
					else {
						for(int i=start.getX()+1,j = start.getY()+1;i<end.getX() && j<end.getY();i++,j++) {
							if(board.getSquare(i,j).getCoin() != null) {
								return false;
							}
						}
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
		
		//check all squares along axes and diagonals from current square.
		
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
		
		for(int i=1;x+i<8 && y+i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x+i, y+i))) {
				possibleSquares.add(board.getSquare(x+i, y+i));
			}
			else
				break;
		}
		
		for(int i=1;x-i>=0 && y-i>=0;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x-i, y-i))) {
				possibleSquares.add(board.getSquare(x-i, y-i));
			}
			else
				break;
		}
		
		for(int i=1;x+i<8 && y-i>=0;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x+i, y-i))) {
				possibleSquares.add(board.getSquare(x+i, y-i));
			}
			else
				break;
		}
		
		for(int i=1;x-i>=0 && y+i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x-i, y+i))) {
				possibleSquares.add(board.getSquare(x-i, y+i));
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
		
		//check all squares along axes and diagonals from current square.
		
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
	
		for(int i=1;x+i<8 && y+i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x+i, y+i)) && board.getSquare(x+i, y+i).getCoin()!=null) {
				captureSquares.add(board.getSquare(x+i, y+i));
			}
		}
		
		for(int i=1;x-i>=0 && y-i>=0;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x-i, y-i)) && board.getSquare(x-i, y-i).getCoin()!=null) {
				captureSquares.add(board.getSquare(x-i, y-i));
			}
		}
		
		for(int i=1;x+i<8 && y-i>=0;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x+i, y-i)) && board.getSquare(x+i, y-i).getCoin()!=null) {
				captureSquares.add(board.getSquare(x+i, y-i));
			}
		}
		
		for(int i=1;x-i>=0 && y+i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x-i, y+i)) && board.getSquare(x-i, y+i).getCoin()!=null) {
				captureSquares.add(board.getSquare(x-i, y+i));
			}
		}
		
				
		return captureSquares;
	}


}