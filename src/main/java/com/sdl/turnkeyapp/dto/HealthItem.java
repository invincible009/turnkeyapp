package com.sdl.turnkeyapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HealthItem {
    @JsonProperty("ID")
    private int id;

    @JsonProperty("Name")
    private String name;
}
