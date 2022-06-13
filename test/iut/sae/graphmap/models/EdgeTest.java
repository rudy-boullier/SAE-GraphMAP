/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package iut.sae.graphmap.models;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Represents the tests of Edge
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class EdgeTest {
    
    /**
     * Clears the registers of Node and Edge
     */
    @Before
    public void clearRegisters() {
        Node.getRegisteredNodes().clear();
        Edge.getRegisteredEdges().clear();
    }

    /**
     * Test of populateGraph method, of class Edge
     */
    @Test
    public void testPopulateGraph() {
        Graph g = new MultiGraph("test");
        Node a = new Node(Node.NodeType.V, "A");
        Node b = new Node(Node.NodeType.L, "B");
        a.populateGraph(g);
        b.populateGraph(g);
        Edge instance = new Edge(Edge.EdgeType.N, a.toString(), b.toString(), 250);
        instance.populateGraph(g);
        assertEquals(250f, g.getEdge(0).getAttribute("length"));
    }
    
    /**
     * Test of populateRegister method, of class Edge
     */
    @Test
    public void testPopulateRegister() {
        Node a = new Node(Node.NodeType.V, "A");
        Node b = new Node(Node.NodeType.L, "B");
        Edge instance = new Edge(Edge.EdgeType.N, a.toString(), b.toString(), 250);
        assertEquals(instance, Edge.getRegisteredEdges().get(0));
    }
    
    /**
     * Test of toString method, of class Edge
     */
    @Test
    public void testToString() {
        Node a = new Node(Node.NodeType.V, "A");
        Node b = new Node(Node.NodeType.L, "B");
        Edge instance = new Edge(Edge.EdgeType.N, a.toString(), b.toString(), 250);
        assertEquals(instance.toString(), "N,V,A,L,B");
    }
    
}
