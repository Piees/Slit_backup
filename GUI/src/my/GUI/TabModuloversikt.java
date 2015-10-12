/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.GUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Arild-Bærbar
 */
public class TabModuloversikt {
    
    public TabModuloversikt()   {
        
    }
    public JPanel makeModuloversiktTab()    {
        JPanel tab2Panel = new JPanel();
        tab2Panel.setLayout(new BoxLayout (tab2Panel, BoxLayout.Y_AXIS));
        
        JPanel modul1Panel = new JPanel();
        JButton modul1Button = new JButton("Modul 1");
        modul1Panel.add(modul1Button);
        JLabel modul1Label = new JLabel("<html>Innlevering:<font color='green'>Godkjent</font></html>");
        modul1Panel.add(modul1Label);
        tab2Panel.add(modul1Panel);
        
        JPanel modul2Panel = new JPanel();
        JButton modul2Button = new JButton("Modul 2");
        modul2Panel.add(modul2Button);
        JLabel modul2Label = new JLabel("<html>Innlevering:<font color='red'>Ikke godkjent</font></html>");
        modul2Panel.add(modul2Label);
        tab2Panel.add(modul2Panel);
        
        JPanel modul3Panel = new JPanel();
        JButton modul3Button = new JButton("Modul 3");
        modul3Panel.add(modul3Button);
        JLabel modul3Label = new JLabel("<html>Innlevering:<font color='yellow'>Under vurdering</font></html>");
        modul3Panel.add(modul3Label);
        tab2Panel.add(modul3Panel);
        
        JPanel modul4Panel = new JPanel();
        JButton modul4Button = new JButton("Modul 4");
        modul4Panel.add(modul4Button);
        JLabel modul4Label = new JLabel("<html>Innlevering:<font color='black'>Ikke levert</font></html>");
        modul4Panel.add(modul4Label);
        tab2Panel.add(modul4Panel);
        
        JPanel modul5Panel = new JPanel();
        JButton modul5Button = new JButton("Modul 5");
        modul5Panel.add(modul5Button);
        JLabel modul5Label = new JLabel("<html>Innlevering:<font color='black'>Ikke levert</font></html>");
        modul5Panel.add(modul5Label);
        tab2Panel.add(modul5Panel);
        return tab2Panel;
    }    
}
