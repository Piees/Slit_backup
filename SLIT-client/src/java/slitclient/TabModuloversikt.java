/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;


import db.dbConnectorRemote;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author Arild
 */
public class TabModuloversikt {
    private final int IS109 = 5;
    private final int IS110 = 10;
    private String userType;
    
    public TabModuloversikt(String userType)   {
        this.userType = userType;
    }
    /**
     * Lager moduloversikttaben. 
     * Lager utvidbar liste med antall moduler.
     * @return JPanel tab2Panel returnerer panel med innholdet i tab2
     */
    public JPanel makeModuloversiktTab()    {
        JPanel tab2Panel = new JPanel();
        
        if(userType.equals("teacher"))  {
            JButton createModulButton = new JButton("Opprett modul");
                createModulButton.addActionListener(new ActionListener()  {
                    @Override
                    public void actionPerformed (ActionEvent e)  {
                        createModul();
                    }
                });
                
            tab2Panel.add(createModulButton);
        }
        
        Component accordion = makeAccordion();
        tab2Panel.add(accordion);
        return tab2Panel;
    }   
    public Component makeAccordion(){
        JXPanel panel = new JXPanel();
        panel.setLayout(new BorderLayout());
        
        ArrayList<Modul> modulList = new ArrayList<Modul>(Modul.makeModules(IS109));  
  
     JXTaskPaneContainer taskPaneContainer = new JXTaskPaneContainer();  
     for (Modul modul : modulList)  {
         JXTaskPane modulPane;
        if (userType.equals("student")) {
            modulPane = new JXTaskPane(modul.getName() + modul.getStatus());
        }
        else {
            modulPane = new JXTaskPane(modul.getName() + "10/50 innlevert."); // + modul.getAntDeliveries());
        }
         modulPane.setCollapsed(true);
         modulPane.add(modul.getInnhold());
         taskPaneContainer.add(modulPane);
     }
    
    
         
     panel.add(taskPaneContainer);
     
     return panel;
    }
    public void createModul()   {
        JDialog createModulDialog = new JDialog();
        JPanel contentPane = (JPanel) createModulDialog.getContentPane();
        JTextField createModulText = new JTextField("Modulttekst");
        createModulText.setSize(250, 250);
        JTextField createModulDesc = new JTextField("Modulbeskrivelse");
        JTextField createModulLearningObj = new JTextField("Læringsmål i denne modulen");
        
        //these have to be moved to some kind of action listener, as they'll now
        //get the default text of the text fields, not the user entered text
        String modulText = createModulText.getText();
        String modulDesc = createModulDesc.getText();
        String modulLearningObj = createModulLearningObj.getText();
        
        String insert = "INSERT INTO Modul (idModul, modulText, modulDesc, learningObj) "
                 + "\nVALUES ('1', '" + modulText + "', '" + modulDesc + "', '" + modulLearningObj + "');";
        System.out.println(insert);
        contentPane.add(createModulText);
        contentPane.add(createModulDesc);
        contentPane.add(createModulLearningObj);
        
        JButton createModulButton = new JButton("Opprett modul");
        createModulButton.addActionListener(new ActionListener()    {
            public void actionPerformed(ActionEvent e)  {
                System.out.println("Lagrer modul i database");
            }
        });
        contentPane.add(createModulButton);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        createModulDialog.setSize(250, 250);
        createModulDialog.pack();
        createModulDialog.setVisible(true);
    }
}
        
        