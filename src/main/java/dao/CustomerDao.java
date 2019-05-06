package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Location;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
//    public static void main(String[] args) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
//			Statement st = con.createStatement();
//			String query = "SELECT ClientId, AccountId, LocationId FROM name WHERE SSN = ?";
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setString(1, "888888888");
//			ResultSet rs = ps.executeQuery();
//			String clientId ="";
//			int AccountId = 0;
//			int LocationId = 0;
//			if (rs.next()) {
//				clientId = Integer.toString(rs.getInt("ClientId"));
//				AccountId = rs.getInt("AccountId");
//				LocationId = rs.getInt("LocationId");
//				System.out.println(clientId+" "+AccountId+" "+LocationId);
//			}
//
//		}catch(Exception e){
//			System.out.println(e);
//		}
//    }

    public Customer getDummyCustomer() {
        Location location = new Location();
        location.setZipCode(11790);
        location.setCity("Stony Brook");
        location.setState("NY");

        Customer customer = new Customer();
        customer.setId("111-11-1111");
        customer.setAddress("123 Success Street");
        customer.setLastName("Lu");
        customer.setFirstName("Shiyong");
        customer.setEmail("shiyong@cs.sunysb.edu");
        customer.setLocation(location);
        customer.setTelephone("5166328959");
        customer.setCreditCard("1234567812345678");
        customer.setRating(1);
        return customer;
    }

    public List<Customer> getDummyCustomerList() {
        /*Sample data begins*/
        List<Customer> customers = new ArrayList<Customer>();

        for (int i = 0; i < 10; i++) {
            customers.add(getDummyCustomer());
        }
		/*Sample data ends*/

        return customers;
    }

    /**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword and returns it as an ArrayList
		 */
		return getAllCustomers();
