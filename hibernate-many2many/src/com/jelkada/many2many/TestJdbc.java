package com.jelkada.many2many;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-many-to-many?useSSL=false";
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
