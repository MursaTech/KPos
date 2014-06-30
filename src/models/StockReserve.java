package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.StockReservesController;

public class StockReserve {
	private String name;
	private int quantity;
	private String units;
	private double buyingPrice;
	private double sellingPrice;
	private String time;
	private static StockReservesController controller = new StockReservesController();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("stock_reserves", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("stock_reserves", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("stock_reserves", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("stock_reserves", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("stock_reserves");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("stock_reserves", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("stock_reserves", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("stock_reserves");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("stock_reserves", id, value);
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
