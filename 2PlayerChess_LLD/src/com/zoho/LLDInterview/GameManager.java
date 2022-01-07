package com.zoho.LLDInterview;

import java.util.Scanner;

public class GameManager {

	private int noOfGames;
	
	private Player getPlayerDetails(int no) {
	
		Scanner input = new Scanner(System.in);
		String name;
		Person.Level level;
		boolean whiteSide = false;
		
		System.out.println("Enter Player "+no+" name :");
		name = input.next();
		
		System.out.println("Enter Player "+no+" Experience level (0 - Beginer, 1 - Advance, 2 - Expert):");
		level = Person.Level.values()[input.nextInt()];
		
		if(no == 1)
			whiteSide = true;
		
		Player player = new Player(new Person(name,level),whiteSide);
		
		return player;
	}
	

	private Input getInput() {
		
		Input input;
		Scanner in = new Scanner(System.in);
		int choice;
		System.out.println("Enter input mode (1 - From console/2 - From file):");
		choice = in.nextInt();
		
		if(choice==1) {
			input = new Input();
		}
		else
		{
			System.out.println("Enter location (Absolute path) of input file:");
			String path = in.next();
			
			while(!Input.isValidInput(path)) {
				System.out.println("Please check your file path/file format");
				path = in.next();
			}
			
			input = new Input(true,path);
		}
		
		return input;
	}
	
	private Output getOutput() {
		
		Output output;
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter location (Absolute path) for output file:");
		String path = in.next();
		

		
		output = new Output(path);
		
		
		return output;
	}
	
	private Game createNewGame(int no) {
		
		System.out.println("Please enter details of game "+no+" :");

		Input input = this.getInput();
		Output output = this.getOutput();
		
		Player player1 = this.getPlayerDetails(1);
		Player player2 = this.getPlayerDetails(2);
		Game game = new Game(player1,player2,input,output);
		
		return game;
	}
	
	public static void main(String[] args) {	
		
		GameManager gameManager = new GameManager();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("*************** Welcome to Two Player Chess Game *****************");
		System.out.print("Enter no of games :");
		
		gameManager.noOfGames = scanner.nextInt();
		
		for(int no =1;no<=gameManager.noOfGames;no++) {
			Game game = gameManager.createNewGame(no);
			System.out.println("Let's start the game...\n");
			game.playGame();
		}

		scanner.close();
	}

}
