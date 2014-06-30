package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.SubCategoriesController;

public class SubCategory {
	private int categoryId;
	private String name;
	private static SubCategoriesController controller = new SubCategoriesController();
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	// CRUD Operations
	
	public static Map<String, String> find(String id) {
		return controller.show("sub_categories", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("sub_categories", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("sub_categories", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("sub_categories", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("sub_categories");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("sub_categories", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("sub_categories", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("sub_categories");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("sub_categories", id, value);
	}
	
	// Associations
	
	public static Map<String, String> category(String id) {
		String categoryId = find(id).get("category_id");
		return Category.find(categoryId);
		
	}

	public static List<Map<String, String>> expenses(String subCategoryId) {
		return Expense.where("sub_category_id", subCategoryId);		
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
