package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.PayrollsController;

public class Payroll {
	private int userId;
	private double salary;
	private String frequency;
	private double commission;
	private static PayrollsController controller = new PayrollsController();
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}	
	
	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("payrolls", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("payrolls", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("payrolls", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("payrolls", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("payrolls");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("payrolls", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("payrolls", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("payrolls");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("payrolls", id, value);
	}
	
	// Associations
	
	public static Map<String, String> user(String id) {
		String userId = find(id).get("user_id");
		return User.find(userId);
		
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
