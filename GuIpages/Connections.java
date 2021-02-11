/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuIpages;

import java.sql.Connection;
import java.sql.DriverManager;



/**
 *
 * @author Kwizera
 */
public class Connections {
    public Connection createConnection(){
    
     
        Connection  connection=null;
    
    try{
       Class.forName("com.mysql.jdbc.Driver");   //register driver

      connection=DriverManager.getConnection( "jdbc:mysql://localhost:3306/contact","root","");
      System.out.println("Connected");
        
    }   catch (Exception ex)
    {
     ex.getMessage();
    }
  return connection;
  
  }
}
