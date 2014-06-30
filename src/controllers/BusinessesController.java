package controllers;


import java.sql.*;

public class BusinessesController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS businesses (id int(10) UNSIGNED AUTO_INCREMENT, name varchar(45)," +
					" business_type varchar(45), location varchar(45), logo blob, created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}

}
