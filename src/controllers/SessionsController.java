package controllers;

import java.sql.*;

public class SessionsController extends DBController {
	private Connection con = connect();
	
	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS sessions (id int(10) UNSIGNED AUTO_INCREMENT, user_id int(10)," +
					" client varchar(45), logged_in varchar(45), created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}
}
