package admin.model;
//IT19058160
//name : W.M.C.S Bandara
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import user.util.Helper;


public class Admin {//public class name CustomerService which implements ICustomer
	//declaring
	//public static final Logger log = Logger.getLogger(AppProperties.class.getName());
	

	private static Statement stmt = null;
	
	private static PreparedStatement pmt=null;
	
	private static Connection con=null;
	

	
	public static Connection getConnecton() {
		//implement methods
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/assigment", "root", ""); 
			//For testing
			System.out.print("Successfully connected"); 
			
			
		}
		catch(Exception e)
		{
			System.out.println("Database connection is not success!");
			//log.log(Level.SEVERE, e.getMessage());
		}
		return con;
	}
	
	
   
    public String insertCustomerCare(String from,String to,String subject ,String message){/*
    	//method implementation
    	
    	
    	String id = Helper.generateStudentIDs(getStudentId());
    	
		boolean isSuccess=false;
		
		
		//check if there are any errors
		try {
			//database connection
			con = getConnecton();
    		stmt = con.createStatement();
			String sql = "insert into sendmessage values('"+id+"','"+from+"' , '"+to+"','"+subject+"','"+message+"')";
			int rs=stmt.executeUpdate(sql);
			
			if(rs > 0) {//if condition
				isSuccess = true;
				
			}
			else {
				isSuccess = false;
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			//log.log(Level.SEVERE, e.getMessage());
		}
		
		return isSuccess;
		*/
    	
    	 String output = ""; 
		 try
		 { 
		 Connection con = getConnecton(); 
		 if (con == null) 
		 { 
		 return "Error while connecting  to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into sendmessage(`id`,`fromm`,`too`,`subject`,`message`)"
		+ " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, from); 
		 preparedStmt.setString(3, to); 
		 preparedStmt.setString(4,subject); 
		 preparedStmt.setString(5, message); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = displaysendmessage(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output;
	}
    
    private ArrayList<String> getStudentId() {

    	ArrayList<String> arrayLst1= new ArrayList<String>();//declearing
    	 
    	try {
    		 con = getConnecton();//Database connection
    	
    		 pmt=con.prepareStatement("select id from sendmessage");
    		 ResultSet rs = pmt.executeQuery();
    		 
    		 while(rs.next())//while loop
    		 {
    			 arrayLst1.add(rs.getString("id"));
    		 }
    	}catch(SQLException e) {
    		//log.log(Level.SEVERE, e.getMessage());
    	}
    	finally 
    	{
    		try 
    		{
    			if(pmt != null)
    			 {
    				 pmt.close();
    			 }
    			 if(con != null)
    			 {
    				 con.close();
    			 }
    		}
    		catch(SQLException e)
    		{
    			//log.log(Level.SEVERE, e.getMessage());
    		}
    	}
    	return arrayLst1;//return value
    }
    	 
    
  
    
  
 
    
	public String deletemessage(String id) { 
		 String output = ""; 
		 try
		 { 
		 Connection con = getConnecton(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from receivemessage where id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(id)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = displaysendmessage(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
	
	
	
	public String displayreceivemessage() 
	 { /*
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
 		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>#</th><th>Name</th><th>Subject</th>" +
	 "<th>Designation</th>" + 
	 "<th>Email</th>" +
	 "<th>Message</th></tr>"; 
	 
	 String query = "select * from receivemessage"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String subject = rs.getString("subject"); 
	 String designation = rs.getString("designation"); 
	 String email = rs.getString("email"); 
	 String message = rs.getString("message"); 
	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + subject + "</td>"; 
	 output += "<td>" + designation + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + message + "</td></tr>"; 
	 // buttons
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output;*/
		
		String output = ""; 
		try
		 { 
		 Connection con = getConnecton(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>ID</th>" 
		 + "<th>Name</th><th>Subject</th>"
		 + "<th>Designation</th>" 
		 + "<th>Message</th>" 
		 + "<th>Remove</th></tr>"; 
		 String query = "select * from receivemessage"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String itemID = Integer.toString(rs.getInt("id")); 
		 String itemCode = rs.getString("name"); 
		 String itemName = rs.getString("subject"); 
		 String itemPrice = rs.getString("designation"); 
		 String itemDesc = rs.getString("email"); 
		 String itemDesc2 = rs.getString("message");
		// Add into the html table
		 output += "<tr><td>" + itemCode + "</td>"; 
		 output += "<td>" + itemName + "</td>"; 
		 output += "<td>" + itemPrice + "</td>"; 
		 output += "<td>" + itemDesc + "</td>"; 
		 output += "<td>" + itemDesc2 + "</td>"; 
		// buttons
		output +="<td><input name='btnRemove2' type='button' value='Remove' "
		+ "class='btnRemove2 btn btn-danger' data-itemid='" + itemID + "'></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output;
	 } 
	
	
	
	
	
	
	
	public String displaysendmessage() 
	 { 
		/*
	 String output = ""; 
	 try
	 { 
		 con = getConnecton();
		stmt = con.createStatement();
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>id</th><th>from</th><th>to</th>" +
	 "<th>subject</th>" + 
	 "<th>message</th></tr>"; 
	 
	 String query = "select * from sendmessage"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String from = rs.getString("fromm"); 
	 String to = rs.getString("too"); 
	 String subject = rs.getString("subject"); 
	 String message = rs.getString("message"); 

	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + from + "</td>"; 
	 output += "<td>" + to + "</td>"; 
	 output += "<td>" + subject + "</td>"; 
	 output += "<td>" + message + "</td></tr>"; 
	 // buttons
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output;*/
		String output = ""; 
		try
		 { 
		 Connection con = getConnecton(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>From</th>" 
		 + "<th>To</th><th>Subject</th>"
		 + "<th>Message</th>" 
		 + "<th>Update</th><th>Remove</th></tr>"; 
		 String query = "select * from sendmessage"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String itemID = Integer.toString(rs.getInt("id")); 
		 String itemCode = rs.getString("fromm"); 
		 String itemName = rs.getString("too"); 
		 String itemPrice = rs.getString("subject"); 
		 String itemDesc = rs.getString("message"); 
		// Add into the html table
		 output += "<tr><td>" + itemCode + "</td>"; 
		 output += "<td>" + itemName + "</td>"; 
		 output += "<td>" + itemPrice + "</td>"; 
		 output += "<td>" + itemDesc + "</td>"; 
		// buttons
		output += "<td><input name='btnUpdate' type='button' value='Update' "
		+ "class='btnUpdate btn btn-secondary' data-itemid='" + itemID + "'></td>"
		+ "<td><input name='btnRemove' type='button' value='Remove' "
		+ "class='btnRemove btn btn-danger' data-itemid='" + itemID + "'></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output;
	 } 
	
	public String updateItem(String ID, String from, String to, 
			 String subject, String message) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = getConnecton(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE sendmessage SET fromm=?,too=?,subject=?,message=? WHERE id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, from); 
			 preparedStmt.setString(2, to); 
			 preparedStmt.setString(3, subject); 
			 preparedStmt.setString(4, message); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = displaysendmessage(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
			public String deleteItem(String id) 
			 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = getConnecton(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for deleting."; 
			 } 
			 // create a prepared statement
			 String query = "delete from sendmessage where id=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = displaysendmessage(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 }
	
}
