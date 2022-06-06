/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iut.sae.graphmap.models;

import java.util.ArrayList;
import java.util.List;
import org.graphstream.graph.Graph;

/**
 * Represents an Edge
 * @author Jonathan MONTMAIN <jmontmain at gmail.com>
 */
public class Edge {
    
    /**
     * The registered edges that are in the graph
     */
    private static final List<Edge> registeredEdges = new ArrayList<>();
    
    /**
     * The Edge type
     */
    private final EdgeType type;
    
    /**
     * The left Node
     */
    private final String leftNode;
    
    /**
     * The right Node
     */
    private final String rightNode;
    
    /**
     * The length of the Edge
     */
    private final float length;
    
    /**
     * Creates a new Edge
     * @param type Type
     * @param leftNode Left Node
     * @param rightNode Right Node
     * @param length Length
     */
    public Edge(EdgeType type, String leftNode, String rightNode, float length) {
        this.type = type;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.length = length;
        populateRegister();
    }
    
    /**
     * Saves the edge in the register
     */
    private void populateRegister() {
        if (!registeredEdges.contains(this)) registeredEdges.add(this);
    }
    
    /**
     * Returns a graphstream representation of the Edge
     * @param g Graph
     * @return graphstream's Edge
     */
    public org.graphstream.graph.Edge populateGraph(Graph g) {
        org.graphstream.graph.Edge edge = g.addEdge(toString(), leftNode, rightNode);
        edge.setAttribute("ui.class", type.toString());
        edge.setAttribute("length", length);
        return edge;
    }
    
    /**
     * Returns a list of all registered Edges
     * @return List of all registered Edges
     */
    public static List<Edge> getRegisteredEdges() {
        return registeredEdges;
    }
    
    /**
     * Returns the type of the Edge
     * @return Type
     */
    public EdgeType getType() {
        return type;
    }
    
    /**
     * Returns the left Node
     * @return Left Node
     */
    public String getLeftNode() {
        return leftNode;
    }
    
    /**
     * Returns the right Node
     * @return Right Node
     */
    public String getRightNode() {
        return rightNode;
    }
    
    /**
     * Returns the length
     * @return Length
     */
    public float getLength() {
        return length;
    }
    
    /**
     * Edge Type
     */
    public enum EdgeType {
        N,
        D,
        A;
    }
    
    /**
     * Returns a String representation of the Edge
     * @return 
     */
    @Override
    public String toString() {
        return type + "," + leftNode + "," + rightNode;
    }
}
