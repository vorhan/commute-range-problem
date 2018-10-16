package com.test.commuterangeproblem.controller;

import com.test.commuterangeproblem.model.City;
import com.test.commuterangeproblem.service.ReachableCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ReachableCitiesController {

    @Autowired
    ReachableCitiesService reachableCitiesService;

    @GetMapping(value = "/reachable-cities/{cityId}")
    public Collection<City> getReachableCities(@PathVariable("cityId") Integer cityId,
                                               @RequestParam("timeLimit") Integer timeLimit) {

        return reachableCitiesService.getReachableCities(cityId, timeLimit);
    }
}
