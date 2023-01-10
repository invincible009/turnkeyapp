package com.sdl.turnkeyapp.models;

public class Issue{
	private int accuracy;
	private int ranking;
	private String profName;
	private String icdName;
	private int iD;
	private String icd;
	private String name;

	public int getAccuracy(){
		return accuracy;
	}

	public int getRanking(){
		return ranking;
	}

	public String getProfName(){
		return profName;
	}

	public String getIcdName(){
		return icdName;
	}

	public int getID(){
		return iD;
	}

	public String getIcd(){
		return icd;
	}

	public String getName(){
		return name;
	}
}
