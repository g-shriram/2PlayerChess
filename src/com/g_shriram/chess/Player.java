package com.g_shriram.chess;

public class Player {
	private Person person;
	boolean whiteSide = false;

	public Player(Person person,boolean whiteSide) {
		this.person = person;
		this.whiteSide = whiteSide;
		}

	public Person getPerson() {
		return this.person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public boolean isWhiteSide() {
		return this.whiteSide == true;
	}
	
	public void setWhiteSide(boolean whiteSide) {
		this.whiteSide = whiteSide;
	}


}
