package com.sdl.turnkeyapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessToken {
	@JsonProperty("Token")
	private String token;
	@JsonProperty("ValidThrough")
	private int validThrough;

}
