package com.g_shriram.chess;

import java.util.List;

public class Game {
	
	private Player[] players;
    private Board board;
    private Player currentTurn;
    private GameStatus status;
    private List<Move> movesPlayed;
  
    private void initialize(Player p1, Player p2)
    {
        players[0] = p1;
        players[1] = p2;
  
        board.initializeBoard();
  
        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        }
        else {
            this.currentTurn = p2;
        }
  
        movesPlayed.clear();
    }
  
    public boolean isEnd()
    {
        return this.getStatus().isActive();
    }
  
    public GameStatus getStatus()
    {
        return this.status;
    }
  
    public void setStatus(GameStatus status)
    {
        this.status = status;
    }
  
    public boolean playerMove(Player player, int startX, 
                                int startY, int endX, int endY)
    {
        Square startSquare = board.getSquare(startX, startY);
        Square endSquare = board.getSquare(startY, endY);
        Move move = new Move(player, startSquare, endSquare);
        return this.makeMove(move, player);
    }
  
    private boolean makeMove(Move move, Player player)
    {
        Coin sourceCoin = move.getStart().getCoin();
        if (sourceCoin == null) {
            return false;
        }
  
        // valid player
        if (player != currentTurn) {
            return false;
        }
  
        if (sourceCoin.isWhite() != player.isWhiteSide()) {
            return false;
        }
  
        // valid move?
        if (!sourceCoin.canMove(board, move.getStart(), 
                                            move.getEnd())) {
            return false;
        }
  
        // kill?
        Coin destPiece = move.getStart().getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
            move.setCoinKilled(destPiece);
        }
  
        // castling?
        if (sourceCoin != null && sourceCoin instanceof King
            && sourceCoin.isCastlingMove()) {
            move.setCastlingMove(true);
        }
  
        // store the move
        movesPlayed.add(move);
  
        // move piece from the stat box to end box
        move.getEnd().setCoin(move.getStart().getCoin());
        move.getStart().setCoin(null);
  
  
        // set the current turn to the other player
        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        }
        else {
            this.currentTurn = players[0];
        }
  
        return true;
    }
}
