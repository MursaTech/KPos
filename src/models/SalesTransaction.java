package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.SalesTransactionsController;

public class SalesTransaction {
	
	private double totalAmount;
	private double amountPaid;
	private double balance;
	private String methodOfPayment;
	private double discount;
	private String paymentId;
	private static SalesTransactionsController controller = new SalesTransactionsController();
	
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
	public String getMethodOfPayment() {
		return methodOfPayment;
	}
	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("sales_transactions", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("sales_transactions", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("sales_transactions", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("sales_transactions", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("sales_transactions");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("sales_transactions", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("sales_transactions", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("sales_transactions");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("sales_transactions", id, value);
	}
	
	// Associations
	
	public static List<Map<String, String>> sales(String salesTransactionId) {
		return Sale.where("sale_transaction_id", salesTransactionId);		
	}
	
	public static Map<String, String> user(String user_id) {
		return User.find(user_id);
	}
	
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}