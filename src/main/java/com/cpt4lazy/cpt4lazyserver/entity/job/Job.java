package com.cpt4lazy.cpt4lazyserver.entity.job;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class Job {
    @JsonAlias("title")
    private String jobPosition;
    private String description;
    @JsonAlias("salary_is_predicted")
    private String benefits;
    private Location location;
    @JsonAlias("company")
    private Company jobPlace;
}



