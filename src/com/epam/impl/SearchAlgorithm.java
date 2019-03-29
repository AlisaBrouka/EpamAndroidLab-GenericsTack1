package com.epam.impl;

import java.util.*;
import java.util.function.Function;

public class SearchAlgorithm <N extends Node> {
    private final List<N> nodesList;
    private final List<Edge<N>> edgesList;
    private Set<N> visitedNodes = new HashSet<>();
    private Set<N> notVisitedNodes = new HashSet<>();
    private List<N> pathNodes = new LinkedList<>();
    private Map<N, N> nodesTree = new HashMap<>();
    private Map<N, Integer> distance = new HashMap<>();
    private Integer cost = 0;
    private Function<Edge<N>, Integer> priceCount;

    public SearchAlgorithm(Graph graph, Function<Edge<N>, Integer> priceCount){
        this.nodesList = new ArrayList<>(graph.getNodes());
        this.edgesList = new ArrayList<>(graph.getEdges());
        this.priceCount = priceCount;
    }

    public List<N> getPathNodes(){
        if (pathNodes == null) throw new NullPointerException("No path found");
        else return pathNodes;
    }

    public int getCost(){
        return cost;
    }

    public void algorithm(String start, String end){

        for(N node : nodesList){
            if(node.getName().equals(start)){
                DijkstraAlgorithm(node);
                break;
            }
        }

        N step = null;
        for(N node : nodesList) {
            if (node.getName().equals(end)){
                step = node;
                break;
            }
        }

       if(step == null) throw new NullPointerException("No such node found");

        pathNodes.add(step);
        while(nodesTree.get(step) != null){
            step = nodesTree.get(step);
            pathNodes.add(step);
        }

        Collections.reverse(pathNodes);

        int i = 0;
        while(i < pathNodes.size()-1){
            cost += getCost( pathNodes.get(i), pathNodes.get(++i) );
        }

    }

    private void DijkstraAlgorithm(N node){
        distance.put(node, 0);
        notVisitedNodes.add(node);
        while(notVisitedNodes.size() > 0){
            N tmpNode = getMinimalNode(notVisitedNodes);
            visitedNodes.add(tmpNode);
            notVisitedNodes.remove(tmpNode);
            findMinimalDistances(tmpNode);
        }
    }

    private void findMinimalDistances(N node){
        Set<N> neighbours = getNeighbours(node);
        for (N neighbour : neighbours){
            if (findDistance(neighbour) > ( findDistance(node) + getDistance(node, neighbour) ) ) {
                distance.put(node, findDistance(node) + getDistance(node, neighbour));
                nodesTree.put(neighbour, node);
                notVisitedNodes.add(neighbour);
            }
        }
    }

    private boolean isVisited(N node){
        return visitedNodes.contains(node);
    }

    private int findDistance(N targetPoint){
        Integer length = distance.get(targetPoint);
        if(length == null) return Integer.MAX_VALUE;
        else return length;
    }

    private Set<N> getNeighbours(N node){
        Set<N> neighbours = new HashSet<>();
        for(Edge<N> edge: edgesList){
            if(edge.getStartPoint().equals(node) && !isVisited(edge.getTargetPoint()) ){
                neighbours.add(edge.getTargetPoint());
            }
        }
        return neighbours;
    }

    private N getMinimalNode(Set<N> nodes){
        N tmpNode = null;
        for(N node: nodes){
            if(tmpNode == null) tmpNode = node;
            else if(findDistance(node) != Integer.MAX_VALUE && findDistance(node) == findDistance(tmpNode)) {
                throw new IllegalArgumentException("2 path with same length");
            }
            else if(findDistance(node) < findDistance(tmpNode)) tmpNode = node;
        }
        return tmpNode;
    }

    private int getDistance(N start, N end){
        for (Edge<N> edge: edgesList){
            if (edge.getStartPoint().equals(start) && edge.getTargetPoint().equals(end)){
                return edge.getLength();
            }
        }
        throw new RuntimeException("unreachable statement");
    }

    private int getCost(N start, N end){
        for (Edge<N> edge : edgesList){
            if (edge.getStartPoint().equals(start) && edge.getTargetPoint().equals(end)){
                return priceCount.apply(edge);
            }
        }
        throw new RuntimeException("unreachable statement");
    }

}
