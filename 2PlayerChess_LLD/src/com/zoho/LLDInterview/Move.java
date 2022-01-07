package com.zoho.LLDInterview;

public class Move {
	 
	private Player player;
	private Square start;
	private Square end;
	private Coin coinMoved;
	private Coin coinKilled;
	private boolean castlingMove = false;
	  
	public Move() {
	  
	}
	  
	public Move(Player player){
		this.player = player;
	}
  
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Coin getCoinMoved() {
		return coinMoved;
	}

	public void setCoinMoved(Coin coinMoved) {
		this.coinMoved = coinMoved;
	}

	public Coin getCoinKilled() {
		return coinKilled;
	}

	public void setCoinKilled(Coin coinKilled) {
		this.coinKilled = coinKilled;
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
