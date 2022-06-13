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
 * Represents the tests of Node
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class NodeTest {
    
    @Before
    public void clearRegisters() {
        Node.getRegisteredNodes().clear();
        Edge.getRegisteredEdges().clear();
    }

    /**
     * Test of populateGraph method, of class Node.
     */
    @Test
    public void testPopulateGraph() {
        Graph g = new MultiGraph("test");
        Node a = new Node(Node.NodeType.V, "A");
        a.populateGraph(g);
        assertEquals(a.toString(), g.getNode(0).toString());
    }

    /**
     * Test of findNode method, of class Node.
     */
    @Test
    public void testFindNode() {
        Node a = new Node(Node.NodeType.V, "A");
        Node b = Node.findNode(a.toString());
        assertEquals(a, b);
    }

    /**
     * Test of isTwoDistanceAccessible method, of class Node.
     */
    @Test
    public void testIsTwoDistanceAccessible() {
        Node a = new Node(Node.NodeType.V, "A");
        Node b = new Node(Node.NodeType.L, "B");
        Node c = new Node(Node.NodeType.R, "C");
        new Edge(Edge.EdgeType.N, a.toString(), b.toString(), 250);
        new Edge(Edge.EdgeType.A, a.toString(), c.toString(), 120);
        boolean result = a.isTwoDistanceAccessible(c);
        assertEquals(true, result);
    }

    /**
     * Test of toString method, of class Node.
     */
    @Test
    public void testToString() {
        Node a = new Node(Node.NodeType.V, "A"); 
        assertEquals("V,A", a.toString());
    }
    
}
