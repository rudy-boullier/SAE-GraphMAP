/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iut.sae.graphmap.graph;

/**
 * Represents the stylesheets of a Graph
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class GraphStyles {
    
    /**
     * Returns the high-level full stylesheet
     * @return The full stylesheet
     */
    public static String getFullStyles() {
        StringBuilder sb = new StringBuilder();
        sb.append("graph { fill-color: #ffffff; }\n");
        sb.append("node { size: 10px, 10px; text-padding: 2; text-background-mode: rounded-box; text-alignment: at-right; }\n");
        sb.append("node.V { fill-color: #ff0000; text-background-color: #323232; text-color: #ffffff; }\n");
        sb.append("node.R { fill-color: #0000ff; }\n");
        sb.append("node.L { fill-color: #00ff00; }\n");
        sb.append("node:clicked { fill-color: #323232; }\n");
        sb.append("edge.A { fill-color: #ffff00; }\n");
        sb.append("edge.D { fill-color: #00ffff; }\n");
        sb.append("edge.N { fill-color: #ff00aa; }");
        return sb.toString();
    }
    
    /**
     * Toggle node titles visibility
     * @param b Node titles visibility
     * @return Corresponding stylesheet
     */
    public static String toggleNodesTitles(boolean b) {
        return "node { text-visibility-mode: " + (b ? "normal" : "hidden") + "; }";
    }
    
    /**
     * Toggle distances visibility
     * @param b Visibility
     * @return Corresponding stylesheet
     */
    public static String toggleDistances(boolean b) {
        return "edge { text-visibility-mode: " + (b ? "normal" : "hidden") + "; }";
    }
    
}
