package com.epam.impl;

import java.util.Set;

class Graph {
    private final Set<Node> nodes;
    private final Set<Edge> edges;

    public Graph(Set<Node> nodes, Set<Edge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    public Set<Edge> getEdges(){
        return edges;
    }

    public Set<Node> getNodes(){
        return nodes;
    }
}
