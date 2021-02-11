/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuIpages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author ukudo
 */
public class JDBC {
 
  PreparedStatement preparedStatement;
  ResultSet resultSet=null;
  Connection conn=new Connections().createConnection();
  int userid=0;
public void addData(String fn,String ln,String phone){

     try{
  
         preparedStatement=conn.prepareStatement("insert into registrationdetails(firstname,lastname,phone) values(?,?,?)");
            
            preparedStatement.setString(1, fn);
              preparedStatement.setString(2, ln);
            preparedStatement.setString(3,phone);
            preparedStatement.executeUpdate();
            final JOptionPane pane=new JOptionPane("Well Inserted !");
            final JDialog d=pane.createDialog("message");
            d.setLocation(60, 100);
            d.setVisible(true);
            
          
     }
     catch(Exception e)
     {
      JOptionPane.showMessageDialog(null, e.getMessage()); 
     }
  }
  
  public ResultSet getData(){
     try{ 
      
      preparedStatement=conn.prepareStatement("SELECT * FROM `registrationdetails`");
      resultSet=preparedStatement.executeQuery();
    /*  while(resultSet.next()){
          String id=resultSet.getString("ID");
          String firstname=resultSet.getString("firstname");
          String lastname=resultSet.getString("lastname");
          String phone=resultSet.getString("phone");
          System.out.println("Id "+id);
          System.out.println("First Name: "+firstname);
          System.out.println("Last Name: "+lastname);
          System.out.println("Phone :"+phone);
          System.out.println("\n................");
      }*/
      
     }catch(Exception e){
      e.printStackTrace();
     }
     return resultSet ;
     }
  public String[] GetSelectedPerson(String column,String Written)
  {
      int Id;
    String []GetUser=new String[10];
     
        try{ 
            Id=  GetId(column,Written);
            userid=Id;
            preparedStatement=conn.prepareStatement("SELECT * FROM `registrationdetails` where ID=?");   
              preparedStatement.setInt(1, Id);
              resultSet= preparedStatement.executeQuery();
if( resultSet.next())
{
  String firstname=resultSet.getString("firstname");
  String lastname=resultSet.getString("lastname");
  String phonenumber=resultSet.getString("phone");
  GetUser[0]=(firstname);
   GetUser[1]=(lastname);
    GetUser[2]=(phonenumber);
}
else
{
     final JOptionPane pane=new JOptionPane("nothing is found !");
            final JDialog d=pane.createDialog("message");
            d.setLocation(400, 300);
            d.setVisible(true);  
}
    
          
      } catch (SQLException ex) {
            
           
          Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
      }
        return GetUser;
      
  }

  private int GetId(String column,String Written)
  
  {
      int id = 0;
       try {
          preparedStatement=conn.prepareStatement("SELECT ID FROM registrationdetails where UPPER("+column+")like '%"+Written+"%'");
//          preparedStatement.setString(1, column);
//          preparedStatement.setString(2, Written);
          
          
                resultSet=preparedStatement.executeQuery();
                if(resultSet.next())
                    id=resultSet.getInt("ID");
}     catch (SQLException ex) {
          Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
      }
       return id;
  }
  public void Update(String fna,String lna,String phone)
  {
      if(userid!=0)
      {
      try {  
          preparedStatement=conn.prepareStatement("update registrationdetails set firstname=?,lastname=?,phone=? where ID=?");
           preparedStatement.setString(1, fna);
           preparedStatement.setString(2, lna);
           preparedStatement.setString(3, phone);
           preparedStatement.setInt(4,userid);
           preparedStatement.executeUpdate();
            final JOptionPane pane=new JOptionPane("well updated !");
            final JDialog d=pane.createDialog("message");
            d.setLocation(400, 300);
            d.setVisible(true);  
           
      } catch (SQLException ex) 
      {
          //If there comes the sql error
          final JOptionPane pane=new JOptionPane("Error \n the phone number is aquired  !");
            final JDialog d=pane.createDialog("message");
            d.setLocation(400, 300);
            d.setVisible(true);
          Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
      }
      
  }
      //If the is no user has been selected
      else
  {
    final JOptionPane pane=new JOptionPane("No user selected !");
            final JDialog d=pane.createDialog("message");
            d.setLocation(400, 300);
            d.setVisible(true);  
  }
  }
  public void Deleteuser()
  {
      if(userid!=0)
      {
      try {  
          preparedStatement=conn.prepareStatement("delete from registrationdetails  where ID=?");
          
           preparedStatement.setInt(1,userid);
           int confirm=JOptionPane.showConfirmDialog(null, "Do you really want to delete the user ?","Prompting",JOptionPane.YES_NO_OPTION);
           if(confirm==0)
           {
           preparedStatement.executeUpdate();
            final JOptionPane pane=new JOptionPane("well deleted !");
            final JDialog d=pane.createDialog("message");
            d.setLocation(400, 300);
            d.setVisible(true); 
           }
           
      } catch (SQLException ex) 
      {
          //If there comes the sql error
      
          Logger.getLogger(JDBC.class.getName()).log(Level.SEVERE, null, ex);
      }
      
  }
      //If the is no user has been selected
      else
  {
    final JOptionPane pane=new JOptionPane("No user selected !");
            final JDialog d=pane.createDialog("message");
            d.setLocation(400, 300);
            d.setVisible(true);  
  } 
  }
 
  
}
