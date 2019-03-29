package com.epam.impl;

public class Main {
    public static void main(String args[]){
        try{
            Navigator nv = new Navigator();
            nv.readData("src/com/epam/impl/roads.txt");
            nv.printGraph();
        }
        catch(NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
