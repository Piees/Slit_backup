/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Arild
 */
public class TabFagstoff {
    
    public TabFagstoff()    {
        
    }
    /**
     * Dette er taben for fagstoff. Foreløpig er den helt tom.
     * @return JPanel tab3Panel returnerer panel med innholdet i tab 3
     */
    public JPanel makeFagstoff()    {
        JPanel tab3Panel = new JPanel();
        JTextField textField = new JTextField("Her kommer alt fagstoff.");
        tab3Panel.add(textField);
        return tab3Panel;
    }    
    
}
