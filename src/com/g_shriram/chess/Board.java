package com.g_shriram.chess;

public class Board {
	Square squares[][];
	
	public Board() {
		squares = new Square[8][8];
		this.initializeBoard();
	}
	
	void initializeBoard(){
		
		// initialize white pieces
		squares[0][0] = new Square(0,0,new Rook(true,"W_R"));
		squares[0][1] = new Square(0,0,new Knight(true,"W_N"));
		squares[0][2] = new Square(0,0,new Bishop(true,"W_B"));
		squares[0][3] = new Square(0,0,new Queen(true,"W_Q"));
		squares[0][4] = new Square(0,0,new King(true,"W_K"));
		squares[0][5] = new Square(0,0,new Bishop(true,"W_B"));
		squares[0][6] = new Square(0,0,new Knight(true,"W_N"));
		squares[0][7] = new Square(0,0,new Rook(true,"W_R"));
		
		// initialize black pieces
		squares[7][0] = new Square(7,0,new Rook(false,"B_R"));
		squares[7][1] = new Square(7,0,new Knight(false,"B_N"));
		squares[7][2] = new Square(7,0,new Bishop(false,"B_B"));
		squares[7][3] = new Square(7,0,new Queen(false,"B_Q"));
		squares[7][4] = new Square(7,0,new King(false,"B_K"));
		squares[7][5] = new Square(7,0,new Bishop(false,"B_B"));
		squares[7][6] = new Square(7,0,new Knight(false,"B_N"));
		squares[7][7] = new Square(7,0,new Rook(false,"B_R"));
		
		//initialize white and black pawns
		for(int i=0;i<8;i++) {
			squares[1][i] = new Square(1,i,new Pawn(true,"W_P"));
			squares[6][i] = new Square(6,i,new Pawn(false,"B_P"));
		}
		
		
		
		//initialize remaining squares without any coins
		for(int i=2;i<6;i++) {
			for(int j=0;j<8;j++) {
				squares[i][j] = new Square(i,j,null);
			}
		}
		
		squares[2][0] = new Square(2,0,new Rook(true,"W_R"));
		squares[2][5] = new Square(2,5,new Rook(true,"W_R"));
		
	}
	
	public Square getSquare(int x,int y) {
		return this.squares[x][y];
	}
	
	public void setSquare(int x,int y,Square square) {
		this.squares[x][y] = square;
	}
	
	
	
	//print current instance of Board
	public void printBoard() {
		
		System.out.print("  ");
		
		//print column header
		for(int i=0;i<8;i++) {
			System.out.print( "   |  " + (char)('a'+i));
		}
		
		System.out.print("   |\n");
		
		System.out.println("--------------------------------------------------------------");		
	
		for(int i=7;i>=0;i--) {
			
			//print row header
			System.out.print("  " + String.valueOf(i+1) + "  |");
			
			for(int j=0;j<8;j++) {
				if(squares[i][j].getCoin()!=null)
					System.out.print("  "+ squares[i][j].getCoin().getCoinName()+ " |");
				else
					System.out.print("      |");
			}
			
			System.out.print("\n");
			System.out.println("--------------------------------------------------------------");	
		}
	}



}
