package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.entity.JobPost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;


@RestController
public class JobController {
    @Value("${cpt4lazy.adzunAppId}")
    private String adzunAppId;
    @Value("${cpt4lazy.adzunKey}")
    private String adzunKey;
    @Value("${cpt4lazy.perPage}")
    private String perPage;
    private static final String BASE_URL = "https://api.adzuna.com/v1/api/jobs/us/search/1";

    @GetMapping(value = "jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<JobPost> getJobs(@RequestParam(defaultValue = "") String what, @RequestParam(defaultValue = "") String where) {
        return WebClient.create(BASE_URL)
                .get()
                .uri("?app_id={adzunAppId}&app_key={adzunKey}&results_per_page={perPage}&what=/{what}&where={where}", adzunAppId, adzunKey, perPage, what,where)
                .retrieve()
                .bodyToFlux(JobPost.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(100)));
    }
}
