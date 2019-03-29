package com.epam.impl;

import java.util.*;

public class SearchAlgorithm {
    private final List<Node> nodesList;
    private final List<Edge> edgesList;
    private Set<Node> visitedNodes = new HashSet<>();
    private Set<Node> notVisitedNodes = new HashSet<>();
    private List<Node> pathNodes = new LinkedList<>();
    private Map<Node, Node> nodesTree = new HashMap<>();
    private Map<Node, Integer> distance = new HashMap<>();
    private Integer cost = 0;

    public SearchAlgorithm(Graph graph){
        this.nodesList = new ArrayList<>(graph.getNodes());
        this.edgesList = new ArrayList<>(graph.getEdges());
    }

    public List<Node> getPathNodes(){
        if (pathNodes == null) throw new NullPointerException("No path found");
        else return pathNodes;
    }

    public int getCost(){
        return cost;
    }

    public void algorithm(String start, String end){

        for(Node node : nodesList){
            if(node.getName().equals(start)){
                DijkstraAlgorithm(node);
                break;
            }
        }

        Node step = null;
        for(Node node : nodesList) {
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
            cost += getCost(pathNodes.get(i), pathNodes.get(++i));
        }

    }

    private void DijkstraAlgorithm(Node node){
        distance.put(node, 0);
        notVisitedNodes.add(node);
        while(notVisitedNodes.size() > 0){
            Node tmpNode = getMinimalNode(notVisitedNodes);
            visitedNodes.add(tmpNode);
            notVisitedNodes.remove(tmpNode);
            findMinimalDistances(tmpNode);
        }
    }

    private void findMinimalDistances(Node node){
        Set<Node> neighbours = getNeighbours(node);
        for (Node neighbour : neighbours){
            if (findDistance(neighbour) > ( findDistance(node) + getDistance(node, neighbour) ) ) {
                distance.put(node, findDistance(node) + getDistance(node, neighbour));
                nodesTree.put(neighbour, node);
                notVisitedNodes.add(neighbour);
            }
        }
    }

    private boolean isVisited(Node node){
        return visitedNodes.contains(node);
    }

    private int findDistance(Node targetPoint){
        Integer length = distance.get(targetPoint);
        if(length == null) return Integer.MAX_VALUE;
        else return length;
    }

    private Set<Node> getNeighbours(Node node){
        Set<Node> neighbours = new HashSet<>();
        for(Edge edge: edgesList){
            if(edge.getStartPoint().equals(node) && !isVisited(edge.getTargetPoint()) ){
                neighbours.add(edge.getTargetPoint());
            }
        }
        return neighbours;
    }

    private Node getMinimalNode(Set<Node> nodes){
        Node tmpNode = null;
        for(Node node: nodes){
            if(tmpNode == null) tmpNode = node;
            else if(findDistance(node) != Integer.MAX_VALUE && findDistance(node) == findDistance(tmpNode)) {
                throw new IllegalArgumentException("2 path with same length");
            }
            else if(findDistance(node) < findDistance(tmpNode)) tmpNode = node;
        }
        return tmpNode;
    }

    private int getDistance(Node start, Node end){
        for (Edge edge: edgesList){
            if (edge.getStartPoint().equals(start) && edge.getTargetPoint().equals(end)){
                return edge.getLength();
            }
        }
        throw new RuntimeException("unreachable statement");
    }

    private int getCost(Node start, Node end){
        for (Edge edge : edgesList){
            if (edge.getStartPoint().equals(start) && edge.getTargetPoint().equals(end)){
                return edge.getWeight();
            }
        }
        throw new RuntimeException("unreachable statement");
    }

}
