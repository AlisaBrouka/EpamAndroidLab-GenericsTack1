package com.epam.impl;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Navigator <N extends Node> implements GpsNavigator {

    private Set<N> nodes = new HashSet<>();
    private Set<Edge<N>> edges = new HashSet<>();
    private Graph graph;
    private Class<N> clazz;
    private Function<Edge<N>, Integer> priceCount;

    public Navigator(Class<N> clazz, Function<Edge<N>, Integer> priceCount) {
        this.clazz = clazz;
        this.priceCount = priceCount;
    }

    @Override
    public void readData(String filePath){

        try(BufferedReader in = new BufferedReader(new FileReader(filePath))) {

            String line;
            LinkedList<String> fileStrings = new LinkedList<>();

            while ((line = in.readLine()) != null) {
                fileStrings.add(line);
            }

            String tmpLine = "";
            while (!fileStrings.isEmpty()) {

                tmpLine = fileStrings.pop();
                String parts[] = tmpLine.split(" ");

                nodes.add( clazz.getConstructor(String.class).newInstance(parts[0]) );
                nodes.add( clazz.getConstructor(String.class).newInstance(parts[1]));

                edges.add( new Edge( clazz.getConstructor(String.class).newInstance(parts[0]),  clazz.getConstructor(String.class).newInstance(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]) ) );
            }

            graph = new Graph(nodes, edges);


        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());

        }
        catch(IOException ex){
            graph = new Graph(new HashSet<>(), new HashSet<>());
            System.out.println(ex.getMessage());
        }
	catch(Exception ex){
		System.out.println(ex.getMessage());
	}

    }

    @Override
    public Path findPath(String pointA, String pointB){

                List<String> nodesNames = new LinkedList<>();
                SearchAlgorithm sh = new SearchAlgorithm(graph, priceCount);

                sh.algorithm(pointA, pointB);
                List<N> pathNodes = sh.getPathNodes();

                for (N node : pathNodes) {
                    nodesNames.add(node.getName());
                }

                return new Path(nodesNames, sh.getCost());
    }

    public void printGraph(){
        Set<N> nodes = graph.getNodes();
        Set<Edge<N>> edges = graph.getEdges();

        System.out.println("Data from file: ");

        System.out.println("1) Nodes");
        for(N node : nodes){
            System.out.print(node.getName() + " ");
        }

        System.out.println("\n2) Edges");
        for(Edge<N> edge : edges){
            System.out.println(edge.getStartPoint().getName() + " " + edge.getTargetPoint().getName());
        }
    }
}
