package com.test.commuterangeproblem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
public class GraphEdge {

    @Id
    @GeneratedValue
    private Integer id;
    private int cityIdFrom;
    int cityIdTo;
    double timeDistance;
}
