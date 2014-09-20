package controllers;

import java.sql.*;

public class UsersController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS users (id int(10) UNSIGNED AUTO_INCREMENT," +
					" first_name varchar(45), last_name varchar(45), user_name varchar(45), password varchar(45), type varchar(45)," +
					" phone_number varchar(45), email varchar(45), profile_picture blob, created_at datetime, updated_at datetime, " +
					"primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {e.printStackTrace();}
	}
}
