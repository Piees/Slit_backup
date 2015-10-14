/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author piees
 */
@Stateless
public class dbConnector implements dbConnectorRemote {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://peterhagane.net:3306/a_team";
    private static final String USERNAME = "yngve";
    private static final String PASSWORD = "a_team";
    //private String queryResult;
    private Connection DBConnection;
    
    @Override
    public Connection dbConnection() {
        Connection dbConnection = null;
                // Check driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
	System.out.println("Where is your MySQL JDBC Driver?");
	e.printStackTrace();
       	//return;
        }
        System.out.println("MySQL JDBC Driver Registered!");

        
        // Connection
        try {
            dbConnection = DriverManager.getConnection(
                    DB_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
            //return null;
        }
        return dbConnection;
    }
    
    @Override
    public String singleQuery(String query, String colName) {
        String queryResult = null;
        Connection dbConnection = dbConnection();
        // Query
        try {
            PreparedStatement ps = dbConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.isLast()) {
                    queryResult = rs.getString(colName);
                    System.out.println(queryResult);
                } else {
                    System.out.println("There are more than one result");
                    break;
                }
            }
            System.out.println("Statement Successful");
        } catch (SQLException e) {
           System.out.println(e);
           return("error: " + e);
        }
        return queryResult;
    }
    
    /**
     * This method is used by the Login class to check if the user
     * has supplied a correct userName and password combination.
     * 
     * This method should later on contain some kind of encryption mechanism
     * like salt?
     * 
     * @param userName
     * @param pwd 
     * @return the result of the login query
     * 
     */
    @Override
    public ArrayList<String> login(String userName, String pwd) {
        String loginQuery = "SELECT * FROM User WHERE userName=? and pwd=?";
        Connection dbConnection = dbConnection();
        ArrayList<String> loginResults = new ArrayList<>();
        try {
            // PreparedStatement prevents SQL Injections by users.
            PreparedStatement ps = dbConnection.prepareStatement(loginQuery);
            ps.setString(1, userName);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            // If true then the username + password was a match
            if (rs.next()) {
                loginResults.add(userName);
                loginResults.add(rs.getString("userType"));
            } 
            else {
                loginResults.add("Username Password combination invalid");              
            }   
        } 
        catch (SQLException ex) {
            Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
            loginResults.add(ex.getMessage());
        }
        return loginResults;
    }
}
