/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iut.sae.graphmap.models;

import java.io.File;
import javax.swing.DefaultListModel;

/**
 *
 * @author Jonathan MONTMAIN <jmontmain at gmail.com>
 */
public class RecentFilesListModel extends DefaultListModel<String> {

    @Override
    public String getElementAt(int index) {
        return new File(super.getElementAt(index)).getName() + " - " + super.getElementAt(index);
    }
    
    public String getRawElementAt(int index) {
        return super.getElementAt(index);
    }
    
}
