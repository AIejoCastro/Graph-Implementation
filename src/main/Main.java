package main;

import model.Graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B", 5, true);
        graph.addEdge("A", "C", 2, true);
        graph.addEdge("B", "D", 4, true);
        graph.addEdge("C", "D", 1, true);
        graph.addEdge("D", "E", 6, true);

        Map<String, Object> result = graph.findShortestPathDijkstra("A", "E");
        int distance = (int) result.get("distance");
        List<String> path = (List<String>) result.get("path");

        System.out.println("Shortest distance: " + distance);
        System.out.println("Shortest path: " + path);

    }
}