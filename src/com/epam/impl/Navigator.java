package com.epam.impl;

import java.io.*;
import java.util.*;

public class Navigator implements GpsNavigator {

    private Set<Node> nodes = new HashSet<>();
    private Set<Edge> edges = new HashSet<>();
    private Graph graph;

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

                nodes.add(new Node(parts[0]));
                nodes.add(new Node(parts[1]));

                edges.add( new Edge( new Node(parts[0]), new Node(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]) ) );
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
                SearchAlgorithm sh = new SearchAlgorithm(graph);

                sh.algorithm(pointA, pointB);
                List<Node> pathNodes = sh.getPathNodes();

                for (Node node : pathNodes) {
                    nodesNames.add(node.getName());
                }

                return new Path(nodesNames, sh.getCost());
    }

    public void printGraph(){
        Set<Node> nodes = graph.getNodes();
        Set<Edge> edges = graph.getEdges();

        System.out.println("Data from file: ");

        System.out.println("1) Nodes");
        for(Node node : nodes){
            System.out.print(node.getName() + " ");
        }

        System.out.println("\n2) Edges");
        for(Edge edge : edges){
            System.out.println(edge.getStartPoint().getName() + " " + edge.getTargetPoint().getName());
        }
    }
}
