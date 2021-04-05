package com.cpt4lazy.cpt4lazyserver.entity.job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Company {
    private String display_name;
}
