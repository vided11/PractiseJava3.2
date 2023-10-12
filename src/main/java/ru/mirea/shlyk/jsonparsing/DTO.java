package ru.mirea.shlyk.jsonparsing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTO {
    @JsonProperty("designation")
    private String name;

    @JsonProperty("discovery_date")
    private String discoveryDate;

    public String getName() {
        return name;
    }

    public String getDate() {
        return discoveryDate;
    }

}

