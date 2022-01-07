package com.zoho.LLDInterview;

import java.util.ArrayList;
import java.util.List;

public class Helper {
	
	public List<Integer> stringToCoordinates(String string){
		List<Integer> pair = new ArrayList<Integer>();
		
		if(string.length()==2) {
			pair.add((int)(string.charAt(0)) - '0' - 1);
			if((int)string.charAt(1) - 'a' >=0)
				pair.add((int)string.charAt(1) - 'a');
			else
				pair.add((int)string.charAt(1) - 'A');
		}
		
		return pair;
	}
	
	public String coordinatesToString(int x,int y) {
		String string="";
		string+=(x+1);
		string+=(char)(y+97);
		return string;
	}


}
