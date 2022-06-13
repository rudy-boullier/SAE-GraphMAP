/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iut.sae.graphmap.models;

import java.util.ArrayList;
import java.util.List;
import org.graphstream.graph.Graph;

/**
 * Represents a Node
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class Node {
    
    // Registered nodes
    private static final List<Node> registeredNodes = new ArrayList<>();
    
    // Node type
    private final NodeType type;
    
    // Node name
    private final String name;
    
    /**
     * Instanciates a new Node
     * @param type Node type
     * @param name Node name
     */
    public Node(NodeType type, String name) {
        this.type = type;
        this.name = name;
        populateRegister();
    }
    
    /**
     * Populates the register with the current Node
     */
    private void populateRegister() {
        if (!registeredNodes.contains(this)) registeredNodes.add(this);
    }
    
    /**
     * Populates the graph
     * @param g Graph
     * @return Graphstream's Node
     */
    public org.graphstream.graph.Node populateGraph(Graph g) {
        org.graphstream.graph.Node node = g.addNode(toString());
        node.setAttribute("ui.label", name);
        node.setAttribute("ui.class", type.toString());
        return node;
    }
    
    /**
     * Finds corresponding Node
     * @param stringRepresentation String representation of the node
     * @return Found Node
     */
    public static Node findNode(String stringRepresentation) {
        Node node = null;
        for (Node fetchedNode : registeredNodes) {
            if (stringRepresentation.charAt(0) == fetchedNode.type.toString().charAt(0) && stringRepresentation.substring(2).strip().equals(fetchedNode.name)) {
                node = fetchedNode;
                break;
            }
        }
        if (node == null) {
            for (Node fetchedNode : registeredNodes) {
                if (fetchedNode.name.equals(stringRepresentation)) {
                    node = fetchedNode;
                    break;
                }
            }
        }
        return node;
    }
    
    /**
     * Returns a list of all registered Nodes
     * @return List of all registered Nodes
     */
    public static List<Node> getRegisteredNodes() {
        return registeredNodes;
    }
    
    /**
     * Returns a list of all the edges of the current Node
     * @return List of all the edges of the current Node
     */
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (Edge edge : Edge.getRegisteredEdges()) {
            if (Node.findNode(edge.getLeftNode()) == this || Node.findNode(edge.getRightNode()) == this) {
                edges.add(edge);
            }
        }
        return edges;
    }
    
    /**
     * Returns a boolean, is true if a Node is two-distance accessible from the current Node, false otherwise
     * @param to Destination Node
     * @return True if a Node is two-distance accessible from the current Node, false otherwise
     */
    public boolean isTwoDistanceAccessible(Node to) {
        List<Edge> fromEdges = getEdges();
        List<Edge> toEdges = to.getEdges();
        
        for (Edge fromEdge : fromEdges) {
            for (Edge toEdge : toEdges) {
                Node leftFromNode = Node.findNode(fromEdge.getLeftNode());
                Node rightFromNode = Node.findNode(fromEdge.getLeftNode());
                Node leftToNode = Node.findNode(toEdge.getLeftNode());
                Node rightToNode = Node.findNode(toEdge.getRightNode());
                Node fromOtherNode = leftFromNode == this ? rightFromNode : leftFromNode;
                Node toOtherNode = leftToNode == to ? rightToNode : leftToNode;
                if (fromOtherNode == to) return true;
                if (toOtherNode == this) return true;
                if (fromOtherNode == toOtherNode) return true;
            }
        }
        
        return false;
    }
    
    /**
     * Returns the node type
     * @return Node type
     */
    public NodeType getType() {
        return type;
    }
    
    /**
     * Returns the edge type
     * @return Edge type
     */
    public String getName() {
        return name;
    }
    
    /**
     * Enumeration of Node types
     */
    public enum NodeType {
        V,
        L,
        R;
    }
    
    /**
     * Returns a String representation of the Node
     * @return String representation of the node
     */
    @Override
    public String toString() {
        return type + "," + name;
    }
    
}
