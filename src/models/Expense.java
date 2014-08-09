package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.ExpensesController;

public class Expense {
	private double amount;
	private int categoryId;
	private int subCategoryId;
	private String time;
	private int expenseTransactionId;
	private static ExpensesController controller = new ExpensesController();
	
	// Getters and Setters
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getExpenseTransactionId() {
		return expenseTransactionId;
	}
	public void setExpenseTransactionId(int expenseTransactionId) {
		this.expenseTransactionId = expenseTransactionId;
	}
	
	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("expenses", id);		
	}

	public static List<Map<String, String>> findBy(String field, String value) {
		return where(field + " = '" + value + "'");		
	}
	
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("expenses", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("expenses", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("expenses", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("expenses");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("expenses", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("expenses", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("expenses");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("expenses", id, value);
	}
	
	// Associations
	
	public static Map<String, String> expenseTransaction(String id) {
		String expenseTransactionId = find(id).get("expense_trasaction_id");
		return ExpenseTransaction.find(expenseTransactionId);
		
	}

	public static Map<String, String> category(String id) {
		String categoryId = find(id).get("category_id");
		return Category.find(categoryId);
		
	}

	public static Map<String, String> subCategory(String id) {
		String subCategoryId = find(id).get("sub_category_id");
		return SubCategory.find(subCategoryId);
		
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
	
}
