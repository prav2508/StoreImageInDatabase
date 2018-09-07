package com.caps.jsp;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.InputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	 
	public class Blob {
	 
	    public static void main(String[] args) throws SQLException {
	        Blob imageTest = new Blob();
	        imageTest.insertImage();
	    }
	 
	    public Connection getConnection() {
	        Connection connection = null;
	 
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/image", "root", "root");
	        } catch (Exception e) {
	            System.out.println("Error Occured While Getting the Connection: - " + e);
	        }
	        return connection;
	    }
	 
	    public void insertImage() {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        FileInputStream inputStream = null;
	 
	        try {
	            File image = new File("C:/honda.jpg"); //Enter the path of image which u want to store.
	            inputStream = new FileInputStream(image);
	 
	            connection = getConnection();
	            statement = connection.prepareStatement("insert into imageinfo(imgcontent) values(?)");
	          
	            statement.setBinaryStream(1, (InputStream) inputStream, (int)(image.length()));
	 
	            statement.executeUpdate();
	 

	        } catch (SQLException e) {
	            System.out.println("SQLException: - " + e);
	        } catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} finally {
	 
	            try {
	                connection.close();
	                statement.close();
	            } catch (SQLException e) {
	                System.out.println("SQLException Finally: - " + e);
	            }
	 
	        }
	 
	    }
	 
	 
	}

