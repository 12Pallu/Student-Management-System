package com.jspiders.jdbc.operations;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInsert3 {
	private static Driver driver;
	private static Connection connection;
	private static Statement statement;
	private static String query;
	public static void main(String[]agrs) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter student id.");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter student name.");
		String name = scanner.nextLine();
		
		System.out.println("Enter student email.");
		String email = scanner.nextLine();
		System.out.println("Enter student age.");
		int age = scanner.nextInt();
		System.out.println("Enter student fees.");
		double fees = scanner.nextDouble();
		scanner.close();

		try {
			connection = openConnection();
			statement = connection.createStatement();
			query = "INSERT INTO student VALUES(" + id + ",'" + name + "'," + age + ",'" + email + "'," + fees + ")";
			int row = statement.executeUpdate(query);
			System.out.println(row + " row(s) affected.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static Connection openConnection() throws SQLException {

		driver = (Driver) new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/weja3", "root", "root");

	}

	private static void closeConnection() throws SQLException {

		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
		DriverManager.deregisterDriver(driver);

	}

}
