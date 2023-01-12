package com.sdl.turnkeyapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Issue{
	@Id
	@GeneratedValue
	private int iD;
	private int accuracy;
	private int ranking;
	private String profName;
	private String icdName;

	private String icd;
	private String name;

	public Issue() {
	}


	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) {
		this.profName = profName;
	}

	public String getIcdName() {
		return icdName;
	}

	public void setIcdName(String icdName) {
		this.icdName = icdName;
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getIcd() {
		return icd;
	}

	public void setIcd(String icd) {
		this.icd = icd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
