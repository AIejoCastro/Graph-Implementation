package model;

import java.util.*;

public class Graph<T> {

    private Map<T, List<T>> map = new HashMap<>();

    public void addVertex(T s) {
        map.put(s, new LinkedList<T>());
    }

    public void addEdge(T data, T destination, boolean bidirectional) {
        if (!map.containsKey(data))
            addVertex(data);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(data).add(destination);
        if (bidirectional == true) {
            map.get(destination).add(data);
        }
    }

    public int getVertexCount() {
        return map.keySet().size();
    }

    public int getEdgesCount(boolean bidirectional) {
        int count = 0;
        for (T v : map.keySet()) {
            count += map.get(v).size();
        }
        if (bidirectional == true) {
            count = count / 2;
        }
        return count;
    }

    public String BFS(T start) {
        Set<T> visited = new HashSet<>();

        Queue<T> queue = new LinkedList<>();

        String msg = "";
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            msg += vertex + " ";

            List<T> neighbors = map.get(vertex);

            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return msg;
    }

    public String DFS(T start) {
        Set<T> visited = new HashSet<>();

        return DFSHelper(start, visited);
    }

    private String DFSHelper(T vertex, Set<T> visited) {

        String msg = "";

        visited.add(vertex);
        msg += vertex + " ";

        List<T> neighbors = map.get(vertex);

        for (T neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                DFSHelper(neighbor, visited);
            }
        }

        return msg;
    }
}