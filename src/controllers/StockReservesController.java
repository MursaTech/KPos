package controllers;

import java.sql.*;

public class StockReservesController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS stock_reserves (id int(10) UNSIGNED AUTO_INCREMENT, name varchar(45), quantity int(10), " +
					"units varchar(45), buying_price double, selling_price double, created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}
}
