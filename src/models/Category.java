package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.CategoriesController;

public class Category {
	private static CategoriesController controller = new CategoriesController(); 
	private String name;
	
	// Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// CRUD Operations
	
	public static Map<String, String> find(String id) {
		return controller.show("categories", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("categories", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("categories", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("categories", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("categories");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("categories", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("categories", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("categories");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("categories", id, value);
	}
	
	// Associations
	
	public static List<Map<String, String>> expenses(String categoryId) {
		return Expense.where("category_id", categoryId);		
	}
	
	public static List<Map<String, String>> subCategories(String categoryId) {
		return SubCategory.where("category_id", categoryId);		
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
