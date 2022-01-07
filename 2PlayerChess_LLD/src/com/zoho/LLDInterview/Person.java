package com.zoho.LLDInterview;

public class Person {

	public enum Level{BEGINNER,ADVANCED,EXPERT};
	private String name;
	public  Level level;
	
	public Person(String name,Level level) {
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	
}
