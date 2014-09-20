package models;

import java.util.*;

import controllers.SessionsController;

public class Session {
	private static SessionsController controller = new SessionsController();
	// CRUD operations
	
	public static Map<String, String> find(String id) {
		return controller.show("sessions", id);		
	}
	
	public static List<Map<String, String>> where(String field, String value) {
		return controller.conditionalSelect("sessions", field, value);		
	}
	
	public static List<Map<String, String>> where(String conditions) {
		return controller.conditionalSelect("sessions", conditions);		
	}

	public static Map<String, String> create(TreeMap<String, String> params) {
		return controller.create("sessions", params);		
	}


	public static List<TreeMap<String, String>> showAll() {
		return controller.index("sessions");		
	}

	public static boolean update(Map<String, String> params, Map<String, String> conditions) {
		for(String field : conditions.keySet()) {
			controller.update("sessions", params, field, conditions.get(field));
		}
		return false;		
	}

	public static boolean delete(String id) {
		controller.delete("sessions", id);
		return false;		
	}

	public static boolean deleteAll() {
		controller.truncate("sessions");
		return false;		
	}
	
	public static boolean exists(String id, String value) {
		return controller.recordExists("sessions", id, value);
	}

	public static void login(TreeMap<String, String> params) {
		create(params);
	}

	public static void logout(String id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("logged_in", "false");
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("id", id);
		update(params, conditions);
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
