package com.test.commuterangeproblem.service;

import com.test.commuterangeproblem.model.City;
import com.test.commuterangeproblem.repository.CityRepository;
import com.test.commuterangeproblem.repository.RouteRepository;
import com.test.commuterangeproblem.service.searchcities.SearchCitiesService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReachableCitiesService {

    @Autowired
    SearchCitiesService searchCitiesService;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    CityRepository cityRepository;

    public List<City> getReachableCities(int startCityId, double timeLimit) {

        val reachableCityIds = searchCitiesService.findReachableCityIds(startCityId, timeLimit);
        reachableCityIds.remove(new Integer(startCityId));
        return cityRepository.getCitiesById(reachableCityIds);
    }
}
