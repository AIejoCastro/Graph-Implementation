import model.Graph;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


public class GraphTest {

    @Test
    public void addNewVertex() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        assertEquals(1, graph.getVertexCount());
    }

    @Test
    public void addMoreVertex() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        assertEquals(4, graph.getVertexCount());
    }

    @Test
    public void duplicatedVertex() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("A");
        assertEquals(3, graph.getVertexCount());
    }

    @Test
    public void newDirectedEdge() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", false);
        assertEquals(1, graph.getEdgesCount(false));
    }

    @Test
    public void newBidirectionalEdge() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, true);
        graph.addEdge(3, 1, true);
        assertEquals(2, graph.getEdgesCount(true));
    }

    @Test
    public void BFSSimpleDirectedGraph() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", false);
        graph.addEdge("B", "C", false);
        graph.addEdge("C", "D", false);
        graph.addEdge("D", "A", false);
        assertEquals("A B C D ", graph.BFS("A"));
    }

    @Test
    public void BFSUndirectedGraph() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(3, 4, true);
        graph.addEdge(4, 1, true);
        assertEquals("1 2 4 3 ", graph.BFS(1));
    }

    @Test
    public void BFSDisconnectedGraph() {
        Graph<Character> graph = new Graph<>();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addEdge('A', 'B', false);
        graph.addEdge('B', 'C', false);
        graph.addEdge('C', 'D', false);
        assertEquals("E ", graph.BFS('E'));
    }

    @Test
    public void DFSSimpleDirectedGraph() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", false);
        graph.addEdge("B", "C", false);
        graph.addEdge("C", "D", false);
        graph.addEdge("D", "A", false);
        assertEquals("A ", graph.DFS("A"));
    }

    @Test
    public void DFSSimpleUndirectedGraph() {
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2, true);
        graph.addEdge(2, 3, true);
        graph.addEdge(3, 4, true);
        graph.addEdge(4, 1, true);
        assertEquals("1 ", graph.DFS(1));
    }

    @Test
    public void DFSDisconnectedGraph() {
        Graph<Character> graph = new Graph<>();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addEdge('A', 'B', false);
        graph.addEdge('B', 'C', false);
        graph.addEdge('C', 'D', false);
        assertEquals("E ", graph.DFS('E'));
    }
}