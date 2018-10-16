package com.test.commuterangeproblem.service.searchcities;

import java.util.List;

public interface SearchCitiesService {

    List<Integer> findReachableCityIds(int startCityId, double timeLimit);
}
