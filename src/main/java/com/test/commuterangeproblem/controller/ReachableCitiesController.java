package com.test.commuterangeproblem.controller;

import com.test.commuterangeproblem.model.City;
import com.test.commuterangeproblem.service.ReachableCitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ReachableCitiesController {

    @Autowired
    ReachableCitiesService reachableCitiesService;

    @GetMapping(value = "/reachable-cities/{cityId}")
    public ResponseEntity<Collection<City>> getReachableCities(@PathVariable("cityId") Integer cityId,
                                               @RequestParam("timeLimit") Integer timeLimit) {

        return ResponseEntity.ok(reachableCitiesService.getReachableCities(cityId, timeLimit));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> error(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
