package helpers;

import java.sql.*;
import java.util.*;

import org.atteo.evo.inflector.English;

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
//		System.out.println(User.showAll());
//		SalesTransaction.migrate();
//		System.out.println(SalesTransaction.last());
//		Sale.delete("sales_transaction_id", "1");
//		System.out.println(Sale.where("sales_transaction_id", "10"));
//		Customer.migrate();
//		CustomerTransaction.migrate();
//		System.out.println(Customer.totalOwing("13252"));
//		Sale.addColumn("approved", "varchar(45)", "updated_at");
//		SalesTransaction.addColumn("approved", "varchar(45)", "updated_at");
//		2, 6, 5, 50, 250, , '2014-07-03 19:03:03', '', id, stock, quant, price
//		params = new HashMap<String, String>();
//		params.put("id", "146");
//		params.put("stock_id", "6");
//		params.put("quantity", "5");
//		params.put("price", "250");
//		System.out.println(Sale.findOrCreateBy("id", params, true));
//		List<String> unapprovedSales = new ArrayList<String>();
//		String sales = "";
//		for(Map<String, String> sale : SalesTransaction.where("approved", "NO")) {
//			for(Map<String, String> ss : SalesTransaction.sales(sale.get("id"))) {
//				System.out.println(ss);
//				sales += Stock.find(ss.get("stock_id")).get("name") + "(" + ss.get("quantity") + ")" + " - ";
//			}
//			unapprovedSales.add("#" + sale.get("id") + " - " + sales);
//		}
//		System.out.println(SalesTransaction.sales("50"));
//		controller.resetDB();
//		System.out.println(h.getClass().getSimpleName().toLowerCase());
//		String regex = "([a-z])([A-Z])";
//        String replacement = "$1_$2";
//        System.out.println("StockReserve".replaceAll(regex, replacement).toLowerCase());
//        System.out.println(English.plural("word", 1)); // == "word"
//        System.out.println(English.plural("Stock", 2)); // == "words"
//        String className = English.plural(h.getClass().getSimpleName().replaceAll(regex, replacement).toLowerCase(), 2);
//        System.out.println(className);
//		UsersController u = new UsersController();
//		System.out.println(u.className);
	}
}
