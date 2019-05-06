package dao;

import model.Login;
import java.sql.*;
public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
//	public static void main(String[] args) {
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
//			String query = "select role from User where username =? and password=?";
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setString(1, "jiar@gmail.com");
//			ps.setString(2, "123456");
////        ps.setString(3, "Customer");
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				System.out.println(rs.getString("role"));}
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
//			String query = "INSERT INTO User VALUES (?, ?, ?)";
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setString(1, "Karin@gmail.com");
//			ps.setString(2, "12345");
//			ps.setString(3, "Manager");
//			ps.execute();
//		} catch (ClassNotFoundException e){
//			e.printStackTrace();
//		}catch (SQLException s){
//			s.printStackTrace();
//		}
//	}

	public Login login(String username, String password, String role) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */
		Login login = new Login();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
//			final String SQL_USERNAME = "jiarchen";
//			final String SQL_PASSWORD = "Cjr19971117";
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
//			Uncomment the query at next line and comment the current using one to chek login
			String query = "SELECT * FROM User WHERE username=? and password=? and role=?";
//			String query = "SELECT * FROM proj.User WHERE user.Username=? and user.Password=? and user.role=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, role);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("User Exist");
				login.setUsername(username);
				login.setPassword(password);
				login.setRole(role);

			}else {
				login = null;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return login;
	}

	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
			String query = "INSERT INTO User VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());
			ps.setString(3, login.getRole());
			ps.execute();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return "failure";
		}catch (SQLException s){
			s.printStackTrace();
            return "failure";
        }
		return "success";
	}

	public String addUser(String username, String password, String role) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
			String query = "INSERT INTO User VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, role);
			ps.execute();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
            return "failure";
        }catch (SQLException s){
			s.printStackTrace();
            return "failure";
        }
		return "success";
	}

}