//		List<Customer> customers = new ArrayList<Customer>();
//		/*
//		 * The students code to fetch data from the database based on searchKeyword will be written here
//		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
//		 */
//		try{
//		    Class.forName("com.mysql.jdbc.Driver");
//		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen","Cjr19971117");
//            Statement st = (Statement) con.createStatement();
//            String query = "SELECT*FROM name";
//            PreparedStatement ps = con.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                Location location = new Location();
//                Customer customer=new Customer();
//                customer.setClientId(Integer.toString(rs.getInt("ClientId")));
//                customer.setFirstName(rs.getString("FirstName"));
//                customer.setLastName(rs.getString("LastName"));
//                customer.setAddress(rs.getString("Address"));
//                location.setCity(rs.getString("City"));
//                location.setState(rs.getString("State"));
//                location.setZipCode(rs.getInt("Zipcode"));
//                customer.setLocation(location);
//                customer.setCreditCard(rs.getString("CreditCardNumber"));
//                customer.setTelephone(rs.getString("Telephone"));
//                customer.setEmail(rs.getString("Email"));
//                customer.setRating(rs.getInt("Rating"));
//                customers.add(customer);
//                    }
//		}catch(Exception e){
//		    System.out.println(e);
//		}return customers;
//		return getDummyCustomerList();
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		Customer customer = new Customer();
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen","Cjr19971117");
		    Statement st = (Statement) con.createStatement();
		    ResultSet rs = st.executeQuery("select *from highestcrevenue");
		    if(rs.next()) {
		        Location location = new Location();
		        customer.setClientId(Integer.toString(rs.getInt("ClientId")));
		        customer.setFirstName(rs.getString("FirstName"));
		        customer.setLastName(rs.getString("LastName"));
		        customer.setAddress(rs.getString("Address"));
				customer.setSsn(rs.getString("SSN"));
		        location.setCity(rs.getString("City"));
		        location.setState(rs.getString("State"));
		        location.setZipCode(rs.getInt("Zipcode"));
		        customer.setLocation(location);
		        customer.setCreditCard(rs.getString("CreditCardNumber"));
		        customer.setTelephone(rs.getString("Telephone"));
		        customer.setEmail(rs.getString("Email"));
		        customer.setRating(rs.getInt("Rating"));
		    }
		} catch (ClassNotFoundException e){
		    e.printStackTrace();
		}catch (SQLException s){
		    s.printStackTrace();
		}
		return customer;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it
		 * customerID, which is the Customer's ID who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		Customer customer = new Customer();
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen","Cjr19971117");
		    Statement st = (Statement) con.createStatement();
		    String query = "SELECT*FROM name WHERE ClientId = ?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, Integer.parseInt(customerID));
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()){
                Location location = new Location();
                customer.setClientId(Integer.toString(rs.getInt("ClientId")));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setAddress(rs.getString("Address"));
				customer.setSsn(rs.getString("SSN"));
                location.setCity(rs.getString("City"));
                location.setState(rs.getString("State"));
                location.setZipCode(rs.getInt("Zipcode"));
                customer.setLocation(location);
                customer.setCreditCard(rs.getString("CreditCardNumber"));
                customer.setTelephone(rs.getString("Telephone"));
                customer.setEmail(rs.getString("Email"));
                customer.setSsn(rs.getString("SSN"));
                customer.setRating(rs.getInt("Rating"));
            }
		} catch (ClassNotFoundException e){
		    e.printStackTrace();
		}catch (SQLException s){
		    s.printStackTrace();
		}
		return customer;
	}
	
	public String deleteCustomer(String customerID) {
		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * customerID, which is the Customer's ID who's details have to be deleted, is given as method parameter
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
			Statement st = con.createStatement();
			String query = "SELECT AccountId, LocationId, SSN FROM name WHERE ClientId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(customerID));
			ResultSet rs = ps.executeQuery();
			String ssn = "";
			int AccountId = 0;
			int LocationId = 0;
			if (rs.next()) {
				ssn = rs.getString("SSN");
				LocationId = rs.getInt("LocationId");
				AccountId = rs.getInt("AccountId");
			}
			String query_3 = "delete from Portfolio where AccountId =?";
			PreparedStatement ps_3 = con.prepareStatement(query_3);
			ps_3.setInt(1, AccountId);
			ps_3.execute();
			String query_4 = "delete from Account where ClientId =?";
			PreparedStatement ps_4 = con.prepareStatement(query_4);
			ps_4.setInt(1, Integer.parseInt(customerID));
			ps_4.execute();
			String query_0 = "delete from Client where SSN =?";
			PreparedStatement ps_0 = con.prepareStatement(query_0);
			ps_0.setString(1, ssn);
			ps_0.execute();
			String query_2 = "delete from Person where SSN =?";
			PreparedStatement ps_2 = con.prepareStatement(query_2);
			ps_2.setString(1, ssn);
			ps_2.execute();
			String query_1 = "delete from Location where LocationId =?";
			PreparedStatement ps_1 = con.prepareStatement(query_1);
			ps_1.setInt(1, LocationId);
			ps_1.execute();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
			return "failure";
		}catch (SQLException s){
			s.printStackTrace();
			return "failure";
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
		
	}


	public String getCustomerID(String email) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID is required to be returned as a String
		 */
        String customerId = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "select C.ClientId from Person P, Client C where P.SSN=C.SSN AND P.Email = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                customerId = Integer.toString(rs.getInt("ClientId"));
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return customerId;
//        return "1";
	}

	public String addCustomer(Customer customer) {
		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		Location location = customer.getLocation();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen","Cjr19971117");
			String query = "SELECT `AUTO_INCREMENT` as LocationId\n" +
					"FROM  INFORMATION_SCHEMA.TABLES\n" +
					"WHERE TABLE_SCHEMA = 'jiarchen'\n" +
					"AND   TABLE_NAME   = 'Location'";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			int locationId = 0;
			if(rs.next()){
				locationId = rs.getInt("LocationId");
			}
			String query_2 = "Insert into Location(zipcode, city, state) Values (?, ?, ?)";
			PreparedStatement ps_2 = con.prepareStatement(query_2);
			ps_2.setInt(1, location.getZipCode());
			ps_2.setString(2,location.getCity());
			ps_2.setString(3,location.getState());
			ps_2.execute();
			String query_3 = "Insert into Person(ssn, lastname, firstname, zipcode, telephone, address, email, locationid)\n" +
					"Values (?,?,?,?,?,?,?,?);";
			PreparedStatement ps_3 = con.prepareStatement(query_3);
			ps_3.setString(1, customer.getSsn());
			ps_3.setString(2,customer.getLastName());
			ps_3.setString(3,customer.getFirstName());
			ps_3.setInt(4, location.getZipCode());
			ps_3.setString(5,customer.getTelephone());
			ps_3.setString(6,customer.getAddress());
			ps_3.setString(7, customer.getEmail());
			ps_3.setInt(8, locationId);
			ps_3.execute();
			String query_4 = "SELECT `AUTO_INCREMENT` as ClientId\n" +
					"FROM  INFORMATION_SCHEMA.TABLES\n" +
					"WHERE TABLE_SCHEMA = 'jiarchen'\n" +
					"AND   TABLE_NAME   = 'Client'";
			PreparedStatement ps_4 = con.prepareStatement(query_4);
			ResultSet rs_4 = ps_4.executeQuery();
			int clientId = 0;
			if(rs_4.next()){
				clientId = rs_4.getInt("ClientId");
			}
			customer.setId(Integer.toString(clientId));
			String query_5 = "Insert into Client(ssn, rating, creditcardnumber) Values (?,?,?)";
			PreparedStatement ps_5 = con.prepareStatement(query_5);
			ps_5.setString(1, customer.getSsn());
			ps_5.setInt(2,customer.getRating());
			ps_5.setString(3,customer.getCreditCard());
			ps_5.execute();
			String query_6 = "Insert into Account(datecreated, clientid) Values (default, ?);";
			PreparedStatement ps_6 = con.prepareStatement(query_6);
			ps_6.setInt(1,clientId);
			ps_6.execute();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return "failure";
		}catch (SQLException S){
			S.printStackTrace();
			return "failure";
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		Location location = customer.getLocation();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
			Statement st = con.createStatement();
			String query = "SELECT ClientId, LocationId FROM name WHERE SSN = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, customer.getSsn());
			ResultSet rs = ps.executeQuery();
			int LocationId = 0;
			if (rs.next()) {
				LocationId = rs.getInt("LocationId");
			}
			String query_1 = "update Location set Zipcode=? where LocationId =?";
			PreparedStatement ps_1 = con.prepareStatement(query_1);
			ps_1.setInt(1, location.getZipCode());
			ps_1.setInt(2, LocationId);
			ps_1.execute();
			String query_2 = "update person set FirstName=? where ssn =?";
			PreparedStatement ps_2 = con.prepareStatement(query_2);
			ps_2.setString(1, customer.getFirstName());
			ps_2.setString(2, customer.getSsn());
			ps_2.execute();
			String query_3 = "update person set LastName=? where ssn =?";
			PreparedStatement ps_3 = con.prepareStatement(query_3);
			ps_3.setString(1, customer.getLastName());
			ps_3.setString(2, customer.getSsn());
			ps_3.execute();
			String query_4 = "update person set Email=? where ssn =?";
			PreparedStatement ps_4 = con.prepareStatement(query_4);
			ps_4.setString(1, customer.getEmail());
			ps_4.setString(2, customer.getSsn());
			ps_4.execute();
			String query_5 = "update person set Telephone=? where ssn =?";
			PreparedStatement ps_5 = con.prepareStatement(query_5);
			ps_5.setString(1, customer.getTelephone());
			ps_5.setString(2, customer.getSsn());
			ps_5.execute();
			String query_6 = "update Client set CreditCardNumber=? where SSN =?";
			PreparedStatement ps_6 = con.prepareStatement(query_6);
			ps_6.setString(1, customer.getCreditCard());
			ps_6.setString(2, customer.getSsn());
			ps_6.execute();
			String query_7 = "update person set Zipcode=? where ssn =?";
			PreparedStatement ps_7 = con.prepareStatement(query_7);
			ps_7.setInt(1, location.getZipCode());
			ps_7.setString(2, customer.getSsn());
			ps_7.execute();
			String query_8 = "update Location set City=? where LocationId =?";
			PreparedStatement ps_8 = con.prepareStatement(query_8);
			ps_8.setString(1, location.getCity());
			ps_8.setInt(2, LocationId);
			ps_8.execute();
			String query_9 = "update Location set State=? where LocationId =?";
			PreparedStatement ps_9 = con.prepareStatement(query_9);
			ps_9.setString(1, location.getState());
			ps_9.setInt(2, LocationId);
			ps_9.execute();
			String query_0 = "update Client set Rating=? where SSN =?";
			PreparedStatement ps_0 = con.prepareStatement(query_0);
			ps_0.setInt(1, customer.getRating());
			ps_0.setString(2, customer.getSsn());
			ps_0.execute();
			String query_a = "update person set Address=? where ssn =?";
			PreparedStatement ps_a = con.prepareStatement(query_a);
			ps_a.setString(1, customer.getAddress());
			ps_a.setString(2, customer.getSsn());
			ps_a.execute();
		}catch(Exception e){
		    System.out.println(e);
		}
		/*Sample data begins*/
        return "success";
		/*Sample data ends*/

	}

    public List<Customer> getCustomerMailingList() {
		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 */
		List<Customer> customers = new ArrayList<Customer>();
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen","Cjr19971117");
		    Statement st = (Statement) con.createStatement();
		    ResultSet rs = st.executeQuery("select * from name");
		    while(rs.next()){
                Customer customer = new Customer();
                Location location = new Location();
				customer.setSsn(rs.getString("SSN"));
                customer.setLastName(rs.getString("LastName"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setAddress(rs.getString("Address"));
                customer.setCity(rs.getString("City"));
                customer.setState(rs.getString("State"));
                customer.setZipCode(rs.getString("Zipcode"));
                customer.setEmail(rs.getString("Email"));
                location.setCity(rs.getString("City"));
                location.setState(rs.getString("State"));
                location.setZipCode(rs.getInt("Zipcode"));
                customer.setLocation(location);
                customers.add(customer);
		    }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
		return customers;
    }

    public List<Customer> getAllCustomers() {
        /*
		 * This method fetches returns all customers
		 */
        List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database based on searchKeyword will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		try{
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen","Cjr19971117");
		    Statement st = (Statement) con.createStatement();
		    ResultSet rs = st.executeQuery("select *from name");
		    while(rs.next()){
                Location location = new Location();
                Customer customer=new Customer();
                customer.setClientId(Integer.toString(rs.getInt("ClientId")));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
				customer.setSsn(rs.getString("SSN"));
                customer.setAddress(rs.getString("Address"));
                location.setCity(rs.getString("City"));
                location.setState(rs.getString("State"));
                location.setZipCode(rs.getInt("Zipcode"));
                customer.setLocation(location);
                customer.setCreditCard(rs.getString("CreditCardNumber"));
                customer.setTelephone(rs.getString("Telephone"));
                customer.setEmail(rs.getString("Email"));
                customer.setRating(rs.getInt("Rating"));
		        customers.add(customer);
		    }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
	return customers;
    }
}
