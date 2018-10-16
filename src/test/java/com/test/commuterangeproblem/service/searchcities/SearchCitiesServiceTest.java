package com.test.commuterangeproblem.service.searchcities;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.test.commuterangeproblem.model.City;
import com.test.commuterangeproblem.service.ReachableCitiesService;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackages = "com.test.commuterangeproblem.repository")
public class SearchCitiesServiceTest {

    @Autowired
    SearchCitiesService searchCitiesService;

    @Autowired
    ReachableCitiesService reachableCitiesService;

    @Test
    public void testSearchCitiesService() {
        val result = searchCitiesService.findReachableCityIds(9, 50);

        val expectedIDs = ImmutableSet.of(0, 1, 2, 4, 5, 9);

        assertEquals(expectedIDs, new HashSet<>(result));
    }

    @Test
    public void testReachableCities() {
        val result = reachableCitiesService.getReachableCities(9, 60);

        val expectedList = ImmutableList.of(
            "Washington",
            "New York",
            "Long Island",
            "Providence",
            "Hartford",
            "Hershey"
        );

        assertEquals(expectedList, result.stream().map(City::getName).collect(Collectors.toList()));
    }


}
