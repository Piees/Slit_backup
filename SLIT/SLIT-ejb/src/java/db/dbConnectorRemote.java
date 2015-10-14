/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import javax.ejb.Remote;

/**
 *
 * @author piees
 */
@Remote
public interface dbConnectorRemote {
    
    /**
     * 
     * @param sql
     * @return single query string
     */
    public String singleQuery(String sql, String colName);
    
    public Connection dbConnection();
    
}
