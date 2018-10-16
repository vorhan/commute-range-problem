package com.test.commuterangeproblem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GraphEdge {

    @Id
    @GeneratedValue
    private Integer id;
    private int cityIdFrom;
    int cityIdTo;
    double timeDistance;
}
