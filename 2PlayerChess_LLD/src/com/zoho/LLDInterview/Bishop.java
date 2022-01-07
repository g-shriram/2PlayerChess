package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Coin {

	public Bishop(boolean white,String coinName)
	{
		super(white,coinName);
		
	}
	
	@Override
	public boolean canMove(Board board, Square start, Square end) {
		
		//check for similar color
		if(end.getCoin()!=null && start.getCoin().isWhite() == end.getCoin().isWhite())
			return false;
	
		//check diagonal constraint
		if(Math.abs(start.getX() - end.getX()) != Math.abs(start.getY() - end.getY()))
			return false;
		
		else {
			//for lower half case
			if(start.getX() - end.getX()>0) {
				
				//for right side case
				if(start.getY() - end.getY()>0) {
					
					//check null condition for squares between start and end
					for(int i=start.getX()-1,j = start.getY()-1;i>end.getX() && j>end.getY();i--,j--) {
						if(board.getSquare(i,j).getCoin() != null) {
							return false;
						}
					}
				}
				
				//for left side case
				else {
					
					//check null condition for squares between start and end
					for(int i=start.getX()-1,j = start.getY()+1;i>end.getX() && j<end.getY();i--,j++) {
						if(board.getSquare(i,j).getCoin() != null) {
							return false;
						}
					}
				}
			}
			
			//for upper half case
			else {
				
				//for right side case
				if(start.getY() - end.getY()>0) {
					
					//check null condition for squares between start and end
					for(int i=start.getX()+1,j = start.getY()-1;i<end.getX() && j>end.getY();i++,j--) {
						if(board.getSquare(i,j).getCoin() != null) {
							return false;
						}
					}
				}
				
				//for left side case
				else {
					
					//check null condition for squares between start and end
					for(int i=start.getX()+1,j = start.getY()+1;i<end.getX() && j<end.getY();i++,j++) {
						if(board.getSquare(i,j).getCoin() != null) {
							return false;
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
		
		//check all squares in direction along diagonals from current square.
		
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
		
		//check all squares in direction along diagonals from current square.
		
		for(int i=1;x+i<8 && y+i<8;i++) {
			if(board.getSquare(x+i, y+i).getCoin()!=null && canMove(board,board.getSquare(x, y),board.getSquare(x+i, y+i))) {
				captureSquares.add(board.getSquare(x+i, y+i));
			}
		}
		
		for(int i=1;x-i>=0 && y-i>=0;i++) {
			if(board.getSquare(x-i, y-i).getCoin()!=null && canMove(board,board.getSquare(x, y),board.getSquare(x-i, y-i))) {
				captureSquares.add(board.getSquare(x-i, y-i));
			}
		}
		
		for(int i=1;x+i<8 && y-i>=0;i++) {
			if(board.getSquare(x+i, y-i).getCoin()!=null && canMove(board,board.getSquare(x, y),board.getSquare(x+i, y-i))) {
				captureSquares.add(board.getSquare(x+i, y-i));
			}
		}
		
		for(int i=1;x-i>=0 && y+i<8;i++) {
			if(board.getSquare(x-i, y+i).getCoin()!=null && canMove(board,board.getSquare(x, y),board.getSquare(x-i, y+i))) {
				captureSquares.add(board.getSquare(x-i, y+i));
			}
		}
		
		return captureSquares;
	}


	
}