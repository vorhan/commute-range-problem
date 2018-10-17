package com.test.commuterangeproblem.repository;

import com.test.commuterangeproblem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("select c from City c where c.id in (:cityIds) ")
    public List<City> getCitiesById(@Param("cityIds") List<Integer> cityIds);
}
