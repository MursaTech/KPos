package controllers;

import helpers.DBHelpers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.atteo.evo.inflector.English;

import models.*;

public class DBController {
	PreparedStatement psmt;
	static ResultSet rst = null;
	ResultSetMetaData rmd = null;
	private DBHelpers helper = new DBHelpers();
	boolean connected = false;
	
//	public String regex = "([a-z])([A-Z])";
//	public String replacement = "$1_$2";
//	public String className = English.plural(this.getClass().getSimpleName().replaceAll(regex, replacement).toLowerCase(), 2);
	
	public Connection connect() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/KPos", "muaad", "muaad");
			connected = true;
		} catch (SQLException e) {
//			e.printStackTrace();
			connected = false;
		}
		return c;
	}
	
	public void closeConnection() {
		try {
			connect().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Connection connectInitial() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "muaad", "muaad");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public List<String> columns(String table) {
		List<String> cols = new ArrayList<String>();
		try {
			rst = connect().createStatement().executeQuery("select * from " + table + " limit 1");
			rmd = rst.getMetaData();
			
			for (int j = 1; j <= rmd.getColumnCount(); j++) {
				cols.add(rmd.getColumnName(j));
			}
		} catch (SQLException e) {}
		return cols;
	}
	
	public static void runMigrations() {
		try {
			connectInitial().createStatement().executeUpdate("create database if not exists KPos");
		} catch (SQLException e) {}
		Category.migrate();
		Expense.migrate();
		ExpenseTransaction.migrate();
		Sale.migrate();
		SalesTransaction.migrate();
		SubCategory.migrate();
		Stock.migrate();
		StockReserve.migrate();
		User.migrate();
		Payroll.migrate();
		Session.migrate();
		Business.migrate();
		Customer.migrate();
		CustomerTransaction.migrate();
	}

	public static void resetDB() {
		Category.deleteAll();
		Expense.deleteAll();
		ExpenseTransaction.deleteAll();
		Sale.deleteAll();
		SalesTransaction.deleteAll();
		SubCategory.deleteAll();
		Stock.deleteAll();
		StockReserve.deleteAll();
		User.deleteAll();
		Payroll.deleteAll();
		Session.deleteAll();
		Business.deleteAll();
		Customer.deleteAll();
		CustomerTransaction.migrate();
	}
	
	public boolean setUp() {
		boolean setUp = false;
		String [] tables = {"businesses", "categories", "customer_transactions", "customers",
				"expense_transactions", "expenses", "payrolls", "sales", "sales_transactions", "sessions",
				"stock_reserves", "stocks", "sub_categories", "users" };
		List<String> tbls = Arrays.asList(tables);
		Collections.sort(tbls);
		connect();
		if (connected) {
			try {
				rst = connect().createStatement().executeQuery("show tables");
				int i = 0;
				while (rst.next()) {
					setUp = tbls.get(i).trim().equalsIgnoreCase(rst.getString(1).trim());
					i++;
				}
			} catch (SQLException e) {
				setUp = false;
			}
		}
		return setUp && connected;		
	}

	public boolean recordExists(String table, String field, String value) {
		boolean exists = false;
		try {
			rst = connect().createStatement().executeQuery("select "+ field +" from " + table + " where " + field + " = '" + value + "'");
			exists = rst.first();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	public List<TreeMap<String, String>> index(String table) {
		List<TreeMap<String, String>> all = new ArrayList<TreeMap<String, String>>();
		TreeMap<String, String> record;
		try {
			rst = connect().createStatement().executeQuery("select * from " + table );
			rmd = rst.getMetaData();
			int n = 1;
			while (rst.next()) {
				record = new TreeMap<String, String>();
				for (int j = 2; j <= rmd.getColumnCount(); j++) {
					record.put(rmd.getColumnName(j), rst.getString(j));
					n++;
				}
				all.add(record);
			}
		} catch (SQLException e) {}
		return all;
	}
	
	public List<Map<String, String>> conditionalSelect(String table, String field, String value) {
		List<Map<String, String>> all = new ArrayList<Map<String, String>>();
		Map<String, String> record;
		try {
			rst = connect().createStatement().executeQuery("select * from " +table +" where " + field + " = '" + value + "'");
			rmd = rst.getMetaData();
			
			while (rst.next()) {
				record = new HashMap<String, String>();
				for (int j = 1; j <= rmd.getColumnCount(); j++) {
					record.put(rmd.getColumnName(j), rst.getString(j));
				}
				all.add(record);
			}
		} catch (SQLException e) {}
		return all;
	}
	
	public List<Map<String, String>> conditionalSelect(String table, String conditions) {
		List<Map<String, String>> all = new ArrayList<Map<String, String>>();
		Map<String, String> record;
		try {
			rst = connect().createStatement().executeQuery("select * from " + table + " where " + conditions);
			rmd = rst.getMetaData();
			
			while (rst.next()) {
				record = new HashMap<String, String>();
				for (int j = 1; j <= rmd.getColumnCount(); j++) {
					record.put(rmd.getColumnName(j), rst.getString(j));
				}
				all.add(record);
			}
		} catch (SQLException e) {}
		return all;
	}

	public Map<String, String> show(String table, String id) {
		Map<String, String> record = new HashMap<String, String>();
		try {
			rst = connect().createStatement().executeQuery("select * from " + table + " where id = '"+ id + "'");
			rmd = rst.getMetaData();
			
			while (rst.next()) {
				for (int j = 1; j <= rmd.getColumnCount(); j++) {
					record.put(rmd.getColumnName(j), rst.getString(j));
				}
			}
		} catch (SQLException e) {}
		return record;
	}

	public boolean update(String table, Map<String, String> params, String id, String value) {
		List<String> keys = new ArrayList<String>();
		for(String key : params.keySet()) {
			keys.add(key);
		}
		String query;
		query = helper.generateUpdateQuery(table , params, id, value);
		int n = 1;
		try {
			psmt = connect().prepareStatement(query);
		
			for (String l : params.keySet()) {
				psmt.setString(n, params.get(l));
				n++;
			}
			psmt.executeUpdate();
		} catch (SQLException e1) {e1.printStackTrace();}
		return true;
	}

	public boolean delete(String table, String id) {
		try {
			psmt = connect().prepareStatement("delete from " + table + " where id = ?");
			psmt.setString(1, id);
			psmt.executeUpdate();
		} catch (SQLException e) {}
		return false;
	}

	public boolean deleteWhere(String table, String field, String value) {
		try {
			psmt = connect().prepareStatement("delete from " + table + " where " + field + "= ?");
			psmt.setString(1, value);
			psmt.executeUpdate();
		} catch (SQLException e) {}
		return false;
	}

	public boolean truncate(String table) {
		try {
			connect().createStatement().executeQuery("truncate " + table );
		} catch (SQLException e) {}
		return false;
	}

	public Map<String, String> create(String table, TreeMap<String, String> params) {
		List<String> keys = new ArrayList<String>();
		for(String key : params.keySet()) {
			keys.add(key);
		}
		String query;
		query = helper.generateCreateQuery(table , params);
		int n = 1;
		try {
			psmt = connect().prepareStatement(query);
		
			for (String l : params.keySet()) {
				psmt.setString(n, params.get(l));
				n++;
			}
			psmt.executeUpdate();
		} catch (SQLException e1) {e1.printStackTrace(); }
		return show(table, params.get(keys.get(0)));
	}
	
	public void createTable() {
	}
	
	public void addColumn(String table, String column, String type, String after) {
		String query = "ALTER TABLE " + table + " ADD COLUMN " + column + " " + type + " AFTER " + after;
		try {
			connect().createStatement().executeUpdate(query);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
//		System.out.println(query);
	}
	
}
