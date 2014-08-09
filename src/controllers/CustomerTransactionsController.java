package controllers;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerTransactionsController extends DBController {
	private Connection con = connect();

	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS customer_transactions (id int(10) UNSIGNED AUTO_INCREMENT," +
					" customer_id int(10), sales_transaction_id int(10), total double, amount_paid double, " +
					"balance_owing double, created_at datetime, updated_at datetime, primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
