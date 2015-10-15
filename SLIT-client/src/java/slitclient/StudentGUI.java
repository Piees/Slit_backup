/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import db.dbConnector;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



/**
 *
 * @author Arild H�yland
 */
public class StudentGUI {

    JFrame frame;
    TabForside tabForside;
    TabModuloversikt tabModuloversikt;
    TabFagstoff tabFagstoff;
    String nameOfUser;

    /**
     * Constructor for MakeGUI. Oppretter objekter av alle tab-klassene, og
     * kaller makeFrame()
     */
    public StudentGUI(String userName) {
        tabForside = new TabForside();
        tabModuloversikt = new TabModuloversikt();
        tabFagstoff = new TabFagstoff();
        //nameOfUser = getNameOfUser(userName);
        makeFrame();
    }
    /*
    public String getNameOfUser(String userName)   {
        ArrayList<String> queryResults = dbConnector.multiQuery("SELECT fname, ename "
                                     + "FROM User WHERE userName = " + userName +";");
        String returnString = null;
        for(String string : queryResults)   {
            returnString += string;
        }
        return returnString;
    }
    /**
     * Lager vinduet. Vinduet har GridBagLayout (enn så lenge i hvert fall).
     * Kaller makeCommon(). 
     * Kaller også makeTabs().
     */
    public void makeFrame() {
        frame = new JFrame("SLIT - " + nameOfUser);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frame.getContentPane();
        GridBagLayout gblContent = new GridBagLayout();
        contentPane.setLayout(gblContent);
       
        
        JPanel commonContent =  makeCommon(nameOfUser);
        GridBagConstraints gbcCommon = new GridBagConstraints();
        gbcCommon.gridx = 0;
        gbcCommon.gridy = 0;
        gbcCommon.gridwidth = 3;
        gblContent.setConstraints(commonContent, gbcCommon);
        
        JTabbedPane tabbedPane = makeTabs();
        GridBagConstraints gbcTab = new GridBagConstraints();
        gbcTab.gridx = 0;
        gbcTab.gridy = 1;
        gbcTab.gridwidth = 3;
        gblContent.setConstraints(tabbedPane, gbcTab);

        contentPane.add(commonContent);
        contentPane.add(tabbedPane);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Lager elementene som er felles for alle faner 
     * (kun topplinja med logo og navn på bruker foreløpig).
     * Har BorderLayout, skal endres. Layout er foreløpig, funker ikke som den
     * bør gjøre nå.
     * @return JPanel content panelet med innholdet som er felles for alle faner
    */
    public JPanel makeCommon(String userName) {

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JButton menuButton = new JButton("Meny");
        content.add(menuButton, BorderLayout.WEST);

        JLabel logoLabel = new JLabel("LOGO");
        content.add(logoLabel, BorderLayout.CENTER);

        JButton nameButton = new JButton("Student Studentsen");
        content.add(nameButton, BorderLayout.EAST);

        return content;
        
    }

 
    /**
     * Lager faner. Kaller de respektive klassene for å lage innholdet, lager 
     * fanene med navn (og innhold) og legger de til tabbedPane.
     * @return JTabbedPane tabbedPane returnerer linja med fanene
     */  
    public JTabbedPane makeTabs()  {
        JTabbedPane tabbedPane = new JTabbedPane();
       
        JComponent tab1 = tabForside.makeForsideTab();
        tabbedPane.addTab("Forside", null, tab1, null);
        
        JComponent tab2 = tabModuloversikt.makeModuloversiktTab();
        tabbedPane.addTab("Moduloversikt", null, tab2, null);
        
        JComponent tab3 = tabFagstoff.makeFagstoff();
        tabbedPane.addTab("Fagstoff", null, tab3, null);
        return tabbedPane;
    }
    
    

    
}
