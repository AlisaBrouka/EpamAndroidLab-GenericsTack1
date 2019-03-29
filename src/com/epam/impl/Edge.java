package com.epam.impl;

import java.util.Objects;

class Edge {
    private final Node startPoint;
    private final Node endPoint;
    private final Integer length;
    private final Integer weight;

    public Edge(Node startPoint, Node endPoint, Integer length, Integer weight){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = length;
        this.weight = weight;
    }

    public Node getStartPoint() {
        return startPoint;
    }

    public Node getTargetPoint() {
        return endPoint;
    }

    public Integer getLength() {
        return length;
    }

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
