package com.epam.impl;

import java.util.Objects;

class Edge <N extends Node> implements EdgeInterface{
    private final N startPoint;
    private final N endPoint;
    private final Integer length;
    private final Integer weight;

    public Edge(N startPoint, N endPoint, Integer length, Integer weight){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = length;
        this.weight = weight;
    }

    @Override
    public N getStartPoint() {
        return startPoint;
    }

    @Override
    public N getTargetPoint() {
        return endPoint;
    }

    @Override
    public Integer getLength() {
        return length;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(startPoint, edge.startPoint) &&
                Objects.equals(endPoint, edge.endPoint) &&
                Objects.equals(length, edge.length) &&
                Objects.equals(weight, edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, length, weight);
    }
}
