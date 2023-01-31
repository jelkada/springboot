package com.jelkada.one2one;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one?useSSL=false";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try {
			System.out.println("Connecting to DB: " + jdbcUrl);
			Connection mysqlConnection = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("mysqlConnection: " + mysqlConnection);
		}
		catch (Exception exc) {
			exc.printStackTrace();
			
		}	
	}
}
