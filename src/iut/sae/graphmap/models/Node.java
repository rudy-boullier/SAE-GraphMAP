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
 * @author Jonathan MONTMAIN <jmontmain at gmail.com>
 */
public class Node {
    
    private static final List<Node> registeredNodes = new ArrayList<>();
    
    private final NodeType type;
    private final String name;
    
    public Node(NodeType type, String name) {
        this.type = type;
        this.name = name;
        populateRegister();
    }
    
    private void populateRegister() {
        if (!registeredNodes.contains(this)) registeredNodes.add(this);
    }
    
    public org.graphstream.graph.Node populateGraph(Graph g) {
        org.graphstream.graph.Node node = g.addNode(toString());
        node.setAttribute("ui.label", name);
        node.setAttribute("ui.class", type.toString());
        return node;
    }
    
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
    
    public static List<Node> getRegisteredNodes() {
        return registeredNodes;
    }
    
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (Edge edge : Edge.getRegisteredEdges()) {
            if (Node.findNode(edge.getLeftNode()) == this || Node.findNode(edge.getRightNode()) == this) {
                edges.add(edge);
            }
        }
        return edges;
    }
    
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
    
    public NodeType getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
    public enum NodeType {
        V,
        L,
        R;
    }
    
    @Override
    public String toString() {
        return type + "," + name;
    }
    
}
