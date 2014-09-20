package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.CustomerTransactionsController;

public class CustomerTransaction {
	
	private static CustomerTransactionsController controller = new CustomerTransactionsController();

	public static Map<String, String> find(String id) {
		return controller.show("customer_transactions", id);		
	}

	public static Map<String, String> findBy(String field, String value) {
		return where(field + " = '" + value + "'").get(0);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("customer_transactions", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("customer_transactions", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("customer_transactions", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("customer_transactions");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("customer_transactions", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("customer_transactions", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("customer_transactions");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("customer_transactions", id, value);
	}
	
	public static List<String> columns() {
		return controller.columns("customer_transactions");
	}
	
	// Associations
	
//	public static List<Map<String, String>> transactions(String id) {
//		return SalesTransaction.where("customer_id", id);		
//	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
