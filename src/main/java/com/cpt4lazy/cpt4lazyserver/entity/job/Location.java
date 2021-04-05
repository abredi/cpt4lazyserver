package com.cpt4lazy.cpt4lazyserver.entity.job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Location {
    private String display_name;
    private String[] area;
}
