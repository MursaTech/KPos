package controllers;

import java.sql.*;

public class ExpensesController extends DBController {
	private Connection con = connect();
	
	public void createTable() {
		try {
			con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS expenses (id int(10) UNSIGNED AUTO_INCREMENT, category_id int(10)," +
					" sub_category_id int(10), amount double, created_at datetime, updated_at datetime, expense_transaction_id int(10), primary key(id))ENGINE = InnoDB");
		} catch (SQLException e) {}
	}
}
