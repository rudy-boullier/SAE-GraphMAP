/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iut.sae.graphmap.models;

import java.io.File;
import javax.swing.DefaultListModel;

/**
 * Represents the recent files List Model
 * @author Jonathan MONTMAIN, Rudy BOULLIER
 */
public class RecentFilesListModel extends DefaultListModel<String> {

    /**
     * Returns formatted name at a given index
     * @param index Index of the file in the list
     * @return Formatted name
     */
    @Override
    public String getElementAt(int index) {
        return new File(super.getElementAt(index)).getName() + " - " + super.getElementAt(index);
    }
    
    /**
     * Returns unformatted name at a given index
     * @param index Index of the file in the list
     * @return Unformatted name
     */
    public String getRawElementAt(int index) {
        return super.getElementAt(index);
    }
    
}
