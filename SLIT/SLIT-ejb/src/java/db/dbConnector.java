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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import com.google.common.collect.ImmutableMap;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private static Connection DBConnection;
    ArrayList<String> updateUsersArrayList;
    private Map<String, String> userMap;
    public static HashMap<String, Map> allUsersHashMap;
    
    @Override
    public Connection dbConnection() {
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
        if (DBConnection == null) {
            try {           
                DBConnection = DriverManager.getConnection(
                    DB_URL, USERNAME, PASSWORD);
                System.out.println("New connection established!");
            } catch (SQLException ex) {
                Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
                //return null;
            }
            return DBConnection;
        }
        else {
            System.out.println("Old connection reused");
            return DBConnection;
        }
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
    public void insertIntoDB(String table, ArrayList<String> columns, ArrayList<Object> values) {
        //create the beginning of the insert-string
        String insert = "INSERT INTO " + table + "(";
      
        int countColumns = 0;
        //while we have more columns than the current count +1, add 
        //column.get(countColumns) +", "; to the insert-string
        while(columns.size() > (countColumns +1))   {
            insert += columns.get(countColumns) + ", ";
            countColumns++;
        }
        //if columns.size() is smaller than countColumns+1, this means that
        //this is the last column. Therefore we can't add a comma at the end,
        //but instead we can close the paranthesis and continue to values
        insert += columns.get(countColumns) + ") VALUES(";
        
        int countValues = 0;
        //same principle as for columns here
        //we insert ? here instead of values, as we're going to add
        //the values using the setString() method of PreparedStatement
        while(values.size() > (countValues + 1))   {
            insert += "?, ";
            countValues++;
        }
        //same principle as for columns, we don't have any more values, therefore 
        //we make this the end of the insert-string
        insert +=  "?);";
        System.out.println("metode insert kalt");
        System.out.print(insert);
        DBConnection = dbConnection();
        try {
            System.out.println("try i insert-metode");
            PreparedStatement ps = DBConnection.prepareStatement(insert);
            int i = 1;
            int index = 0;
            //this sets the "?" in our insert-string as the corresponding -1
            //index in the arraylist
            //meaning our first "?" will have the first element (index 0) of our arraylist
            //because arraylist index starts at 0, and the index for counting "?" in our
            //insert-string starts at 1, this must always be one larger than the arraylist-index
            while (values.size() >= i) {
                if(values.get(index) instanceof String) {
                    ps.setString(i, values.get(index).toString());
                }           
                else if (values.get(index) instanceof Integer) {
                    ps.setInt(i, (int) values.get(index));
                }           
                else if (values.get(index) instanceof Boolean) {
                    ps.setBoolean(i,(Boolean) values.get(index));
                }           
                else if (values.get(index) instanceof File) {
                    File file = (File) values.get(index);
                    FileInputStream fileInput = null;
                    try {
                        fileInput = new FileInputStream(file);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(dbConnector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ps.setBinaryStream(i,(FileInputStream) fileInput);
                }           
                else if (values.get(index) instanceof java.sql.Timestamp) {
                    ps.setTimestamp(i,(java.sql.Timestamp) values.get(index)); 
                }
                else {
                    System.out.println("INVALID OBJECT TYPE!");
                }
                index++;
                i++;
            }
            System.out.println(ps);
            ps.executeUpdate();
        }
        catch (SQLException ex)  {
            System.out.println("CATCH I INSERT-METODE");
            System.out.println(ex);
        }
    }
    
    @Override
    public int countRows(String column, String tableName)    {
        String count = "SELECT COUNT(" + column + ") FROM " +  tableName + ";";
        String numberOfRows = "";
        DBConnection = dbConnection();
        try {
            PreparedStatement ps = DBConnection.prepareStatement(count);
            ResultSet rs = ps.executeQuery();
            rs.next();
            numberOfRows = rs.getString(1);
            System.out.println("ANTALL REKKER I MODUL:" + numberOfRows);
        }
        catch (SQLException e)  {
            System.out.println(e);
        }
        int returnInt = Integer.parseInt(numberOfRows);
        return returnInt;
    }
    
    @Override
    public ArrayList multiQuery(ArrayList<String> columns, ArrayList<String> 
            tables, ArrayList<String> where)    {
        String query = "SELECT ";
        ArrayList<String> queryResults = new ArrayList<>();
        
        int countColumns = 0;
        while(columns.size() > (countColumns +1))   {
            query += columns.get(countColumns) + ", ";
            countColumns++;
        }
        query += columns.get(countColumns) + " FROM ";
        
        int countTables = 0;
        while(tables.size() > (countTables +1)) {
            query += tables.get(countTables) + ", ";
            countTables++;
        }
        query += tables.get(countTables);
        if(where != null)    {
            int countWhere = 0;
            query += " WHERE ";
                while(where.size() > (countWhere +1))   {
                query += where.get(countWhere) + ", ";
                countWhere ++;
                }
            query += where.get(countWhere) + ";";
        }
        else {
            query += ";";
        }
        DBConnection = dbConnection();
        try {
            System.out.println("try i multi-query metode");
            PreparedStatement ps = DBConnection.prepareStatement(query);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next())   {
                int i = 1;
                while (columnCount >= i)    {
                    queryResults.add(rs.getString(i));
                    i++;
                }
            }
            System.out.println("QueryResults-liste HER: " + queryResults.size());
        }
        catch (SQLException e)  {
            System.out.println("SQL-SYNTAX-ERROR I MULTI-QUERY-METODE");
            System.out.println(e);
        }
        return queryResults;
    }    
    
    //HER KOMMER UPDATEUSERS, MÅ FLYTTES TIL ANNEN EJB
    @Override
    public Map<String, String> eachUserMap(int fromIndex) {
        userMap = ImmutableMap.of(
                "userType", updateUsersArrayList.get(fromIndex), //fromIndex (+1,2,3,4)
                "mail", updateUsersArrayList.get(fromIndex + 1),
                "fname", updateUsersArrayList.get(fromIndex + 2),
                "lname", updateUsersArrayList.get(fromIndex + 3),
                "userName", updateUsersArrayList.get(fromIndex + 4)
            );
        return userMap;
    }
    @Override
    public void updateUsersHashMap() {
        ArrayList<String> select = new ArrayList<>(Arrays.asList("userType,"
                + "mail, fname, lname, userName"));
        ArrayList<String> from = new ArrayList<>(Arrays.asList("User"));
        ArrayList<String> where = new ArrayList<>(Arrays.asList("userName != 'null'"));
        updateUsersArrayList = multiQuery(select, from, where);
        allUsersHashMap = new HashMap<>(); //make global
        for(int i = 0; i < updateUsersArrayList.size(); i += 5) {
            Map<String, String> updateUserHashMapHelper = eachUserMap(i);
            allUsersHashMap.put(updateUserHashMapHelper.get("userName"), 
                        updateUserHashMapHelper);
        }
    }
    
    //@Override
    public HashMap<String, Map> getAllUsersHashMap() {
        return allUsersHashMap;
    }
    
    
    
    /**
     * Lager contactPanel som er inni forside-taben. Returnerer til makeForsideTab()
     * @return JPanel contactPanel panelet som viser kontaktene (lærere)
     */
    @Override
    public JPanel makeContactPanel()    {
        JPanel contactPanel = new JPanel();
        GridBagLayout contactLayout = new GridBagLayout();
        contactPanel.setLayout(contactLayout);
        
//        for(int i = 0; i < getAllUsersHashMap().size(); i++) {
//            GridBagConstraints fishCon = new GridBagConstraints();
//            JLabel contactLabel = new JLabel(getAllUsersHashMap().get(i));
//        }
        System.out.println("Pre-FISH");
        for(Entry<String, Map> entry : getAllUsersHashMap().entrySet()) {
            String key = entry.getKey();
            System.out.println("FISH!");
            System.out.println(key);
        }

        GridBagConstraints gbcSearchField = new GridBagConstraints();
        JTextField searchField = new JTextField(20);
        gbcSearchField.gridx = 0;
        gbcSearchField.gridy = 0;
        gbcSearchField.gridwidth = 2;
        contactLayout.setConstraints(searchField, gbcSearchField);
        //searchField.addActionListener(new returnSearchResults); //tbi
        contactPanel.add(searchField);        
        
        GridBagConstraints gbcContact = new GridBagConstraints();
        JLabel contactLecturer1 = new JLabel("<html>Hallgeir Nilsen,<br>"
                + "hallgeir.nilsen@uia.no,<br>"
                + "Kontor: H1 011</html>");
        String mailHallgeir = "hallgeir.nilsen@uia.no";
        gbcContact.gridx = 0;
        gbcContact.gridy = 1;
        contactLayout.setConstraints(contactLecturer1, gbcContact);
        //contactLecturer1.addActionListener(new sendMailActionListener(mailHallgeir));
        contactPanel.add(contactLecturer1);

        JButton contactLecturer2 = new JButton("<html>Even Larsen,<br>"
                + "even.larsen@uia.no,<br>"
                + "Kontor: H1 007</html>");
        String mailEven = "even.larsen@uia.no";
        gbcContact.gridx = 1;
        gbcContact.gridy = 1;
        contactLayout.setConstraints(contactLecturer2, gbcContact);
        //contactLecturer2.addActionListener(new sendMailActionListener(mailEven));
        contactPanel.add(contactLecturer2);

        JButton contactTA1 = new JButton("<html>Arild Høyland,<br>"
                + "arildh93@gmail.com</html>");
        String mailArild = "arildh93@gmail.com";
        gbcContact.gridx = 0;
        gbcContact.gridy = 2;
        contactLayout.setConstraints(contactTA1, gbcContact);
        //contactTA1.addActionListener(new sendMailActionListener(mailArild));
        contactPanel.add(contactTA1);

        JButton contactTA2 = new JButton("<html>Robin Rondestvedt,<br>"
                + "robin@example.com</html>");
        String mailRobin = "robin@example.com";
        gbcContact.gridx = 1;
        gbcContact.gridy = 2;
        contactLayout.setConstraints(contactTA2, gbcContact);
        //contactTA2.addActionListener(new sendMailActionListener(mailRobin));
        contactPanel.add(contactTA2);
    return contactPanel;
    }
    
    
    
}
