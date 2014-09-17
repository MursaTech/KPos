package models;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controllers.UsersController;

public class User {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String passowrd;
	private String type;
	private static UsersController controller = new UsersController();
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassowrd() {
		return passowrd;
	}
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("users", id);		
	}

	public static List<Map<String, String>> findBy(String field, String value) {
		return where(field + " = '" + value + "'");		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("users", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("users", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("users", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("users");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("users", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("users", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("users");
		return false;		
	}

	public static boolean exists(String id, String value) {
		return controller.recordExists("users", id, value);
	}
	
	public static int count() {
		return showAll().size();
	}
	
	public static String fullName(String id) {
		return find(id).get("first_name") + " " + find(id).get("last_name");
	}
	
	// Associations
	
	public static List<Map<String, String>> expenseTransactions(String id) {
		return ExpenseTransaction.where("user_id", id);
		
	}

	public static List<Map<String, String>> salesTransactions(String id) {
		return ExpenseTransaction.where("user_id", id);
		
	}

	public static List<Map<String, String>> sessions(String id) {
		return Session.where("user_id", id);
		
	}

	// One to one association 
	
	public static Map<String, String> payroll(String id) {
		return Payroll.where("user_id", id).get(0);		
	}
	
	// Migrations
	
	public static void migrate() {
		controller.createTable();
	}
}
