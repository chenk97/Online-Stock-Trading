package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Location;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
  

    public Employee getDummyEmployee()
    {
        Employee employee = new Employee();

        Location location = new Location();
        location.setCity("Stony Brook");
        location.setState("NY");
        location.setZipCode(11790);

		/*Sample data begins*/
        employee.setEmail("shiyong@cs.sunysb.edu");
        employee.setFirstName("Shiyong");
        employee.setLastName("Lu");
        employee.setLocation(location);
        employee.setAddress("123 Success Street");
        employee.setStartDate("2006-10-17");
        employee.setTelephone("5166328959");
        employee.setEmployeeID("631-413-5555");
        employee.setHourlyRate(100);
		/*Sample data ends*/

        return employee;
    }

    public List<Employee> getDummyEmployees()
    {
       List<Employee> employees = new ArrayList<Employee>();

        for(int i = 0; i < 10; i++)
        {
            employees.add(getDummyEmployee());
        }

        return employees;
    }

	public String addEmployee(Employee employee) {
                int locationId = 0;
		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
                try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
			String query = "insert into Location Values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
                        ps.setInt(1, employee.getLocation().getZipCode());
                        ps.setString(2, employee.getLocation().getCity());
                        ps.setString(3, employee.getLocation().getState());
                        ps.setString(4, null);
                        ps.execute();
                        String query_out = ("select LocationId from Location L where L.ZipCode =? and L.City = ? and L.State =?");
                        PreparedStatement ps_out = con.prepareStatement(query_out);
                        ps_out.setInt(1, employee.getLocation().getZipCode());
                        ps_out.setString(2, employee.getLocation().getCity());
                        ps_out.setString(3, employee.getLocation().getState());
                        //int a;
                        ResultSet rs = ps_out.executeQuery();
                        if(rs.next()){
                            locationId = rs.getInt("LocationId");
                        }
                        //int a = rs.getInt("LocationId");
                        String query_2 = "insert into Person Value(?,?,?,?,?,?,?,?)";
                        PreparedStatement ps_2 = con.prepareStatement(query_2);
                        ps_2.setString(1, employee.getSsn());
                        ps_2.setString(2, employee.getLastName());
                        ps_2.setString(3, employee.getFirstName());
                        ps_2.setInt(4, employee.getLocation().getZipCode());
                        ps_2.setString(5, employee.getTelephone());
                        ps_2.setString(6, employee.getAddress());
                        ps_2.setString(7, employee.getEmail());
                        ps_2.setInt(8, locationId);                       
                        ps_2.execute();
                        String query_3 = "insert into Employee Value(?,?,?,?)";
                        PreparedStatement ps_3 = con.prepareStatement(query_3);
                        ps_3.setString(1, employee.getSsn());
                        //ps_3.setString(, employee.getSsn());
                        ps_3.setString(2, employee.getStartDate());
                        ps_3.setInt(3, (int) employee.getHourlyRate());
                        ps_3.setString(4, "Customer Representative");
                        ps_3.execute();
                        
                }catch (Exception e){
			e.printStackTrace();
		}
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */

                 Location a = new Location();


                 try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
			String query = "update Employee E set HourlyRate = ? where E.EmpId = ?";
			PreparedStatement ps = con.prepareStatement(query);       
			//ps.setString(1, employee.getStartDate());
                        ps.setInt(1, (int) employee.getHourlyRate());             
                        ps.setString(2, employee.getSsn());
                        ps.execute();
                        String query_2 = "update Location L set Zipcode = ?, City = ?, State = ? where L.LocationId = ?";
                        PreparedStatement ps_2 = con.prepareStatement(query_2);
                        a = employee.getLocation();
                        //System.out.print(a.getZipCode());
                        ps_2.setInt(1, employee.getLocation().getZipCode());
                        ps_2.setString(2, a.getCity());
                        ps_2.setString(3, a.getState());
                        ps_2.setInt(4, employee.getLocationId());
                        System.out.print(employee.getLocationId());
                        ps_2.execute();
                        String query_3 = "update Person P set Zipcode = ?, LastName = ?, FirstName = ?, Telephone = ?, Address = ?, Email = ? where P.SSN = ?";
                        PreparedStatement ps_3 = con.prepareStatement(query_3);
                        ps_3.setInt(1, a.getZipCode());
                        ps_3.setString(2, employee.getLastName());
                        ps_3.setString(3, employee.getFirstName());
                        ps_3.setString(4, employee.getTelephone());
                        ps_3.setString(5, employee.getAddress());
                        ps_3.setString(6, employee.getEmail());
                        ps_3.setString(7, employee.getSsn());
                        ps_3.execute();                         
                 }catch(Exception e){
                     e.printStackTrace();
                 }
		
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		 try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
			String query = "delete from Employee where EmpId = ?";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, employeeID);
                        ps.execute();
                        String q = "delete from Person where SSN = ?";
                        PreparedStatement ps_2 = con.prepareStatement(q);
                        ps_2.setString(1, employeeID);
                        ps_2.execute();
                 }catch(Exception e){
                     e.printStackTrace();
                 }
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */
		List<Employee> employees = new ArrayList<Employee>();
                //Location location = new Location();
                //System.out.println("23333");
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
                    Statement st = con.createStatement();
                    //ResultSet rs = st.executeQuery("select * from Location");
                  ResultSet rs = st.executeQuery("select distinct * from Employee E, Location L, Person P where E.EmpId = P.SSN and L.LocationId = P.LocationId and P.Zipcode = L.Zipcode");
                     while(rs.next()) {
                        Employee employee = new Employee();
                        Location location = new Location();
                        location.setCity(rs.getString("City"));
                        location.setState(rs.getString("State"));
                        location.setZipCode(rs.getInt("Zipcode"));
                        employee.setLocation(location);
                        employee.setAddress(rs.getString("Address"));
                        employee.setEmployeeID(rs.getString("EmpId"));
                        //System.out.print(rs.getString("EmpId"));
                        //employee.setSsn(rs.getString("SSN"));
                        employee.setEmail(rs.getString("Email"));
                         //employee.setSsn(rs.getString("SSN"));
                         employee.setFirstName(rs.getString("FirstName"));
                         employee.setLastName(rs.getString("LastName"));
                         employee.setHourlyRate(rs.getFloat("HourlyRate"));
                         employee.setStartDate(rs.getString("StartDate"));
                         employee.setTelephone(rs.getString("Telephone"));
                         employee.setLevel(rs.getString("Level"));
                         employee.setLocationId(rs.getInt("LocationId"));
//                         location.setCity(rs.getString("City"));
//                         location.setState(rs.getString("State"));
//                         location.setZipCode(rs.getInt("Zipcode"));
                         employee.setLocation(location);
                         employees.add(employee);
                    }
                 //employees.add(employee);
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }catch (SQLException s){
                    s.printStackTrace();
                }		
		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */
                Employee employee = new Employee();
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
                    Statement st = con.createStatement();
                  ResultSet rs = st.executeQuery("select distinct * from Person,Location,Employee WHERE "
                          + "Person.SSN=Employee.EmpId AND Person.LocationId=Location.LocationId AND Person.SSN="+employeeID);
                     while(rs.next()) {
                        Location location = new Location();
                        location.setCity(rs.getString("City"));
                        location.setState(rs.getString("State"));
                        location.setZipCode(rs.getInt("Zipcode"));
                        location.setLocationId(rs.getInt("LocationId"));
                        employee.setLocation(location);
                        employee.setAddress(rs.getString("Address"));
                        employee.setEmployeeID(rs.getString("EmpId"));
                        //System.out.print(rs.getString("EmpId"));
                        employee.setSsn(rs.getString("SSN"));
                         //System.out.print(rs.getString("EmpId"));
                         employee.setSsn(rs.getString("EmpId"));
                         employee.setId(rs.getString("EmpId"));
                        employee.setEmail(rs.getString("Email"));
                         //employee.setSsn(rs.getString("SSN"));
                         employee.setFirstName(rs.getString("FirstName"));
                         employee.setLastName(rs.getString("LastName"));
                         employee.setHourlyRate(rs.getFloat("HourlyRate"));

                         employee.setStartDate(rs.getString("StartDate"));
                         //System.out.print(rs.getString("StartDate"));
                         employee.setTelephone(rs.getString("Telephone"));
                         //System.out.print(rs.getString("Telephone"));
                         employee.setLevel(rs.getString("Level"));
                         employee.setLocationId(rs.getInt("LocationId"));
//                         location.setCity(rs.getString("City"));
//                         location.setState(rs.getString("State"));
//                         location.setZipCode(rs.getInt("Zipcode"));
                         employee.setLocation(location);
                         
                     }
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }catch (SQLException s){
                    s.printStackTrace();
                }
            return employee;
	}
        
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
        Employee employee = new Employee();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM HighestRevenue");
            while(rs.next()) {
                Location location = new Location();
                location.setCity(rs.getString("City"));
                location.setState(rs.getString("State"));
                location.setZipCode(rs.getInt("Zipcode"));
                location.setLocationId(rs.getInt("LocationId"));
                employee.setLocation(location);
                employee.setAddress(rs.getString("Address"));
                employee.setEmployeeID(rs.getString("Id"));
                //System.out.print(rs.getString("EmpId"));

                employee.setSsn(rs.getString("Id"));
                employee.setId(rs.getString("Id"));
                employee.setEmail(rs.getString("Email"));
                //employee.setSsn(rs.getString("SSN"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setHourlyRate(rs.getFloat("HourlyRate"));
                employee.setStartDate(rs.getString("StartDate"));
                employee.setTelephone(rs.getString("Telephone"));
                employee.setLevel("Customer Representative");
                employee.setLocationId(rs.getInt("LocationId"));
//                         location.setCity(rs.getString("City"));
//                         location.setState(rs.getString("State"));
//                         location.setZipCode(rs.getInt("Zipcode"));
                employee.setLocation(location);

            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */
                String a = "";
                 try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
                    Statement st = con.createStatement();
                    //ResultSet rs = st.executeQuery("select * from Location");
                    String query = ("select P.SSN from Person P where P.Email = ?");
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, username);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()) {
                        a = rs.getString("SSN");
                    }
                    }catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }catch (SQLException s){
                        s.printStackTrace();
                }	
		return a;
	}

}
