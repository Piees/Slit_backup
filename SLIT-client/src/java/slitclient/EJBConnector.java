/*
 * The purpose of this class is to connect to the EJB dbConnectorRemote, and provide 
 * this connection to other classes thats needs it by using a simple getMethod.
 *  
 * This way we will only need to change the adress one place if we ever need
 * to change the lookup adress.
 */
package slitclient;

import db.dbConnectorRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Viktor Setervang
 */
public class EJBConnector {
    dbConnectorRemote ejbRemote;
   
    public EJBConnector() {
        System.out.println("Starting...");
        System.out.println("Establishing connection to the bean..");
        InitialContext initialContext;        
        try {
            initialContext = new InitialContext();
            ejbRemote = (dbConnectorRemote) initialContext.lookup("java:global/SLIT/SLIT-ejb/dbConnector"); 
        } catch (NamingException ex) {
            Logger.getLogger(EJBConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            System.exit(1);
        }    
    }

    /**
     * Use this method to get the enterprise java bean connection
     * 
     * @return 
     */
    public dbConnectorRemote getEjbRemote() {
        return ejbRemote;
    }
    
}
