package com.sdl.turnkeyapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SpecialisationItem{

	@Id
	@GeneratedValue
	private int id;
	private int specialistID;
	private int iD;
	private String name;

	public int getSpecialistID(){
		return specialistID;
	}

	public int getID(){
		return iD;
	}

	public String getName(){
		return name;
	}
}
