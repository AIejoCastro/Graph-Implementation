package model;

import java.util.*;

public class Graph<T> {

    private Map<T, List<Edge<T>>> map = new HashMap<>();

    public void addVertex(T vertex) {
        map.put(vertex, new LinkedList<Edge<T>>());
    }

    public void addEdge(T source, T destination, int weight, boolean bidirectional) {
        if (!map.containsKey(source))
            addVertex(source);

        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(new Edge<>(destination, weight));
        if (bidirectional) {
            map.get(destination).add(new Edge<>(source, weight));
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

    public String bfs(T start) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        String msg = "";
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            msg += current + " ";

            List<Edge<T>> neighbors = map.get(current);
            for (Edge<T> neighbor : neighbors) {
                T neighborVertex = neighbor.vertex;
                if (!visited.contains(neighborVertex)) {
                    visited.add(neighborVertex);
                    queue.offer(neighborVertex);
                }
            }
        }

        return msg;
    }

    public String dfs(T start) {
        Set<T> visited = new HashSet<>();
        return dfsHelper(start, visited);
    }

    private String dfsHelper(T current, Set<T> visited) {
        String msg = "";

        visited.add(current);
        msg += current + " ";

        List<Edge<T>> neighbors = map.get(current);
        for (Edge<T> neighbor : neighbors) {
            T neighborVertex = neighbor.vertex;
            if (!visited.contains(neighborVertex)) {
                dfsHelper(neighborVertex, visited);
            }
        }

        return msg;
    }


    public Map<String, Object> Dijkstra(T start, T end) {
        PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<>();
        Map<T, Integer> distances = new HashMap<>();
        Map<T, T> previous = new HashMap<>();

        // Initialize distances with infinity
        for (T vertex : map.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }

        // Set distance of start vertex to 0
        distances.put(start, 0);

        // Add start vertex to the priority queue
        priorityQueue.offer(new Node<>(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node<T> current = priorityQueue.poll();
            T currentVertex = current.vertex;
            int currentDistance = current.distance;

            // Skip if the current distance is already greater than the recorded distance
            if (currentDistance > distances.get(currentVertex)) {
                continue;
            }

            List<Edge<T>> neighbors = map.get(currentVertex);

            for (Edge<T> neighbor : neighbors) {
                T neighborVertex = neighbor.vertex;
                int neighborDistance = neighbor.weight;

                int totalDistance = currentDistance + neighborDistance;

                // Update the distance and previous vertex if a shorter path is found
                if (totalDistance < distances.get(neighborVertex)) {
                    distances.put(neighborVertex, totalDistance);
                    previous.put(neighborVertex, currentVertex);
                    priorityQueue.offer(new Node<>(neighborVertex, totalDistance));
                }
            }
        }

        List<T> path = new ArrayList<>();
        T current = end;
        while (current != null) {
            path.add(current);
            current = previous.get(current);
        }
        Collections.reverse(path);

        // Return the shortest distance and path
        Map<String, Object> result = new HashMap<>();
        result.put("distance", distances.get(end));
        result.put("path", path);

        return result;
    }

    private static class Node<T> implements Comparable<Node<T>> {
        private T vertex;
        private int distance;

        public Node(T vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node<T> other) {
            return Integer.compare(distance, other.distance);
        }
    }

    private static class Edge<T> {
        private T vertex;
        private int weight;

        public Edge(T vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
