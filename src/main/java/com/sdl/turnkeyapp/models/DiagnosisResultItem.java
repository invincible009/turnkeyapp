package com.sdl.turnkeyapp.models;

import java.util.List;

public class DiagnosisResultItem{
	private Issue issue;
	private List<SpecialisationItem> specialisation;

	public Issue getIssue(){
		return issue;
	}

	public List<SpecialisationItem> getSpecialisation(){
		return specialisation;
	}
}