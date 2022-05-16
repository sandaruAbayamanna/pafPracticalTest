package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.xdevapi.Table;

public class User {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ElectroGrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readUser()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table class='table' border='1'><thead class='table-dark'>"
					+ "<th>User ID</th>"
					//+ "<th>TEL No</th>"
					//+ "<th>Date</th>"
					+ "<th>Name</th>"
					+ "<th>Lname</th>"
					+ "<th>Email</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></thead>";
	 
			String query = "select * from user"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String Id = Integer.toString(rs.getInt("Id")); 
				String userId = rs.getString("userId");
				String name = rs.getString("name");
				String lname = rs.getString("lname");
				String email = rs.getString("email");
				

				// Add into the HTML table 
				output += "<tr><td><input id='hidUserIDUpdate' "
						+ "name='hidUserIDUpdate' "
						+ "type='hidden' value='" + Id + "'>" 
						+ userId + "</td>"; 
				output += "<td>" + name + "</td>";
				output += "<td>" + lname + "</td>";
				output += "<td>" + email + "</td>";

				// buttons     
//				output += "<td><input name='btnUpdate' type='button'"
//						+ "value='Update' class='btnUpdate btn btn-secondary'></td>"
//						+ "<td><form method='post' action='Payment.jsp'>"
//						+ "<input name='btnRemove' type='submit'"
//						+ "value='Remove' class='btnRemove btn btn-danger'>"
//						+ "<input name='hidPaymentIDDelete' type='hidden'"
//						+ "value='" + payId + "'>" + "</form></td></tr>"; 
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-payid='" + Id + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-payid='" + Id + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//Add details about the payment
	public String insertUser(String userId, String name, String lname, String email)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting.";
			} 
	 
			// create a prepared statement 
			String query = " insert into user (Id , userId , name , lname , email)"+ " values (?, ?, ?, ?, ?)"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, userId);
			 preparedStmt.setString(3, name);
			 preparedStmt.setString(4, lname);
			 preparedStmt.setString(5, email);
			
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newUser = readUser(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Payment.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updateUser(String Id, String userId, String name, String lname, String email)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for updating.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE user SET userId=?,name=?,lname=?,email=? WHERE Id=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, userId);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, email);
			preparedStmt.setInt(7, Integer.parseInt(Id)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deleteUser(String Id)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "delete from user where Id=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(Id)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
