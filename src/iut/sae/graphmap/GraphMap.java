package iut.sae.graphmap;

import iut.sae.graphmap.graph.GraphVisualisationWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Program Entry Class
 * @author Jonathan MONTMAIN <jonathan.montmain at gmail.com>
 */
public class GraphMap {

    /**
     * Inits the whole program
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GraphMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.setProperty("org.graphstream.ui", "swing");
        System.setProperty("sun.java2d.opengl", "True");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        new GraphVisualisationWindow().setVisible(true);
    }
    
}
