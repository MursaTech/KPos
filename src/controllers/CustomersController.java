package controllers;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomersController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS customers (id int(10) UNSIGNED AUTO_INCREMENT," +
					" id_number varchar(45), full_name varchar(45), address varchar(45), allowed_limit double, due_date datetime, " +
					"total_owing double, created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
