package com.g_shriram.chess;

public class Move {
	  private Player player;
	  private Square start;
	  private Square end;
	  private Coin coinMoved;
	  private Coin coinKilled;
	  private boolean castlingMove = false;
	  
	  public Move(Player player, Square start, Square end)
	  {
	        this.player = player;
	        this.start = start;
	        this.end = end;
	    }
	  
	  
	  
	    public boolean isCastlingMove()
	    {
	        return this.castlingMove;
	    }
	  
	    public void setCastlingMove(boolean castlingMove)
	    {
	        this.castlingMove = castlingMove;
	    }
	    
	    public Square getStart() {
	    	return this.start;
	    }
	    
	    public void setStart(Square start) {
	    	this.start = start;
	    }
	    
	    public Square getEnd() {
	    	return this.end;
	    }
	    
	    public void setEnd(Square end) {
	    	this.end = end;
	    }
}
