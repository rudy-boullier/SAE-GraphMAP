/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package iut.sae.graphmap.models;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Represents the tests of Path
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class PathTest {
    
    @Before
    public void clearRegisters() {
        Node.getRegisteredNodes().clear();
        Edge.getRegisteredEdges().clear();
    }
    
    /**
     * Test of findShortestPath method, of class Path.
     */
    @Test
    public void testFindShortestPath() {
        Graph g = new MultiGraph("test");
        Node a = new Node(Node.NodeType.V, "A");
        Node b = new Node(Node.NodeType.L, "B");
        a.populateGraph(g);
        b.populateGraph(g);
        Edge ab = new Edge(Edge.EdgeType.A, a.toString(), b.toString(), 250);
        ab.populateGraph(g);
        assertEquals(Path.findShortestPath(g, a, b).getFrom(), a);
        assertEquals(Path.findShortestPath(g, a, b).getTo(), b);
    }
    
    /**
     * Test of getDistance method, of class Path.
     */
    @Test
    public void testGetDistance() {
        Graph g = new MultiGraph("test");
        Node a = new Node(Node.NodeType.V, "A");
        Node b = new Node(Node.NodeType.L, "B");
        a.populateGraph(g);
        b.populateGraph(g);
        Edge ab = new Edge(Edge.EdgeType.A, a.toString(), b.toString(), 250);
        ab.populateGraph(g);
        Path path = Path.findShortestPath(g, a, b);
        assertEquals(path.getDistance(), 250f, 0.1f);
    }
    
}
