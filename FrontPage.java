/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.LESDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author Arild-Bærbar
 */
public class FrontPage {

    JFrame frame;

    public FrontPage() {
        makeFrame();
    }

    public static void main(String[] args) {
        FrontPage forside = new FrontPage();
    }

    public void makeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frame.getContentPane();
        GridBagLayout gblContent = new GridBagLayout();
        contentPane.setLayout(gblContent);
       
        
        JPanel commonContent =  makeCommon();
        
        JTabbedPane tabbedPane = makeTabs();
        GridBagConstraints gbcTab = new GridBagConstraints();
        gbcTab.gridx = 0;
        gbcTab.gridy = 1;
        gbcTab.gridwidth = 3;
        gblContent.setConstraints(tabbedPane, gbcTab);
        GridBagConstraints gbcCommon = new GridBagConstraints();
        gbcCommon.gridx = 0;
        gbcCommon.gridy = 0;
        gbcCommon.gridwidth = 3;
        gblContent.setConstraints(commonContent, gbcCommon);
        contentPane.add(commonContent);
        contentPane.add(tabbedPane);
        
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel makeCommon() {

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
    public JPanel makeForsideTab()    {
        JPanel tab1Panel = new JPanel();
        GridBagLayout tab1Layout = new GridBagLayout();
        tab1Panel.setLayout(tab1Layout);
        JPanel nextLecturePanel = new JPanel();
        nextLecturePanel.setLayout(new BoxLayout(nextLecturePanel, BoxLayout.Y_AXIS));

        JLabel nextLectureHeader = new JLabel("Neste forelesning:");
        nextLecturePanel.add(nextLectureHeader);

        JLabel nextLecture1 = new JLabel("<html><u>Onsdag 23. september,</u><br>08:15-11:00."
                + "<br>Tema: Abstraksjon</html>");
        nextLecturePanel.add(nextLecture1);

        JLabel nextLecture2 = new JLabel("<html><u>Tirsdag 01. oktober,</u><br>08:15-10:00."
                + "<br>Tema: Modularisering</html>");
        nextLecturePanel.add(nextLecture2);

        GridBagConstraints gbcNL = new GridBagConstraints();
        gbcNL.gridx = 0;
        gbcNL.gridy = 1;
        gbcNL.fill = GridBagConstraints.VERTICAL;
        tab1Layout.setConstraints(nextLecturePanel, gbcNL);
        tab1Panel.add(nextLecturePanel);

        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));

        JLabel messagesHeader = new JLabel("Meldinger:");
        messagesPanel.add(messagesHeader);

        JLabel demoMessage1 = new JLabel("<html><u>Husk å installere BlueJ</u><br>"
                + "Når du installerer er det viktig at...</html>");
        messagesPanel.add(demoMessage1);

        JLabel demoMessage2 = new JLabel("<html><u>Nyttige tips i Java</u><br>"
                + "Det kan være praktisk å...</html>");
        messagesPanel.add(demoMessage2);

        GridBagConstraints gbcMP = new GridBagConstraints();
        gbcMP.gridx = 1;
        gbcMP.gridy = 1;
        gbcMP.fill = GridBagConstraints.VERTICAL;
        gbcMP.insets = new Insets(0, 2, 0, 0);
        tab1Layout.setConstraints(messagesPanel, gbcMP);
        tab1Panel.add(messagesPanel);

        JPanel contactPanel = new JPanel();
        GridBagLayout contactLayout = new GridBagLayout();
        contactPanel.setLayout(contactLayout);

        GridBagConstraints gbcContact = new GridBagConstraints();
        JButton contactLecturer1 = new JButton("<html>Hallgeir Nilsen,<br>"
                + "hallgeir.nilsen@uia.no,<br>"
                + "Kontor: H1 011</html>");
        String mailHallgeir = "hallgeir.nilsen@uia.no";
        gbcContact.gridx = 0;
        gbcContact.gridy = 0;
        contactLayout.setConstraints(contactLecturer1, gbcContact);
        contactLecturer1.addActionListener(new sendMailActionListener(mailHallgeir));
        contactPanel.add(contactLecturer1);

