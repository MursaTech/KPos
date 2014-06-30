package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.ExpenseTransactionsController;

public class ExpenseTransaction {
	private double totalAmount;
	private double amountPaid;
	private double balance;
	private String time;
	private static ExpenseTransactionsController controller = new ExpenseTransactionsController();
	
	// Getters and Setters
	
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}	
	
	// CRUD Operations
	
	public static Map<String, String> find(String id) {
		return controller.show("expense_transactions", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("expense_transactions", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("expense_transactions", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("expense_transactions", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("expense_transactions");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("expense_transactions", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("expense_transactions", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("expense_transactions");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("expense_transactions", id, value);
	}
	
	// Associations
	
	public static List<Map<String, String>> expenses(String expenseTransactionId) {
		return Expense.where("expense_transaction_id", expenseTransactionId);		
	}
	
	public static Map<String, String> user(String user_id) {
		return User.find(user_id);
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
