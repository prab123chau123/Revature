package com.revature.javaFoundationProject_HotelManagement;
import java.sql.*;
public class MyDataBaseConnectivity 
{
	private static Connection connection=null;
	private static Statement statement=null;
	private PreparedStatement preparedStatement=null;
	private ResultSet resultSet=null;
	static {
		try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost/hotel_management","root","Prabhat44@");
		statement=connection.createStatement();
		}catch(Exception e) {}
		}
	  public static Connection getConnection()
	    {
	        return connection;
	    }
	  public static Statement getStatement() {
		  return statement;
	  }

}
