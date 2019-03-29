package com.epam.impl;

import java.util.Set;

class Graph <N extends Node> implements GraphInterface{
    private final Set<N> nodes;
    private final Set<Edge<N>> edges;

    public Graph(Set<N> nodes, Set<Edge<N>> edges){
        this.nodes = nodes;
        this.edges = edges;
    }

    @Override
    public Set<Edge<N>> getEdges(){
        return edges;
    }

    @Override
    public Set<N> getNodes(){
        return nodes;
    }
}
