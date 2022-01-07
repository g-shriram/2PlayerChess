package com.zoho.LLDInterview;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Output {
	
	private String path ="";
	
	public Output(String path) {
		
		String fileName = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
		this.path = path+ '\\' + fileName;
		
		try {
	        File file = new File(this.path);
	       
	        if (file.createNewFile()) {
	          System.out.println("Output File created: " + this.path);
	        } else {
	          System.out.println("File already exists.");
	        }
	        
	      } catch (IOException e) {
	        System.out.println("An error occurred while creating output file.");
	        e.printStackTrace();
	        System.exit(0);
	      }
	}
	
	public void initializeOutput(Game game) {
		
		this.writeString("\n   ************************CHESS SCORE SHEET***********************\n");
		
		String player1_colour="White";
		String player2_colour="Black";
		
		if(!game.getPlayer1().isWhiteSide()) {
			player1_colour = "Black";
		}
		
		if(!game.getPlayer2().isWhiteSide()) {
			player2_colour = "Black";
		}
		
		this.writeString("\nPlayer 1:   "+game.getPlayer1().getPerson().getName()+"  |  "+player1_colour+"  |  "+game.getPlayer1().getPerson().getLevel()+"\n");
		this.writeString("\nPlayer 2:   "+game.getPlayer2().getPerson().getName()+"  |  "+player2_colour+"  |  "+game.getPlayer2().getPerson().getLevel()+"\n");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  
		
		this.writeString("\nData and Time: "+formatter.format(date)+"\n");
		this.writeString("\n\n   Player     |      Coin     |     From   |     To     | Coin_Captured  |\n");
	}
	
	public void writeString(String string) {
		
		try {
		      FileWriter fileWriter = new FileWriter(this.path,true);
		      fileWriter.write(string);
		      fileWriter.close();
		      
		    } catch (IOException e) {
		      System.out.println("An error occurred while writing to output file.");
		      e.printStackTrace();
		      
		    }
	}
	public void writeMove(Move move) {
			
		String string = "";	
		String player = "p1";
			
		if(!move.getPlayer().isWhiteSide())
			player = "p2";
		
		string += "\n    "+player + "        |";
		string += "      "+move.getCoinMoved().getCoinName() + "      |";
		string += "     "+new Helper().coordinatesToString(move.getStart().getX(), move.getStart().getY()) + "     |";
		string += "     "+new Helper().coordinatesToString(move.getEnd().getX(), move.getEnd().getY()) + "     |";
		
		if(move.getCoinKilled()!=null)
			string += "     "+move.getCoinKilled().getCoinName() + "        |";
		else
			string+="                |";
		
		
		writeString(string);
			
	}
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
