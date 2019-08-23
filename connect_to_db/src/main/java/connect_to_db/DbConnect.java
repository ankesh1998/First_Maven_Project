package connect_to_db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DbConnect
 */

public class DbConnect extends HttpServlet {
	
	
	
	private Statement stmt;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("Id"));
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 System.out.println("Connecting to a selected database...");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","2015");
			
			  System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      String sql = ("SELECT * FROM admin where admin_id="+id);
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id1  = rs.getInt("admin_id");
		         String contact = rs.getString("contact");
		         String first = rs.getString("firstname");
		         String email_id = rs.getString("email_id");

		         //Display values
		         PrintWriter pw=response.getWriter();
		         pw.println("ID: " + id1);		       
		         pw.print(",Name: " + first);
		         pw.println(",Contact: " + contact);
		         pw.println(",Email-id: " + email_id);
		      }

		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		
	}

}
