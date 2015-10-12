/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Arild-Bærbar
 */
public class TabFagstoff {
    
    public TabFagstoff()    {
        
    }
    public JPanel makeFagstoff()    {
        JPanel tab3Panel = new JPanel();
        JTextField textField = new JTextField("Her kommer alt fagstoff.");
        tab3Panel.add(textField);
        return tab3Panel;
    }    
    
}
