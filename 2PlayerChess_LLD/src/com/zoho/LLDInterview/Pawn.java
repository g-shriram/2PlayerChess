package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Coin {
	
	private boolean firstMove=true;
	private boolean enPassant = false;

	public boolean isEnPassant() {
		return enPassant;
	}
	public void setEnPassant(boolean enPassant) {
		this.enPassant = enPassant;
	}
	public boolean isFirstMove() {
		return firstMove;
	}
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	public Pawn(boolean white,String coinName)
	{
		super(white,coinName);
		this.firstMove = true;
		
	}
	@Override
	public boolean canMove(Board board, Square start, Square end) {

		//check for similar color
		if(end.getCoin()!=null && start.getCoin().isWhite() == end.getCoin().isWhite())
			return false;
		
		if(Math.abs(end.getX() - start.getX()) +  Math.abs(end.getY() - start.getY()) > 2)
			return false;
		
		if(start.getCoin().isWhite()) {
			
			//validate white pawn direction
			if((end.getX() - start.getX()) <0)
				return false;
			
			if(start.getY() == end.getY()) {
				//check isFirstMove
				if(end.getX() - start.getX()==2 && (this.firstMove && board.getSquare(start.getX()+1, start.getY()).getCoin()==null && end.getCoin()==null))
					return true;
				
				if(end.getX() - start.getX()==1 && end.getCoin()==null)
					return true;
				
				
			}
			else {
				if(end.getCoin()!=null) 
					return true;
				
				//checks for En-passant move
				else if(board.getSquare(end.getX()-1,end.getY()).getCoin()!=null && board.getSquare(end.getX()-1,end.getY()).getCoin().isWhite()!=start.getCoin().isWhite() && board.getSquare(end.getX()-1,end.getY()).getCoin().getClass().getSimpleName().equals("Pawn")) {
					Pawn pawn = (Pawn)board.getSquare(end.getX()-1,end.getY()).getCoin();
					if(pawn.isEnPassant())
						return true;
					else
						return false;
				}
			}
		}
		else {
			//validate black pawn direction
			if((start.getX() - end.getX()) <0)
				return false;
			
			if(start.getY() == end.getY()) {
				//check isFirstMove
				if(start.getX() - end.getX()==2 && (this.firstMove && board.getSquare(start.getX()-1, start.getY()).getCoin()==null && end.getCoin()==null))
					return true;
				
				if(start.getX() - end.getX()==1 && end.getCoin()==null)
					return true;
				
				
			}
			else {
				if(end.getCoin()!=null) 
					return true;
				
				//checks for En-passant move
				else if(board.getSquare(end.getX()+1,end.getY()).getCoin()!=null && board.getSquare(end.getX()+1,end.getY()).getCoin().getClass().getSimpleName().equals("Pawn")) {
					Pawn pawn = (Pawn)board.getSquare(end.getX()+1,end.getY()).getCoin();
					if(pawn.isEnPassant())
						return true;
					else
						return false;
				}
			}
		}
		
		return false;
	}
	

	// To get next possible moves
	@Override
	public List<Square> getPossibleSquares(Board board,int x, int y) {
		
		List<Square> possibleSquares = new ArrayList<Square>();
		
		//next possible moves
		int x_moves[] = {1,2,-1,-2, 1,-1, 1,-1};
		int y_moves[] = {0,0, 0, 0,-1,-1, 1, 1};
		
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
		int x_moves[] = { 1,-1, 1,-1};
		int y_moves[] = {-1,-1, 1, 1};
		
		int i =0;
		while(i<4) {
			
			if((x+x_moves[i]) <0 || (x+x_moves[i])>=8 || (y+y_moves[i]) <0 || (y+y_moves[i])>=8) {
				i++;
				continue;
			}
			
			if(board.getSquare(x+x_moves[i],y+ y_moves[i]).getCoin()!=null && canMove(board,board.getSquare(x, y),board.getSquare(x+x_moves[i],y+ y_moves[i]))) {
				captureSquares.add(board.getSquare(x+x_moves[i],y+y_moves[i]));
			}
			
			//checks for En-passant move
			if(board.getSquare(x+x_moves[i],y+ y_moves[i]).getCoin()==null && canMove(board,board.getSquare(x, y),board.getSquare(x+x_moves[i],y+ y_moves[i]))) {
				if(board.getSquare(x+x_moves[i]-1,y+ y_moves[i]).getCoin()!=null && board.getSquare(x+x_moves[i]-1,y+ y_moves[i]).getCoin().getClass().getSimpleName().equals("Pawn"))
					captureSquares.add(board.getSquare(x+x_moves[i]-1,y+ y_moves[i]));
				else
					captureSquares.add(board.getSquare(x+x_moves[i]+1,y+ y_moves[i]));
			}
			
			i++;
		}
		
		
		
		
		return captureSquares;
	}


	
}