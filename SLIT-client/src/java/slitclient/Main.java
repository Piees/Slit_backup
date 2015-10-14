/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slitclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import db.dbConnectorRemote;
//import db.dbConnector;

/**
 *
 * @author piees
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Starting...");
        System.out.println("Establishing connection to the bean..");
        dbConnectorRemote fbRemote;
        try {
            InitialContext initialContext = new InitialContext();        
            fbRemote = (dbConnectorRemote) initialContext.lookup("java:global/SLIT/SLIT-ejb/dbConnector");
            // Its probably not good practice to use the dbConnector like below.
            new Login(fbRemote);
            //System.out.println("Calling method on the bean...");
            //System.out.println("    Result1: " + fbRemote.singleQuery("Select mail from User where userName = 'viktos08'", "mail"));
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            System.exit(1);
        }
        System.out.println("Exiting...");    
    }
}
