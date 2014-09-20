package controllers;

import java.sql.*;

public class CategoriesController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS categories (id int(10) UNSIGNED AUTO_INCREMENT," +
					" name varchar(45), created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}
}
