package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	private Player player1;
	private Player player2;
    private Board board;
    private Player currentPlayer;
    private GameStatus status;
    private List<Move> movesPlayed;
    private List<Coin> killedCoins;
    private Pawn enPassantCoin;

    private Input input;
    private Output output;
  
  
	public Game(Player player1, Player player2,Input input,Output output)
    {
    	
        this.player1 = player1;
        this.player2 = player2;
        this.input = input;
        this.output = output;
        this.enPassantCoin = null;
        
        this.status = new GameStatus();
        this.board = new Board();
       
        board.initializeBoard();
        output.initializeOutput(this);
  
        if (player1.isWhiteSide()) {
            this.currentPlayer = player1;
        }
        else {
            this.currentPlayer = player2;
        }
  
        movesPlayed = new ArrayList<Move>();
        killedCoins = new ArrayList<Coin>();
        movesPlayed.clear();
        killedCoins.clear();
     
    }
    
    public void playGame() {
    	
    	while(this.getStatus().isActive()) {
    		
    		System.out.println('\n'+currentPlayer.getPerson().getName() + "'s Turn : \n");
    		
    		if(!input.hasNext()) {
    			resignGame();
    			break;
    		}
    		
    		String nextMove = input.next();
    		    		
    		if(nextMove.equals("exit")) {
    			resignGame();
    			break;
    		}
    		
    		if(nextMove.equals("print"))
    		{
    			this.board.printBoard();
    			continue;
    		}
    		
    		if(nextMove.length()>2)
    		{
    			System.out.println("Invalid Move");
    			continue;
    		}
    		
    		List<Integer> coordinates = new Helper().stringToCoordinates(nextMove);

    		Move move = new Move(currentPlayer);
    		move.setStart(board.getSquare(coordinates.get(0),coordinates.get(1)));
    		
    		if(move.getStart().getCoin()==null || (currentPlayer.isWhiteSide() != move.getStart().getCoin().isWhite())) {
    			System.out.println("Invalid Move");
    			continue;
    		}
    		
    		showDescription(move);
    		
    		if(!input.hasNext()) {
    			resignGame();
    			break;
    		}
    		
    		nextMove = input.next();
    		    		
    		if(nextMove.equals("exit")) {
    			resignGame();
    			break;
    		}
    		
    		if(nextMove.equals("print"))
    		{
    			this.board.printBoard();
    			continue;
    		}
    		
    		if(nextMove.length()>2)
    		{
    			System.out.println("Invalid Move");
    			continue;
    		}
    		
    		coordinates = new Helper().stringToCoordinates(nextMove);
    		
    		move.setEnd(board.getSquare(coordinates.get(0),coordinates.get(1)));
    		
    		
    		if(!isValidMove(move)) {
    			System.out.println("Invalid Move");
    			continue;
    		}
    		
    		updateBoard(move);
    		
    		if(isCheck(move)) {
    			System.out.println("!!!!!!!!!!!!!!! Check !!!!!!!!!!!!!!!");
    		}
    		
    		changeTurn();
    	}
    }
    
    public void exitGame() {
    	System.out.println("\n **************** Game Ended by " + currentPlayer.getPerson().getName()+ "  ***************** \n" );
    	System.out.println("\n Records can be found at : "+output.getPath()+" \n" );
    }
    
    public void resignGame() {
    	
    	this.getStatus().setActive(false);
		this.getStatus().setResignation(true);
		if(currentPlayer == player1)
			this.getStatus().setPlayerWon(player2);
		else
			this.getStatus().setPlayerWon(player1);
		exitGame();
    }
    
    public void changeTurn() {
    	
    	if(this.currentPlayer == this.player1)
			this.setCurrentPlayer(player2);
		else
			this.setCurrentPlayer(player1);
    }
    
    public boolean isCheck(Move move) {
    	
    	List<Square> captureSquares = move.getEnd().getCaptureSquares(board);
    	
    	if(captureSquares == null)
    		return false;
    	
    	//check any square that has king in coins that can be captured.
    	for(Square square:captureSquares) {
    		if(square.getCoin().getClass().getSimpleName().equals("King"))
    			return true;
    	}
    	
    	return false;
    }
    
    public void showDescription(Move move) {
    	
    	if(move.getStart().getCoin()==null)
    		return;
    	
    	System.out.println("\nCurrent Coin Type : " + move.getStart().getCoin().getClass().getSimpleName() + "( "+move.getStart().getCoin().getCoinName()+" )");
    	board.getSquare(move.getStart().getX(), move.getStart().getY()).printPossibleSquares(board);
    }
    
    public void updateBoard(Move move) {
    	
    	if(this.enPassantCoin!=null) {
    		this.enPassantCoin.setEnPassant(false);
    		this.enPassantCoin = null;
    	}
    	
    	move.setCoinMoved(move.getStart().getCoin());
    	
    	//Check Em=passant move and Pawn promotion
    	if(move.getStart().getCoin().getClass().getSimpleName().equals("Pawn")) {
    		Pawn pawn = (Pawn)move.getStart().getCoin();
    		if(pawn.isFirstMove()) {
    			pawn.setFirstMove(false);
    			if(Math.abs(move.getStart().getX() - move.getEnd().getX()) == 2) {
    				pawn.setEnPassant(true);
    				this.enPassantCoin = pawn;
    			}
    				
    		}
    		
    		if(move.getEnd().getCoin()==null && move.getEnd().getY()!=move.getStart().getY()) {
    			if(board.getSquare(move.getEnd().getX()-1,move.getEnd().getY()).getCoin()!=null && board.getSquare(move.getEnd().getX()-1,move.getEnd().getY()).getCoin().getClass().getSimpleName().equals("Pawn"))
    				board.getSquare(move.getEnd().getX()-1,move.getEnd().getY()).setCoin(null);
				else
					board.getSquare(move.getEnd().getX()+1,move.getEnd().getY()).setCoin(null);
    		}
    		
    		if(move.getEnd().getX()==0 || move.getEnd().getX()==7) {
    			this.promotePawn(move);
    		}
    	}
    	
    	if(move.getStart().getCoin().getClass().getSimpleName().equals("Rook")) {
    		Rook rook = (Rook)move.getStart().getCoin();
    		rook.setMoved(true);
    		
    	}
    	
    	//check for castling move
    	if(move.getStart().getCoin().getClass().getSimpleName().equals("King")) {
    		if(isCastling(move)) {
    			doCastle(move);
    		}
    		King king = (King)move.getStart().getCoin();
    		king.setMoved(true);
    	}
    	
    	//check coins for capturing
    	if(move.getEnd().getCoin()!=null) {
    		move.setCoinKilled(move.getEnd().getCoin());
    		killedCoins.add(move.getEnd().getCoin());
    	}
	
    	movesPlayed.add(move);

    	move.getEnd().setCoin(move.getStart().getCoin());
    	move.getStart().setCoin(null);
    	output.writeMove(move);
    	System.out.println("Coin Placed !!!");
    	return;
    }
    
    public boolean isCastling(Move move) {
    	
    	int x = move.getEnd().getX();
    	int y = move.getEnd().getY();
    	
    	if((x==0 && y==2) || (x==0 && y==6) || (x==7 && y==2) || (x==7 && y==6))
    		return true;
    	
    	return false;
    }
    
    public void doCastle(Move move) {
    	
    	int x = move.getEnd().getX();
    	int y = move.getEnd().getY();
    	
    	//change rook's firstMove status
    	
    	if(x==0 && y==2) {
    		Rook rook = (Rook)board.getSquare(0, 0).getCoin();
    		board.getSquare(0, 0).setCoin(null);
    		board.getSquare(0, 3).setCoin(rook);
    	}

		if(x==0 && y==6) {
			Rook rook = (Rook)board.getSquare(0, 7).getCoin();
			board.getSquare(0, 7).setCoin(null);
			board.getSquare(0, 5).setCoin(rook);
		}
		
		if(x==7 && y==2) {
			Rook rook = (Rook)board.getSquare(7, 0).getCoin();
			board.getSquare(7, 0).setCoin(null);
			board.getSquare(7, 3).setCoin(rook);
		}
		
		if(x==7 && y==6) {
			Rook rook = (Rook)board.getSquare(7, 7).getCoin();
			board.getSquare(7, 7).setCoin(null);
			board.getSquare(7, 5).setCoin(rook);
		}


    	
    }
    
    public void promotePawn(Move move) {
    	
    	System.out.println("Choose a Coin to promote : (1 - Queen/2 - Bishop/3 - Knight/4 - Rook)");
    	Scanner in = new Scanner(System.in);
    	int choice = in.nextInt();
    	boolean isWhite = this.getCurrentPlayer().isWhiteSide();
    	
    	switch(choice) {
    	case 1:move.getStart().setCoin(new Queen(isWhite,"Q")); break;
    	case 2:move.getStart().setCoin(new Bishop(isWhite,"B"));break;
    	case 3:move.getStart().setCoin(new Knight(isWhite,"K"));break;
    	case 4:move.getStart().setCoin(new Rook(isWhite,"R"));break;
    	default:System.out.println("Invalid Move");
    	}

    	return;
    }
    
    public boolean isValidMove(Move move) {
    	return move.getStart().getCoin().canMove(board, move.getStart(), move.getEnd());
    }
  
    public boolean isActive()
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
         
    
    public Player getPlayer1() {
  		return player1;
  	}

  	public void setPlayer1(Player player1) {
  		this.player1 = player1;
  	}

  	public Player getPlayer2() {
  		return player2;
  	}

  	public void setPlayer2(Player player2) {
  		this.player2 = player2;
  	}

  	public Board getBoard() {
  		return board;
  	}

  	public void setBoard(Board board) {
  		this.board = board;
  	}

  	public Player getCurrentPlayer() {
  		return currentPlayer;
  	}

  	public void setCurrentPlayer(Player currentTurn) {
  		this.currentPlayer = currentTurn;
  	}

  	public List<Move> getMovesPlayed() {
  		return movesPlayed;
  	}

  	public void setMovesPlayed(List<Move> movesPlayed) {
  		this.movesPlayed = movesPlayed;
  	}

 
}
