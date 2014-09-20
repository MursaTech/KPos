package controllers;

import java.sql.*;

public class SalesController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS sales (id int(10) UNSIGNED AUTO_INCREMENT, stock_id int(10)," +
					" quantity int(10), price double, total_amount double, VAT double, approved varchar(45), sales_transaction_id int(10)," +
					" created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}
}
