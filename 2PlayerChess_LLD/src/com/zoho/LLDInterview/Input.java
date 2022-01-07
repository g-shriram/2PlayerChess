package com.zoho.LLDInterview;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Input {

	private boolean storedMove = false;
	private String path ="";
	private Scanner scanner;

	public Input(){
		this.storedMove = false;
		scanner = new Scanner(System.in);
	}
	
	
	public Input(boolean storedMove,String path){
		
		this.storedMove = storedMove;
		this.path = path;
		
		try  
		{  
			FileInputStream fis=new FileInputStream(this.path);       
			scanner = new Scanner(fis);    
		}  
		catch(IOException e)  
		{  
			e.printStackTrace();  
		} 
	}
	
	public static boolean isValidInput(String path) {
		try  
		{  
			//validate file path 
			FileInputStream fis=new FileInputStream(path);       
			Scanner scanner = new Scanner(fis);  

			//validate moves in file
			while(scanner.hasNext()) {
				scanner.next();
			}
			
			scanner.close();
			return true;
		}  
		catch(Exception e)  
		{  
			return false;  
		} 
	}
	
	public String next() {
		
		String string = scanner.next().toLowerCase();
		
		if(storedMove)
			System.out.println(string);
		
		return string;
	}
	
	public boolean hasNext() {
		return scanner.hasNext();
	}
	
	
	protected void finalize()  {
		this.scanner.close();
	}
	
	public boolean isStoredMove() {
		return storedMove;
	}

	public void setStoredMove(boolean storedMove) {
		this.storedMove = storedMove;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
