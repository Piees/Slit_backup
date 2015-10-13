 /*
 * This class might conflict with solutions like JPA or other JavaEE solutions. 
 * If we keep this solution we should consider moving it to a different project.
 */
package my.dbconnection;

// 3rd party lib, but is provided by Netbeans.
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Viktor Setervang
 */
public class DBConnection {
    private Connection DBConnection;
    
    public Connection connect() {
        try {
            // Returns the Class object associated with the given string name
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Success");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Connection Failed" + e);
        }
        // The url is hardcoded, so its probably not the best solution.
        String url = "jdbc:mysql://peterhagane.net:3306/a_team";
        try {
            DBConnection = (Connection ) DriverManager.getConnection(url, "viktor", "a_team");
            System.out.println("Database Connected");
        }
        catch(SQLException e) {
            System.out.println("No Database" + e);
        }
        return DBConnection;
    }
}
        
  
