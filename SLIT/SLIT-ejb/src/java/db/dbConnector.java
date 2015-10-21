/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author piees
 */
@Stateless
public class dbConnector implements dbConnectorRemote {

    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://peterhagane.net:3306/a_team";
    private static final String USERNAME = "yngve";
    private static final String PASSWORD = "a_team";
    //private String queryResult;
    private Connection DBConnection;
    
    @Override
    public Connection dbConnection() {
        Connection dbConnection = null;
        /*        // Check driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
	System.out.println("Where is your MySQL JDBC Driver?");
	e.printStackTrace();
       	//return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        */
        
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
    
    @Override
    public ArrayList<String> multiQuery(String query)   {
        Connection dbConnection = dbConnection();
        ArrayList<String> queryResults = new ArrayList<>();
        try {
            PreparedStatement ps = dbConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next())    {
                queryResults.add(rs.getString(i));
                i++;
            }
        }
        catch (SQLException e)  {
            System.out.println(e);
        }
        return queryResults;
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
                loginResults.add(rs.getString("fname"));
                loginResults.add(rs.getString("lname"));
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
    
    @Override
    public void uploadFileIntoResource(File file,
                                        String userName, String title) {
        System.out.println("Inserter uploadFileIntoResources1");
        Connection connection = dbConnection();
        System.out.println("Inserter uploadFileIntoResources2");
        
        /*
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tempCurrentTime = sdf.format(dt);        
        java.sql.Time currentTime  */
        
        
        //java.sql.Date currentTime = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String insertStatement = "INSERT INTO Resources (resourceFile, userName, title)" + 
                "VALUES (?, ?, ?)";
        
        //System.out.println(insertStatement);
        
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            PreparedStatement ps = connection.prepareStatement(insertStatement);
            ps.setBinaryStream(1, fileInput);
            ps.setString(2, userName);
            ps.setString(3, title);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
           
        }

               
    }
    
    // Should probably not be innside this class
    /*
    public enum DeliveryStatus {
        VURDERT, IKKEVURDERT, SETT, IKKESETT;      
    }
    
    @Override
    public void uploadFileIntoDelivery(int idModul, File fileToUpload, String userName) {
        Connection connection = dbConnection();
       
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = 
            new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        // currentTime == "2015-10-19 16:19:54" samme som pcen. 
        // Om man ikke kan inserte en string som DATETIME
        // test:
        //@Temporal(TemporalType.TIMESTAMP)
        //java.util.Date myDate;
        
        // temp
        DeliveryStatus status = DeliveryStatus.IKKESETT;
        String insertStatement = "INSERT INTO Delivery (idModul, deliveryFile, deliveryDate, deliveryStatus, deliveredBy)" +
                "VALUES ("+ idModul + ", " + fileToUpload + ", " + currentTime + ", " + status  + ", " + userName + ")";
        Statement statement;
        
        
        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertStatement);
        } catch (SQLException ex) {
                Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}
