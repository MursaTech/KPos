package models;

import java.util.*;

import controllers.*;

public class Business {
	private static BusinessesController controller = new BusinessesController();
	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("businesses", id);		
	}

	public static List<Map<String, String>> findBy(String field, String value) {
		return where(field + " = '" + value + "'");		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("businesses", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("businesses", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("businesses", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("businesses");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("businesses", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("businesses", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("businesses");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("businesses", id, value);
	}
	
	// Associations
	
//	public static Map<String, String> expenseTransaction(String id) {
//		String expenseTransactionId = find(id).get("expense_trasaction_id");
//		return ExpenseTransaction.find(expenseTransactionId);
//		
//	}
//
//	public static Map<String, String> category(String id) {
//		String categoryId = find(id).get("category_id");
//		return Category.find(categoryId);
//		
//	}
//
//	public static Map<String, String> subCategory(String id) {
//		String subCategoryId = find(id).get("sub_category_id");
//		return SubCategory.find(subCategoryId);
//		
//	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