        JButton contactLecturer2 = new JButton("<html>Even Larsen,<br>"
                + "even.larsen@uia.no,<br>"
                + "Kontor: H1 007</html>");
        String mailEven = "even.larsen@uia.no";
        gbcContact.gridx = 1;
        gbcContact.gridy = 0;
        contactLayout.setConstraints(contactLecturer2, gbcContact);
        contactLecturer2.addActionListener(new sendMailActionListener(mailEven));
        contactPanel.add(contactLecturer2);

        JButton contactTA1 = new JButton("<html>Arild Høyland,<br>"
                + "arildh93@gmail.com</html>");
        String mailArild = "arildh93@gmail.com";
        gbcContact.gridx = 0;
        gbcContact.gridy = 1;
        contactLayout.setConstraints(contactTA1, gbcContact);
        contactTA1.addActionListener(new sendMailActionListener(mailArild));
        contactPanel.add(contactTA1);

        JButton contactTA2 = new JButton("<html>Robin Rondestvedt,<br>"
                + "robin@example.com</html>");
        String mailRobin = "robin@example.com";
        gbcContact.gridx = 1;
        gbcContact.gridy = 1;
        contactLayout.setConstraints(contactTA2, gbcContact);
        contactTA2.addActionListener(new sendMailActionListener(mailRobin));
        contactPanel.add(contactTA2);

        GridBagConstraints gbcCP = new GridBagConstraints();
        gbcCP.gridx = 2;
        gbcCP.gridy = 1;
        tab1Layout.setConstraints(contactPanel, gbcCP);
        tab1Panel.add(contactPanel);

        JPanel activityPanel = new JPanel();
        activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));

        JLabel lastActivityHeader = new JLabel("<html><u>Sist aktivitet</u></html>");
        activityPanel.add(lastActivityHeader);

        JPanel activity1 = new JPanel();
        activity1.setLayout(new BoxLayout(activity1, BoxLayout.Y_AXIS));
        JLabel activity1Label = new JLabel("Modul 2 godkjent");
        JButton modulComment = new JButton("Se tilbakemelding");
        activity1.add(activity1Label);
        activity1.add(modulComment);

        activityPanel.add(activity1);

        JPanel activity2 = new JPanel();
        activity2.setLayout(new BoxLayout(activity2, BoxLayout.Y_AXIS));
        JLabel activity2Label = new JLabel("Forelesningsplan høst 2015");
        JButton goToActivity = new JButton("Les mer...");
        activity2.add(activity2Label);
        activity2.add(goToActivity);

        activityPanel.add(activity2);

        GridBagConstraints gbcAP = new GridBagConstraints();
        gbcAP.gridx = 2;
        gbcAP.gridy = 2;
        tab1Layout.setConstraints(activityPanel, gbcAP);
        tab1Panel.add(activityPanel);
    return tab1Panel;
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
    public JPanel makeFagstoff()    {
        JPanel tab3Panel = new JPanel();
        JTextField textField = new JTextField("Her kommer alt fagstoff.");
        tab3Panel.add(textField);
        return tab3Panel;
    }
    
      
    public JTabbedPane makeTabs()  {
        JTabbedPane tabbedPane = new JTabbedPane();
       
        JComponent tab1 = makeForsideTab();
        tabbedPane.addTab("Forside", null, tab1, null);
        
        JComponent tab2 = makeModuloversiktTab();
        tabbedPane.addTab("Moduloversikt", null, tab2, null);
        
        JComponent tab3 = makeFagstoff();
        tabbedPane.addTab("Fagstoff", null, tab3, null);
        return tabbedPane;
    }
    
    

    public void sendMail(String mail) {
        Desktop desktop = Desktop.getDesktop();
        try {
            String message = "mailto:" + mail;
            URI uri = URI.create(message);
            desktop.mail(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class sendMailActionListener implements ActionListener {

        String mail;

        public sendMailActionListener(String mail) {
            this.mail = mail;
        }

        public void actionPerformed(ActionEvent e) {
            sendMail(mail);
        }
    }
}
