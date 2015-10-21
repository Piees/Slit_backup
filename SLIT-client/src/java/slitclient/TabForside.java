/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Arild
 */
public class TabForside {
    
    public TabForside() {
        
    }
    
    /**
     * Lager forside-taben. Returnerer den til MakeGUI.makeTabs()
     * @return JPanel tab1Panel panel med innholdet i tab 1
     */
    public JPanel makeForsideTab()    {
        JPanel tab1Panel = new JPanel();
        GridBagLayout tab1Layout = new GridBagLayout();
        tab1Panel.setLayout(tab1Layout);
        
        JPanel nextLecturePanel = makeNextLecturePanel();
        GridBagConstraints gbcNLP = new GridBagConstraints();
        gbcNLP.gridx = 0;
        gbcNLP.gridy = 1;
        gbcNLP.fill = GridBagConstraints.VERTICAL;
        tab1Layout.setConstraints(nextLecturePanel, gbcNLP);
        tab1Panel.add(nextLecturePanel);

        JPanel messagesPanel = makeMessagesPanel();
        GridBagConstraints gbcMP = new GridBagConstraints();
        gbcMP.gridx = 1;
        gbcMP.gridy = 1;
        gbcMP.fill = GridBagConstraints.VERTICAL;
        gbcMP.insets = new Insets(0, 2, 0, 0);
        tab1Layout.setConstraints(messagesPanel, gbcMP);
        tab1Panel.add(messagesPanel);

        JPanel contactPanel = makeContactPanel();
        GridBagConstraints gbcCP = new GridBagConstraints();
        gbcCP.gridx = 2;
        gbcCP.gridy = 1;
        tab1Layout.setConstraints(contactPanel, gbcCP);
        tab1Panel.add(contactPanel);

        JPanel activityPanel = makeActivityPanel();
        GridBagConstraints gbcAP = new GridBagConstraints();
        gbcAP.gridx = 2;
        gbcAP.gridy = 2;
        gbcAP.fill = GridBagConstraints.VERTICAL;
        tab1Layout.setConstraints(activityPanel, gbcAP);
        tab1Panel.add(activityPanel);
    return tab1Panel;
    }    
    
    /**
     * Lager nextLecturePanelet som er inni forside-taben. Returnerer til makeForsideTab()
     * @return JPanel nextLecturePanel panelet med neste forelesninger
     */
    private JPanel makeNextLecturePanel()    {
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
    return nextLecturePanel;
    }
   
    /**
     * Lager messagesPanel som er inni forside-taben. Returnerer til makeForsideTab()
     * @return JPanel messagesPanel panelet med meldinger
     */
    private JPanel makeMessagesPanel()   {
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
    return messagesPanel;
    }
    
    /**
     * Lager contactPanel som er inni forside-taben. Returnerer til makeForsideTab()
     * @return JPanel contactPanel panelet som viser kontaktene (lærere)
     */
    private JPanel makeContactPanel()    {
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
    return contactPanel;
    }
    
    /**
     * Lager activityPanel som er inni forside-taben. Returnerer til makeForsideTab()
     * @return JPanel activityPanel panelet som viser siste hendelser i systemet
     */
    private JPanel makeActivityPanel()  {
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
    return activityPanel;
    }
    
    /**
     * Sender mail til en av kontaktene
     * @param mail e-post adressen til kontakten
     */
    private void sendMail(String mail) {
        Desktop desktop = Desktop.getDesktop();
        try {
            String message = "mailto:" + mail;
            URI uri = URI.create(message);
            desktop.mail(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ActionListener class for kontakt-knappene
     */
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
