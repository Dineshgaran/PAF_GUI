package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
public class FundingServlet {
	
	//Connect to the MySQL DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/test_", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();} 
		 	return con; 
		} 

		public String insertPost(String post_title, String content) 
		 { 
			 String output = ""; 
			 try
			 { 
				 Connection con = connect(); 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for inserting."; 
				 } 
				 
				    LocalDate published_date= LocalDate.now();
					LocalTime published_time= LocalTime.now();
			 	 	 // create a prepared statement
				 	 String query = "INSERT INTO Funding_Bodies_Post(`title`,`content`,`published_date`,`published_time`)" + " VALUES (?, ?, ?, ?)"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 					 
					 
					 // binding values
					 preparedStmt.setString(1, post_title);
					 preparedStmt.setString(2, content);
					 preparedStmt.setString(3, published_date.toString());
					 preparedStmt.setString(4, published_time.toString());
					
					
					 				 
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 String newPost = readPost(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newPost + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
				 System.err.println(e.getMessage());
			 } 
		 	return output; 
		 } 

		//Read Posts
		 public String readPost() 
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
				 output = "<table border='1'><tr><th>Post ID</th>" +
				 "<th>Post Title</th>" +
				 "<th>Content</th>" + 
				 "<th>Published Date</th>" + 
				 "<th>Published Time</th>" +
				 "<th>Update</th><th>Remove</th></tr>"; 
			 
				 
				 String query = "SELECT * FROM Funding_Bodies_Post"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String postID = Integer.toString(rs.getInt("id")); 
					 String postTitle = rs.getString("title"); 
					 String content = rs.getString("content"); 
					 String publishedDate = rs.getString("published_date"); 
					 String publishedTime = rs.getString("published_time"); 
					 
				
					 
					 
					 
					 // Add into the html table
					 output += "<tr><td><input id='hidpostNOUpdate' name='hidpostNOUpdate' type='hidden' value='" + postID
								+ "'>" + postID + "</td>"; 
					 output += "<td>" + postTitle + "</td>"; 
					 output += "<td>" + content + "</td>"; 
					 output += "<td>" + publishedDate + "</td>"; 
					 output += "<td>" + publishedTime + "</td>"; 
					 
					 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-postid='" 
					 + postID + "'>" + "</td></tr>";
				 } 
				 	 con.close(); 
				 	 // Complete the html table
				 	 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the order"; 
				 System.err.println(e.getMessage()); 
			 } 
		 	 return output; 
		 } 
				
		//Update Posts
		 public String updatePost(String post_ID,String post_title,String content) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 LocalDate published_date= LocalDate.now();
		 LocalTime published_time= LocalTime.now();
				 // create a prepared statement
				 String query = "UPDATE Funding_Bodies_Post SET title=? , content=? , published_date=? , published_time=? WHERE id=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setString(1, post_title); 
				 preparedStmt.setString(2, content); 
				 preparedStmt.setString(3, published_date.toString()); 
				 preparedStmt.setString(4, published_time.toString()); 
				 preparedStmt.setInt(5, Integer.parseInt(post_ID)); 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newPost = readPost(); output = "{\"status\":\"success\", \"data\": \"" + newPost+ "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the Post.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 	return output; 
			 } 
		
			//Delete Orders
			 public String deletePost(String postID) 
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
				 String query = "DELETE FROM Funding_Bodies_Post WHERE id=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(postID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newPost = readPost(); output = "{\"status\":\"success\", \"data\": \"" + newPost + "\"}";
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the post.\"}"; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		} 
} 