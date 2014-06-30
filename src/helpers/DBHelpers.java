package helpers;

import java.sql.*;
import java.util.*;

import models.*;

import controllers.*;

public class DBHelpers {
	PreparedStatement psmt;
	ResultSet rst = null;
	ResultSetMetaData rmd = null;
	
	public String generateCreateQuery(String table, TreeMap<String, String> params) {
		String query = "insert into " + table + " (";
		for(String l : params.keySet()) {
			query += l + ", ";
		}
		query = query.substring(0, query.length() - 2);
		query += ", created_at) values (";
		for(String l : params.keySet()) {
			query += "?, ";
		}
		query = query.substring(0, query.length() - 2);
		query += ", now())";
		System.out.println(query);
		return query;
	}

	public String generateUpdateQuery(String table, Map<String, String> params, String id, String value) {
		String query = "update " + table + " set ";
		for(String l : params.keySet()) {
			query += l + " = ?, ";
		}
		query += "updated_at = now()";
		query += " where " + id + " = '" + value + "'";
		return query;
	}
	
	public Set<String> columnDataTypes(String table, Connection con) {
		Set<String> types = new HashSet<String>();
		try {
			rst = con.createStatement().executeQuery("select * from " + table + " limit 1");
			rmd = rst.getMetaData();
			
			for (int j = 1; j <= rmd.getColumnCount(); j++) {
				types.add(rmd.getColumnTypeName(j));
			}
		} catch (SQLException e) {}
		return types;
	}
	
//	public boolean recordExists(String table, String field, String value, Connection con) {
//		boolean exists = false;
//		try {
//			rst = con.createStatement().executeQuery("select "+ field +" from " + table + " where " + field + " = '" + value + "'");
//			exists = rst.first();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return exists;
//	}
//	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> pros = new HashMap<String, String>();
		Map<String, String> conditions = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		map.put("name", "Muaad");
		map.put("height", "180");
		map.put("weight", "65");
		map.put("Age", "No");
		
		pros.put("product", "Personal Computers");
		pros.put("quantity", "50");
		pros.put("units", "laptops");
		pros.put("buying_price", "35000");
		pros.put("selling_price", "50000");
		pros.put("date", "2014-06-22 22:11:09.0");
		
		conditions.put("first_name", "Muaad");
		params.put("last_name", "Abdirahman");
		params.put("user_name", "muaad");
		
		DBHelpers h = new DBHelpers();
//		System.out.println(h.generateCreateQuery("users", map));
		
//		DBController controller = new DBController();
//		System.out.println(h.columnDataTypes("sales", controller.connect()));
//		System.out.println(controller.setUp());
		
//		System.out.println(Expense.find("Phone"));
		
//		System.out.println(Expense.create(pros));
//		System.out.println(Expense.all());

//		"update expenses set expense = ?, reason = ? where time = '"+time+"'"
//		System.out.println(h.generateUpdateQuery("users", map, "name", "Muaad"));
//		
//		User.update(params, conditions);
		
//		Expense.update(pros, conditions);
//		Expense.delete("phone");
//		Expense.deleteAll();
//		System.out.println(Expense.where("product", "Personal Computers"));
//		DBController.runMigrations();
//		System.out.println(h.recordExists("users", "id", "1", DBController.connect()));
//		System.out.println(User.exists("first_name", "muaad"));;
		
//		System.out.println(User.findBy("user_name", "muaad").get(0).get("password"));
		
//		User.deleteAll();
	}
}