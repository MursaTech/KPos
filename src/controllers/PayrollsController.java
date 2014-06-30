package controllers;

import java.sql.*;

public class PayrollsController extends DBController {
	private Connection con = connect();
	
	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS payrolls (id int(10) UNSIGNED AUTO_INCREMENT," +
					" user_id int(10), salary double, frequency varchar(45), commission double, created_at datetime, " +
					"updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}
}
