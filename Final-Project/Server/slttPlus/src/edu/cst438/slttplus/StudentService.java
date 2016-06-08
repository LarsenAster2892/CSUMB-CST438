package edu.cst438.slttplus;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.*;


/**
 * Servlet implementation class StudentService
 */
@WebServlet("/students/*")
public class StudentService extends HttpServlet {

		private static final long serialVersionUID = 1L;
		
	    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	    static final String DB_URL="jdbc:mysql://localhost:3306/sltt";
	    static final String USER = "root";
	    static final String PASS = "win4dowU";
	    
	    {
	    	try {
	    		
			// Register JDBC driver
	         Class.forName("com.mysql.jdbc.Driver");
	         
	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}

	    }
	   
	    public StudentService() {
	        super();
	    }

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// the context URL should be /slttPlus/students/*
			response.setContentType("text/plain"); 
			int studentId = parseURL( request.getRequestURI()) ;
			
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			PrintWriter out = response.getWriter();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
			if (studentId != 0) {
				Student s = getStudent(studentId);
				if (s == null)
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				else 
					gson.toJson(s, out);
			} else {
				//ProjectList crimes = getAllProjects();
				//gson.toJson(crimes, out);
			}
			out.close();	
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// update an existing Student.  Data is in the message body in JSON form.
			InputStreamReader in = new InputStreamReader(request.getInputStream());
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			Student s = gson.fromJson(in,  Student.class);
			updateStudent(s);
		}
		
		@Override
		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int studentId = parseURL( request.getRequestURI()) ;
				deleteStudent(studentId);
		}
		
		@Override
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// insert a new crime
			InputStreamReader in = new InputStreamReader(request.getInputStream());
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			Student s = gson.fromJson(in,  Student.class);
			insertStudent(s);
		}
		
		private int parseURL(String url) throws ServletException {
			StringTokenizer st = new StringTokenizer(url, "/"); 
			st.nextToken();  // skip over project name
			st.nextToken();  // skip over "crimes"  
			if (st.hasMoreTokens()){
				// get the {id} convert to int.
				return Integer.parseInt( st.nextToken() );
			} else {
			   return 0;  // there is no {id} 
			}
		}
		
		private Student getStudent (int studentId) {
			Connection conn = null;
			try {
				conn = getConnection();
		         // Execute SQL query
		         Statement stmt = conn.createStatement();
		         String sql = "SELECT studentId, studentFname, studentLname from students where studentId = "+studentId;
		         //String sqld = "select detail from crimedetails where id="+id;
		         ResultSet rs = stmt.executeQuery(sql);
		         if (rs.next()==false){
		        	 // student not found;
		        	 return null;
		         }
		         // create the crime object 
		         Student s = new Student();
		         s.studentId    = rs.getInt("studentId");
		         s.studentFname = rs.getString("studentFname");
		         s.studentLname = rs.getString("studentLname");
		         rs.close();
		         // get the crime details 
		         //rs = stmt.executeQuery(sqld);
		         //while (rs.next()){
		         //	 c.details.add( rs.getString("detail"));
		         //}
		         //rs.close();
		         stmt.close();
		         conn.commit();
		         conn.close();
		         return s;
			} catch (Exception e ){
				e.printStackTrace();
				return null;
			} 
		}
		
		
		private void insertStudent(Student s){
			Connection conn = null;
			try {
				 conn = getConnection();
		         // Execute SQL query
				 insertStudent(conn, s);
		         conn.commit();
		         conn.close();
			} catch (Exception e ){
				e.printStackTrace();
			} 
		}
		
		private void insertStudent(Connection conn, Student s){
			try {
		         // Execute SQL query
		         Statement stmt = conn.createStatement();
		         String sql = "INSERT INTO students values ("+ s.studentId +", '" + s.studentFname +"' ,'" + s.studentLname +"')";
		         //String sqld = "insert into crimedetails values (";
		         stmt.executeUpdate(sql);
		         //for (String detail : s.details){
		         //	 String sqlr = sqld+c.id+", '"+detail+"')";
		         //	 stmt.executeUpdate(sqlr);
		         //}
			} catch (Exception e ){
				e.printStackTrace();
			} 
		}
		
		private void deleteStudent(int studentId) {
			Connection conn = null;
			try {
				conn = getConnection();
				deleteStudent(conn, studentId);
		         conn.commit();
		         conn.close();
			} catch (Exception e ){
				e.printStackTrace();
			} 
		}
		
		private void deleteStudent(Connection conn, int studentId){
			try {
		         // Execute SQL query
		         Statement stmt = conn.createStatement();
		         String sql = "DELETE FROM students where id="+studentId;
		         
		         stmt.executeUpdate(sql);
		         stmt.close();
			} catch (Exception e ){
				e.printStackTrace();
			} 
		}
		
		private void updateStudent(Student s){
			Connection conn = null;
			try {
				conn = getConnection();
				deleteStudent(conn, s.studentId);
				insertStudent(conn, s);
		         conn.commit();
		         conn.close();
			} catch (Exception e ){
				e.printStackTrace();
			} 
		}
		
		private Connection getConnection() throws Exception {

	         // Open a connection
	         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         conn.setAutoCommit(false); 
	         return conn;
		}
}