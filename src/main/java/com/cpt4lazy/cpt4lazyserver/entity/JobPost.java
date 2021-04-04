package com.cpt4lazy.cpt4lazyserver.entity;

import com.cpt4lazy.cpt4lazyserver.entity.job.Job;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class JobPost {
    private List<Job> results;
}
