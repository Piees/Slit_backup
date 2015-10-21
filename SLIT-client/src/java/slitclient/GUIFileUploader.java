/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import db.dbConnectorRemote;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
 
/**
 *
 * @author Viktor Setervang
 */
public class GUIFileUploader {
    
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private File file;
   
   
    public GUIFileUploader(){
        prepareGUI();
    }

    public static void main(String[] args){
        GUIFileUploader  fileuploader = new GUIFileUploader();      
        fileuploader.showFileChooserDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        });    
        headerLabel = new JLabel("", JLabel.CENTER);        
        statusLabel = new JLabel("",JLabel.CENTER);    

        statusLabel.setSize(350,100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);  
    }

    private void showFileChooserDemo(){
        headerLabel.setText("Control in action: JFileChooser"); 

        final JFileChooser  fileDialog = new JFileChooser();
        JButton showFileDialogButton = new JButton("Open File");
        showFileDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(mainFrame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    GUIFileUploader.this.file = fileDialog.getSelectedFile();
                    statusLabel.setText("File Selected :"
                            + GUIFileUploader.this.file.getName());
                }
                else{
                    statusLabel.setText("Open command cancelled by user." );           
                }      
            }
        });
      
        JButton uploadButton = new JButton("Upload File");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EJBConnector ejbConnector = new EJBConnector();
                //InserterRemote uploader = ejbConnector.getInserter();
                dbConnectorRemote uploader = ejbConnector.getEjbRemote();
                if (GUIFileUploader.this.file != null) {
                    System.out.println("file is not null");
                    //FileInputStream file = new FileInputStream(GUIFileUploader.this.file);
                    uploader.uploadFileIntoResource(GUIFileUploader.this.file, "viktos08", "True awesomeness");
                }
                else {
                    statusLabel.setText("No file selected!" );
                }
            }
        });
      
        controlPanel.add(showFileDialogButton);
        controlPanel.add(uploadButton);
        mainFrame.setVisible(true);  
    }
}