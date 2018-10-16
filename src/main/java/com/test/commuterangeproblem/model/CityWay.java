package com.test.commuterangeproblem.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CityWay implements Comparable<CityWay> {

    private int cityId;
    private double timeDistance;

    @Override
    public int compareTo(CityWay p) {
        return timeDistance < p.timeDistance
            ? -1
            : timeDistance == p.timeDistance
                ? cityId - p.cityId
                : 1;
    }
}
