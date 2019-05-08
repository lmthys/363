package p2;

import java.sql.*;

public class P3 {
	
	public static void main(String[] args) throws Exception {
		// Load and register a JDBC driver
		try {
			// Load the driver (registers itself)
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception E) {
			System.err.println("Unable to load driver.");
			E.printStackTrace();
		}
		try {
			// Connect to the database
			Connection conn1;
			String dbUrl = "jdbc:mysql://csdb.cs.iastate.edu:3306/db363lmandrew";
			String user = "dbu363lmandrew";
			String password = "3QAs4vAq";
			conn1 = DriverManager.getConnection(dbUrl, user, password);
			System.out.println("*** Connected to the database ***");

			// Create Statement and ResultSet variables to use throughout the project
			Statement statement = conn1.createStatement();
			ResultSet rs = null;
			statement.executeUpdate("set SQL_SAFE_UPDATES = 0");
			// get salaries of all instructors
			System.out.println("Step A");
			stepA(rs, statement);
			System.out.println("");
			// Step B 
			System.out.println("Step B");
			statement.executeUpdate("create table MeritList (" + 
					"StudentID char(9)," + 
					"Classification varchar(10)," + 
					"GPA double," + 
					"MentorID char(9)," + 
					"CreditHours int )");
			//Get top 20 students by GPA 
			rs = statement.executeQuery("select * from Student s, Person p where s.StudentID = p.ID order by s.GPA DESC");
			PreparedStatement ps = conn1.prepareStatement("insert MeritList (Classification, GPA, MentorID, CreditHours, StudentID) values (?, ?, ?, ?, ?)");
					
			double GPA = 0.0;
			double lastGPA = 0.0;
			int i = 0;
			while(rs.next()) {
				GPA = rs.getDouble("GPA");
				if(i == 20 && (Double.compare(GPA, lastGPA)==0)) {
					i--;
				}
				if(i != 20) {
					System.out.println("GPA: "+GPA); // put them into DB
					ps.setString(1,rs.getString("Classification"));
					ps.setDouble(2,GPA);
					ps.setInt(3,rs.getInt("MentorID"));
					ps.setInt(4, rs.getInt("CreditHours"));
					ps.setInt(5, rs.getInt("StudentID"));
					ps.executeUpdate();
					lastGPA = GPA;
					i++;
				}
				
				
				
			}
			System.out.println("");
			// Part C 
			//Print out MeritList
			System.out.println("Step C");
			rs = statement.executeQuery("select * from MeritList m order by m.GPA");

			while (rs.next()) {
				//get value of salary from each tuple
				System.out.println("Classification: "+rs.getString("Classification"));
				System.out.println("GPA: "+rs.getDouble("GPA"));
				System.out.println("MentorID: "+rs.getInt("MentorID"));
				System.out.println("CreditHours: "+rs.getInt("CreditHours"));
				System.out.println("StudentID: "+rs.getInt("StudentID"));
				System.out.println("");
				
			}
			System.out.println("");
			// Step D 
			System.out.println("Step D");
			rs = statement.executeQuery("select * from MeritList ml, Instructor i where ml.MentorID = i.InstructorID order by ml.MentorID");
			PreparedStatement ps2 = conn1.prepareStatement("update Instructor i " + "set i.Salary = i.Salary * ? " + "where i.InstructorID = ?");
			
			int mentorID = 0; 
			int lastMentor = 0;
			String classification = "";
			double lastR = 0.0;
			double raise = 0;
			double given = 0.0;
			double maxRaise = 0.0;
			
			while(rs.next()) {
				//select mentor tuple and update salary
				mentorID = rs.getInt("MentorID");
				classification = rs.getString("Classification");
				if(classification.equals("Freshman")) {
					raise = 1.04;
				}
				else if(classification.equals("Sophomore")) {
					raise = 1.06;
				}
				else if(classification.equals("Junior")) {
					raise = 1.08;
				}
				else if(classification.equals("Senior")) {
					raise = 1.1;
				}
				if(lastMentor == mentorID) {
					if(raise > maxRaise) {
						maxRaise = raise;
					}
				}
				if(lastMentor != mentorID) {
					if(lastMentor == 0) {
						lastMentor = mentorID; 
					}
					System.out.println(mentorID + ": Gets a " + raise + " raise");
					ps2.setDouble(1, raise);
					ps2.setInt(2, lastMentor);
					ps2.executeUpdate();
				}
				// Give them a raise 
				
				// Then set the variables to check
				lastMentor = mentorID;
				lastR = raise;
				
				
			}
			System.out.println("");
			// Step E
			System.out.println("Step E");
			stepA(rs, statement);
			System.out.println("");
			
			//Step F
			// Close all statements and connections
			statement.executeUpdate("DROP TABLE MeritList");
			ps.close();
			ps2.close();
			statement.close();
			rs.close();
			conn1.close();

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	private static void stepA(ResultSet rs, Statement statement) throws SQLException {
		// Step A
		rs = statement.executeQuery("select * from Instructor i, Person p where i.InstructorID = p.ID");

		int totalSalary = 0;
		int salary;

		while (rs.next()) {
			//get value of salary from each tuple
			salary = rs.getInt("Salary");
			System.out.println("Name: " + rs.getString("Name")+"\n"+"Salary: "+salary);
			totalSalary += salary;	
			
		}
		System.out.println("Total Salary of all faculty: "+totalSalary);
	}
}


