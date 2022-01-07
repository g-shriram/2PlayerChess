package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;

public class King extends Coin {

	private boolean isMoved = false;

	public King(boolean white,String coinName)
	{
		super(white,coinName);
		
	}
	
	@Override
	public boolean canMove(Board board, Square start, Square end) {
		
		//check for similar color
		if(end.getCoin()!=null && start.getCoin().isWhite() == end.getCoin().isWhite())
			return false;
				
		
		if(isCheck(board,start,end)) {
			return false;
		}
		
	
		if(!this.isMoved && isCastlingMove(end)) {
			
			if(!isCastlingPossible(board,end))
				return false;
			else
				return true;
		}
		
		
		if(Math.abs(start.getX() - end.getX())>1 || Math.abs(start.getY() - end.getY())>1)
			return false;
		
		
		return true;
	}
	
	public boolean isCastlingPossible(Board board,Square square) {
	
		int x = square.getX();
    	int y = square.getY();
    	
    	//check for rook's and king's first move and null conditions for squares between king and rook
    	
    	if(x==0 && y==2) {
    		Rook rook = (Rook) board.getSquare(0, 0).getCoin();
    		if(rook==null || rook.isMoved())
    			return false;
    		
    		if(board.getSquare(0, 1).getCoin()!=null || board.getSquare(0, 2).getCoin()!=null || board.getSquare(0, 3).getCoin()!=null)
    			return false;
    	}

    	if(x==0 && y==6) {
    		Rook rook = (Rook) board.getSquare(0, 7).getCoin();
    		if(rook==null || rook.isMoved())
    			return false;
    		
    		if(board.getSquare(0, 5).getCoin()!=null || board.getSquare(0, 6).getCoin()!=null )
    			return false;
    	}
    	
    	if(x==7 && y==2) {
    		Rook rook = (Rook) board.getSquare(7, 0).getCoin();
    		if(rook==null || rook.isMoved())
    			return false;
    		
    		if(board.getSquare(7, 1).getCoin()!=null || board.getSquare(7, 2).getCoin()!=null || board.getSquare(7, 3).getCoin()!=null)
    			return false;
    	}
    	
    	if(x==7 && y==6) {
    		Rook rook = (Rook) board.getSquare(7, 7).getCoin();
    		if(rook==null || rook.isMoved())
    			return false;
    		
    		if(board.getSquare(7, 5).getCoin()!=null || board.getSquare(7, 6).getCoin()!=null )
    			return false;
    	}
		return true;
	}
	
    public boolean isCastlingMove(Square square) {

    	int x = square.getX();
    	int y = square.getY();
    	
    	if((x==0 && y==2) || (x==0 && y==6) || (x==7 && y==2) || (x==7 && y==6))
    		return true;
    	
    	return false;
    }
	
    //checks for attack on given square from all opponent's coins
	private boolean isCheck(Board board, Square start, Square end) {
		if(end.getCoin()!=null)
			end.getCoin().setWhite(!end.getCoin().isWhite());
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				
				if(board.getSquare(i, j).getCoin()!=null && board.getSquare(i, j).getCoin().isWhite()!=start.getCoin().isWhite()) {
					
					List<Square> possibleSquares = board.getSquare(i, j).getPossibleSquares(board);
					
					for(Square square:possibleSquares) {
						if(square.getX() == end.getX() && square.getY()==end.getY()) {
							if(end.getCoin()!=null)
								end.getCoin().setWhite(!end.getCoin().isWhite());
							return true;
						}
							
					}
					
				}
			}
		}
		if(end.getCoin()!=null)
			end.getCoin().setWhite(!end.getCoin().isWhite());
		return false;
	}
	
	// To get next possible moves
	@Override
	public List<Square> getPossibleSquares(Board board,int x, int y) {

		
		List<Square> possibleSquares = new ArrayList<Square>();
		int x_moves[] = { 1,1,1, 0,0,-1,-1,-1, 0,0};
		int y_moves[] = {-1,0,1,-1,1,-1, 0, 1,-2,2};
		
		int i =0;
		while(i<10) {
			
			
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
		int x_moves[] = { 1,1,1, 0,0,-1,-1,-1};
		int y_moves[] = {-1,0,1,-1,1,-1, 0, 1};
		
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

	public boolean isMoved() {
		return isMoved;
	}
	
	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}
	


	
}
