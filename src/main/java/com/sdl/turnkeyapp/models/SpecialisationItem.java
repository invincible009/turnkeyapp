package com.sdl.turnkeyapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SpecialisationItem{

	@Id
	@GeneratedValue
	private int iD;
	private int specialistID;
	private String name;

	public SpecialisationItem() {
	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public int getSpecialistID() {
		return specialistID;
	}

	public void setSpecialistID(int specialistID) {
		this.specialistID = specialistID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
