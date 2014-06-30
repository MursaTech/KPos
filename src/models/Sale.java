package models;

import java.util.*;

import controllers.SalesController;

public class Sale {
	
	private int stockId;
	private int quantity;
	private double price;
	private double totalAmount;
	private String time;
	private int salesTransactionId;
	private static SalesController controller = new SalesController();
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getSalesTransactionId() {
		return salesTransactionId;
	}
	public void setSalesTransactionId(int salesTransactionId) {
		this.salesTransactionId = salesTransactionId;
	}
	
	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("sales", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("sales", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("sales", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("sales", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("sales");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("sales", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("sales", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("sales");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("sales", id, value);
	}

	// Associations
	
	public static Map<String, String> salesTransaction(String id) {
		String salesTransactionId = find(id).get("sales_trasaction_id");
		return SalesTransaction.find(salesTransactionId);
		
	}

	public static Map<String, String> stock(String id) {
		String stockId = find(id).get("stock_id");
		return Stock.find(stockId);
		
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
