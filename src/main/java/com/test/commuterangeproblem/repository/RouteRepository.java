package com.test.commuterangeproblem.repository;

import com.test.commuterangeproblem.model.GraphEdge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RouteRepository extends JpaRepository<GraphEdge, Integer> {

    @Query("select a from GraphEdge a where ((a.cityIdFrom= :cityId AND a.cityIdTo NOT IN :passedCityIds) OR (a.cityIdTo= :cityId AND a.cityIdFrom NOT IN :passedCityIds)) AND a.timeDistance<= :timeLimit")
    public Set<GraphEdge> findNeighbourCities(@Param("cityId") Integer cityId, @Param("passedCityIds") List<Integer> passedCityIds, @Param("timeLimit") double timeLimit);

}
