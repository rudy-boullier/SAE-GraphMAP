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
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class Path {
    
    // Source node
    private final Node from;
    
    // Destination node
    private final Node to;
    
    // Edges of the path
    private final List<Edge> edges;
    
    /**
     * Instanciates a new Path
     * @param from Source node
     * @param to Destination node
     * @param edges Edges of the path
     */
    public Path(Node from, Node to, List<Edge> edges) {
        this.from = from;
        this.to = to;
        this.edges = edges;
    }

    /**
     * Returns the source node
     * @return Source node
     */
    public Node getFrom() {
        return from;
    }

    /**
     * Returns the destination node
     * @return Destination node
     */
    public Node getTo() {
        return to;
    }

    /**
     * Returns the edges of the path
     * @return Edges of the path
     */
    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }
    
    /**
     * Computes and returns the distance
     * @return Distance
     */
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
    
    /**
     * Returns a String representation of the Path
     * @return String representation of the path
     */
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
    
    /**
     * Returns the shortest-corresponding path
     * @param g Graph
     * @param from Source node
     * @param to Destination node
     * @return Found path
     */
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
