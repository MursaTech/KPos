package controllers;

import java.sql.*;

public class SubCategoriesController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS sub_categories (id int(10) UNSIGNED AUTO_INCREMENT," +
					" name varchar(45), category_id int(10), created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}
}
