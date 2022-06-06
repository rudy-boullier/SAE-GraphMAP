/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iut.sae.graphmap.models;

import java.util.ArrayList;
import java.util.List;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;

/**
 * Represents a Path
 * @author Jonathan MONTMAIN <jmontmain at gmail.com>
 */
public class Path {
    
    private final Node from;
    private final Node to;
    private final List<Edge> edges;
    
    public Path(Node from, Node to, List<Edge> edges) {
        this.from = from;
        this.to = to;
        this.edges = edges;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }
    
    public float getDistance() {
        int sumOfDistances = 0;
        if (edges.isEmpty()) return -1f;
        for (Edge edge : edges) {
            float distance = edge.getLength();
            if (distance == -1f) return -1f;
            sumOfDistances += distance;
        }
        return sumOfDistances;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean initialized = false;
        Node previousNode = null;
        for (Edge edge : edges) {
            Node leftNode = Node.findNode(edge.getLeftNode());
            Node rightNode = Node.findNode(edge.getRightNode());
            if (!initialized) {
                previousNode = from;
                initialized = true;
                sb.append(from.getName());
            }
            sb.append(" - ");
            if (leftNode == previousNode) {
                previousNode = rightNode;
                sb.append(rightNode.getName());
            } else {
                previousNode = leftNode;
                sb.append(leftNode.getName());
            }
        }
        return sb.toString();
    }
    
    public static Path findShortestPath(Graph g, Node from, Node to) {
        Dijkstra algo = new Dijkstra(Dijkstra.Element.EDGE, null, "length");
        algo.init(g);
        algo.setSource(g.getNode(from.toString()));
        algo.compute();
        List<Edge> edges = new ArrayList<>();
        Path path = new Path(from, to, edges);
        org.graphstream.graph.Path graphPath = algo.getPath(g.getNode(to.toString()));
        for (org.graphstream.graph.Edge edge : graphPath.getEdgePath()) {
            Edge foundEdge = null;
            for (Edge fetchedEdge : Edge.getRegisteredEdges()) {
                if (
                    (g.getNode(fetchedEdge.getLeftNode()) == edge.getNode0() && g.getNode(fetchedEdge.getRightNode()) == edge.getNode1())
                    || (g.getNode(fetchedEdge.getRightNode()) == edge.getNode0() && g.getNode(fetchedEdge.getLeftNode()) == edge.getNode1())
                ) {
                    foundEdge = fetchedEdge;
                    break;
                }
            }
            if (foundEdge != null) edges.add(foundEdge);
        }
        return path;
    }
    
}
