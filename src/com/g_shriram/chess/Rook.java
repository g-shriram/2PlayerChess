package com.g_shriram.chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Coin {
	
	

	public Rook(boolean white,String coinName)
	{
		super(white,coinName);
		
	}
	
	
	
	@Override
	public boolean canMove(Board board, Square start, Square end) {
		
		if(end.getCoin()!=null && start.getCoin().isWhite() == end.getCoin().isWhite())
			return false;
		
		if(start.getX() == end.getX()) {
			if(start.getY()>end.getY()) {
				for(int i=start.getY()-1;i>end.getY();i--) {
					if(board.getSquare(start.getX(), i).getCoin() != null) {
						return false;
					}
				}
			}
			else {
				for(int i=start.getY()+1;i<end.getY();i++) {
					if(board.getSquare(start.getX(), i).getCoin() != null) {
						return false;
					}
				}
			}
		}
		
		if(start.getY() == end.getY()) {
			if(start.getX()>end.getX()) {
				for(int i=start.getX()-1;i>end.getX();i--) {
					if(board.getSquare(i,start.getY()).getCoin() != null) {
						return false;
					}
				}
			}
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



	@Override
	public List<Square> getPossibleSquares(Board board,int x, int y) {
		List<Square> possibleSquares = new ArrayList<Square>();
		
		for(int i=x+1;i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(i, y))) {
				possibleSquares.add(board.getSquare(i, y));
			}
			else
				break;
				
		}
		
		for(int i=x-1;i>0;i--) {
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
		
		for(int i=y-1;i>0;i--) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x, i))) {
				possibleSquares.add(board.getSquare(x, i));
			}
			else
				break;
		}
		
		
		
		return possibleSquares;
	}



	@Override
	public List<Square> getCaptureSquares(Board board,int x, int y) {
		List<Square> captureSquares = new ArrayList<Square>();
		
		for(int i=x+1;i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(i, y)) && board.getSquare(i,y).getCoin()!=null) {
				captureSquares.add(board.getSquare(i, y));
			}
			else
				break;
		}
		
		for(int i=x-1;i>0;i--) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(i, y))  && board.getSquare(i,y).getCoin()!=null) {
				captureSquares.add(board.getSquare(i, y));
			}
			else
				break;
		}
		
		for(int i=y+1;i<8;i++) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x, i)) && board.getSquare(i,y).getCoin()!=null) {
				captureSquares.add(board.getSquare(x, i));
			}
			else
				break;
		}
		
		for(int i=x-1;i>0;i--) {
			if(canMove(board,board.getSquare(x, y),board.getSquare(x, i)) && board.getSquare(i,y).getCoin()!=null) {
				captureSquares.add(board.getSquare(x, i));
			}
			else
				break;
		}
		
		return captureSquares;
	}




}
