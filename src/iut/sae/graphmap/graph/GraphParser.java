/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iut.sae.graphmap.graph;

import iut.sae.graphmap.models.Edge;
import iut.sae.graphmap.models.Edge.EdgeType;
import iut.sae.graphmap.models.Node;
import iut.sae.graphmap.models.Node.NodeType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import org.graphstream.graph.Graph;

/**
 * Represents the parser that will parse a file into a graph
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class GraphParser {
    
    /**
     * Parses a given File into a graph
     * @param file File to parse
     * @param g Graph to build
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void parse(File file, Graph g) throws FileNotFoundException, IOException {
        Edge.getRegisteredEdges().clear();
        Node.getRegisteredNodes().clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            Set<String> alreadyParsedSet = new HashSet<>();
            while ((line = reader.readLine()) != null) {
                while (line.length() > 0 && (line.charAt(0) == '"' || line.charAt(0) == '\r' || line.charAt(0) == '\n')) line = line.substring(1);
                if (line.length() > 0 && line.charAt(line.length() - 1) == '"') line = line.substring(0, line.length() - 1);
                String[] arguments = line.split(":", 2);
                String node = arguments[0];
                String rest = null;
                if (arguments.length >= 2) {
                    rest = arguments[1];
                }
                if (!alreadyParsedSet.contains(node)) {
                    char firstCharacter = node.charAt(0);
                    NodeType type = firstCharacter == 'R' ? NodeType.R : firstCharacter == 'L' ? NodeType.L : NodeType.V;
                    Node createdNode = new Node(type, node.substring(2).strip());
                    createdNode.populateGraph(g);
                }
                if (rest != null) {
                    String[] links = rest.split(";");
                    for (String link : links) {
                        String[] linkConfiguration = link.split("::", 2);
                        if (alreadyParsedSet.contains(linkConfiguration[1])) {
                            char firstCharacter = linkConfiguration[0].charAt(0);
                            EdgeType type = firstCharacter == 'A' ? EdgeType.A : firstCharacter == 'D' ? EdgeType.D : EdgeType.N;
                            float distance = Float.parseFloat(linkConfiguration[0].split(",", 2)[1]);
                            Edge createdEdge = new Edge(type, node, linkConfiguration[1], distance);
                            createdEdge.populateGraph(g);
                        }
                    }
                }
                if (!alreadyParsedSet.contains(node)) alreadyParsedSet.add(node);
            }
        }
    }
    
}
