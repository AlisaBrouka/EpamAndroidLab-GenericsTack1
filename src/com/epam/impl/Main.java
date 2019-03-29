package com.epam.impl;

import java.util.function.Function;

public class Main {
    public static void main(String args[]){
        try{
            Function<Edge<Node>, Integer> function = e -> e.getLength() * e.getWeight();
            Navigator nv = new Navigator(Node.class, function );
            nv.readData("src/com/epam/impl/roads.txt");
            nv.printGraph();
            System.out.println( nv.findPath("C", "Metro").toString() );
        }
        catch(NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        catch(Exception ex){

            ex.printStackTrace();
        }
    }
}
