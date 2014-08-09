package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.CustomersController;

public class Customer {
	// CRUD operations
	
	private static CustomersController controller = new CustomersController();

	public static Map<String, String> find(String id) {
		return controller.show("customers", id);		
	}

	public static Map<String, String> findBy(String field, String value) {
		return where(field + " = '" + value + "'").get(0);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("customers", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("customers", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("customers", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("customers");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("customers", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("customers", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("customers");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("customers", id, value);
	}
	
	public static List<String> columns() {
		return controller.columns("customers");
	}
	
	public static double totalOwing(String name) {
		double total = 0.0;
		if(exists("full_name", name)) {
			String tot = findBy("full_name", name).get("total_owing");
			if (tot == (null)) {
				total = 0.0;
			}
			else {
				total = Double.parseDouble(tot);
			}
		}		
		return total;
	}
	
	public static String [] customerNames() {
		String [] customers = new String[showAll().size()];
		for (int i = 0; i < showAll().size(); i++) {
			customers[i] = showAll().get(i).get("full_name");
		}
		return customers;
	}	

	public static Map<String, String> findOrCreateBy(String column, Map<String, String> params, boolean update) {
		Map<String, String> record = new TreeMap<String, String>();
		Map<String, String> conditions = new TreeMap<String, String>();
		conditions.put(column, params.get(column));
		if (exists(column, params.get(column))) {
			if (update) {
				update(params, conditions);
			}
			record = findBy(column, params.get(column));
		}
		else {
			create(new TreeMap<String, String>(params));
		}
		return record;
	}
	

	// Associations
	
	public static List<Map<String, String>> transactions(String id) {
		List<Map<String, String>> trx = new ArrayList<Map<String,String>>();
		for(Map<String, String> t : CustomerTransaction.where("customer_id", id)) {
			trx.add(SalesTransaction.find(t.get("sales_transaction_id")));
		}
		return trx;
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}

}
