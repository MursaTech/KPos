package views;

import java.sql.*;
import java.util.*;


public class MursalDB {
	
	private Connection con = null;
	String user = null, pass = null;
	boolean success = false;
	private ResultSet rst = null, rst1 = null;
	private Statement stmt = null, stmt1 = null;
	private PreparedStatement psmt = null;
	Set<String> barcodes = new HashSet<String>();
	List<String> items = new LinkedList<String>();
	Set<String> reasons = new HashSet<String>();
	Set<String> dates = new TreeSet<String>();
	List<String> listDates = new LinkedList<String>();
	Set<String> tables = new HashSet<String>();
	List<String> columns = null;
	Vector<String> data = null;
	List<Integer> morning = new LinkedList<Integer>();
	List<Integer> afternoon = new LinkedList<Integer>();
	List<Integer> evening = new LinkedList<Integer>();
	List<Integer> night = new LinkedList<Integer>();
	List<Integer>lateNight = new LinkedList<Integer>();
	Map<String, Integer> countItems = new HashMap<String, Integer>();
	Map<String, Integer> countDates = new HashMap<String, Integer>();
	String name = null, itemName = null, q = null, reason, newReason, currentDate, top1, top2, top3, bottom1, bottom2, bottom3, mostValuable1, mostValuable2, mostValuable3,
			leastValuable1, leastValuable2, leastValuable3, busiest1, busiest2, busiest3, leastBusy1, leastBusy2, leastBusy3, table, unit, customerName, address, dueDate;
    int quantity = 0, enteredQuantity = 0, total = 0, amount_paid = 0, balance = 0, xpense, xpenseTotal, xpPaid, xpBalance, salesTotal = 0, grandSalesTotal = 0,
    		yesterdaySalesTotal = 0, count = 0, discount = 0, monthSalesTotal = 0, yearSalesTotal = 0, buyPrice, sellPrice, weekSalesTotal = 0, natID;
    double price = 0.0, limit, totalOwing;
	   
	public Connection connect() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos", "muaad", "muaad"); // "muaad", "muaad" user, pass
			success = true;
		} catch (SQLException e) {
			success = false;
			System.err.println(e);
			//JOptionPane.showMessageDialog(null, "Connection failed!!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return c;
	}
	
	boolean connected = false;
	public Connection setUpConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", user, pass); // "muaad", "muaad" user, pass
			connected = true;
		} catch (SQLException e) {
			connected = false;
			//JOptionPane.showMessageDialog(null, "Connection failed!!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return c;
	}
	
	List<String> list;
	void readProducts(String s) {
		con = connect();
		 try {
			stmt = con.createStatement();
			rst = stmt.executeQuery("select product from inventory where product like '"+s+"%' or product like '%"+s+"%'");
			list = new Vector<String>();
			while(rst.next()) {
				list.add(rst.getString(1));
			}
		} catch (SQLException e) {e.printStackTrace();}
	}

	//Statistics.............
	
	public void getDatesCount() throws SQLException {
		getDates();
		listDates.addAll(dates);
		//con = connect();
		for (int i = 0; i < listDates.size(); i++) {
			stmt = con.createStatement();
			rst = stmt.executeQuery("SELECT * FROM sales where time like '"+listDates.get(i)+"%'");
			while (rst.next()) {
				count++;
			}
			countDates.put(listDates.get(i), count);
			count = 0;
		}
		List<Integer> li = new ArrayList<Integer>();
		List<String> keys = new ArrayList<String>();
		keys.addAll(countDates.keySet());
		li.addAll(countDates.values());
		Collections.sort(li);
		for (int i = 0; i < keys.size(); i++) {
			if(countDates.get(keys.get(i)) == li.get(0)) {
				Calendar cal = new GregorianCalendar(Integer.parseInt(keys.get(i).substring(0, 4)),
						Integer.parseInt(keys.get(i).substring(5, 7)), Integer.parseInt(keys.get(i).substring(8, 10)));
				leastBusy1 = cal.getTime().toString();
				//System.out.println(leastBusy1);
			}
			else if(countDates.get(keys.get(i)) == li.get(1)) {
				Calendar cal = new GregorianCalendar(Integer.parseInt(keys.get(i).substring(0, 4)),
						Integer.parseInt(keys.get(i).substring(5, 7)), Integer.parseInt(keys.get(i).substring(8, 10)));
				leastBusy2 = cal.getTime().toString();
				//System.out.println(leastBusy2);
			}
			/*else if(countDates.get(keys.get(i)) == li.get(2)) {
				Calendar cal = new GregorianCalendar(Integer.parseInt(keys.get(i).substring(0, 4)),
						Integer.parseInt(keys.get(i).substring(5, 7)), Integer.parseInt(keys.get(i).substring(8, 10)));
				busiest3 = cal.getTime().toString();
			}
			else if(countDates.get(keys.get(i)) == li.get(li.size() - 1)) {
				Calendar cal = new GregorianCalendar(Integer.parseInt(keys.get(i).substring(0, 4)),
						Integer.parseInt(keys.get(i).substring(5, 7)), Integer.parseInt(keys.get(i).substring(8, 10)));
				leastBusy1 = cal.getTime().toString();
			}*/
			else if(countDates.get(keys.get(i)) == li.get(li.size() - 1)) {
				Calendar cal = new GregorianCalendar(Integer.parseInt(keys.get(i).substring(0, 4)),
						Integer.parseInt(keys.get(i).substring(5, 7)), Integer.parseInt(keys.get(i).substring(8, 10)));
				busiest1 = cal.getTime().toString();
				//System.out.println(busiest1);
			}
			else if(countDates.get(keys.get(i)) == li.get(li.size() - 2)) {
				Calendar cal = new GregorianCalendar(Integer.parseInt(keys.get(i).substring(0, 4)),
						Integer.parseInt(keys.get(i).substring(5, 7)), Integer.parseInt(keys.get(i).substring(8, 10)));
				busiest2 = cal.getTime().toString();
				//System.out.println(busiest2);
			}
		}
		//System.out.println(keys.get(0).substring(0, 4)+"-"+keys.get(0).substring(5, 7)+"-"+keys.get(0).substring(8, 10));
		//System.out.println(countDates);
		//System.out.println(busiest1+", "+busiest2+", "+leastBusy2+", "+leastBusy1);
	}
	
	public void getGrandSalesTotal() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("SELECT * FROM transaction_history");
		while (rst.next()) {
			grandSalesTotal += rst.getInt(2);
		}
	}
	
	public void loadItems() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("select * from inventory");
		while (rst.next()) {
			items.add((String) rst.getObject(1));
		}
	}
	
	public void getCountOfItemsSold() throws SQLException {
		loadItems();
		//con = connect();
		for (int i = 0; i < items.size(); i++) {
			stmt = con.createStatement();
			rst = stmt.executeQuery("SELECT * FROM sales where product = '"+items.get(i)+"'");
			while (rst.next()) {
				//count++;
				quantity += rst.getInt(2);
			}
			//count *= quantity;
			countItems.put(items.get(i), quantity);
			count = 0;
			quantity = 0;
		}
		List<Integer> li = new ArrayList<Integer>();
		List<String> keys = new ArrayList<String>();
		keys.addAll(countItems.keySet());
		li.addAll(countItems.values());
		Collections.sort(li);
		for (int i = 0; i < keys.size(); i++) {
			if(countItems.get(keys.get(i)) == li.get(0)) {
				bottom1 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(1)) {
				bottom2 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(2)) {
				bottom3 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(li.size() - 1)) {
				top1 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(li.size() - 2)) {
				top2 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(li.size() - 3)) {
				top3 = keys.get(i);
			}
		}
		//System.out.println(countItems);
		//System.out.println(li);
		//System.out.println(top1+", "+top2+", "+top3+", "+bottom3+", "+bottom2+", "+bottom1);
	}
	
	public void getValueOfItemsSold() throws SQLException {
		loadItems();
		int p = 0;
		//con = connect();
		for (int i = 0; i < items.size(); i++) {
			stmt = con.createStatement();
			rst = stmt.executeQuery("SELECT * FROM sales where product = '"+items.get(i)+"'");
			while (rst.next()) {
				//count++;
				quantity += rst.getInt(2);
				p = rst.getInt(3);
			}
			//count *= quantity;
			countItems.put(items.get(i), quantity * p);
			count = 0;
			quantity = 0;
			p = 0;
		}
		List<Integer> li = new ArrayList<Integer>();
		List<String> keys = new ArrayList<String>();
		keys.addAll(countItems.keySet());
		li.addAll(countItems.values());
		Collections.sort(li);
		for (int i = 0; i < keys.size(); i++) {
			if(countItems.get(keys.get(i)) == li.get(0)) {
				leastValuable1 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(1)) {
				leastValuable2 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(2)) {
				leastValuable3 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(li.size() - 1)) {
				mostValuable1 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(li.size() - 2)) {
				mostValuable2 = keys.get(i);
			}
			else if(countItems.get(keys.get(i)) == li.get(li.size() - 3)) {
				mostValuable3 = keys.get(i);
			}
		}
		//System.out.println(countItems);
		//System.out.println(mostValuable1+", "+mostValuable2+", "+mostValuable3+", "+leastValuable1+", "+leastValuable2+", "+leastValuable3);
	}
	
	public void getSalesTotalForYesterday() throws SQLException {
		String yesterday = null;
		getCurrentDate();
		if((Integer.parseInt(currentDate.substring(8,10))) < 10) {
			yesterday = currentDate.substring(0,8)+"0"+(Integer.parseInt(currentDate.substring(8,10)) - 1);
		}
		else {
			yesterday = currentDate.substring(0,8)+(Integer.parseInt(currentDate.substring(8,10)) - 1);
		}
		//con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("SELECT * FROM transaction_history where Time like '" +yesterday+ "%'");
		while (rst.next()) {
			yesterdaySalesTotal += rst.getInt(2);
		}
	}
	
	public void getSalesTotalForMonth() throws SQLException {
		String today = null;
		getCurrentDate();
		//con = connect();
		for (int i = 1; i <= Integer.parseInt(currentDate.substring(8,10)); i++) {
			stmt = con.createStatement();
			if(i < 10) {
				today = currentDate.substring(0,8)+"0"+ i;
				//System.out.println(today);
			}
			else {
				today = currentDate.substring(0,8)+ i;
			}
			//System.out.println(today);
			rst = stmt.executeQuery("SELECT * FROM transaction_history where Time like '"+ today + "%'");
			while (rst.next()) {
				monthSalesTotal += rst.getInt(2);
			}
			//System.out.println(today + " "+ monthSalesTotal);
		}

		stmt.close();
		con.close();
	}
	
	public void getSalesTotalForYear() throws SQLException {
		//con = connect();
		getCurrentDate();
		rst = stmt.executeQuery("SELECT * FROM transaction_history where Time like '"+ currentDate.substring(0,4) + "%'");
		while (rst.next()) {
			yearSalesTotal += rst.getInt(2);
		}
		stmt.close();
		con.close();
	}
	
	public void getSalesTotalForWeek() throws SQLException {
		//getCurrentDate();
		//getDatesCount() populates a collection(listDates) with dates in which transactions took place
		getDatesCount();
		//set cal with today's date
		Calendar cal = new GregorianCalendar();
		for (int i = 0; i < listDates.size(); i++) {
			//set c with the date of when the transaction took place
			Calendar c = new GregorianCalendar(Integer.parseInt(listDates.get(i).substring(0, 4)),
					Integer.parseInt(listDates.get(i).substring(5, 7)), Integer.parseInt(listDates.get(i).substring(8, 10)));
			//This is like saying if the day of week in month when the transaction took place is the same as that of today, then the transaction
			//must have taken place in the same week as today
			if(cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == c.get(Calendar.DAY_OF_WEEK_IN_MONTH)) {
				rst = stmt.executeQuery("SELECT * FROM transaction_history where Time like '"+ listDates.get(i) + "%'");
				while (rst.next()) {
					weekSalesTotal += rst.getInt(2);
				}
			}
		}
	}
	
	public void categorizeTimes() {
		for(int i = 6; i < 12; i++) {
			morning.add(i);
		}
		for(int i = 12; i < 16; i++) {
			afternoon.add(i);
		}
		for(int i = 16; i < 19; i++) {
			evening.add(i);
		}
		for(int i = 19; i < 23; i++) {
			night.add(i);
		}
		for(int i = 0; i < 6; i++) {
			lateNight.add(i);
		}
	}
	
	public void getPeakAndDownTimes() throws SQLException {
		/*String today = null;
		getCurrentDate();
		Calendar cal = new GregorianCalendar(Integer.parseInt(currentDate.substring(0, 4)),
				Integer.parseInt(currentDate.substring(5, 7)), Integer.parseInt(currentDate.substring(8, 10)));
		con = connect();
		for (int i = 1; i <= cal.get(Calendar.DAY_OF_WEEK); i++) {
			stmt = con.createStatement();
			if(i < 10) {
				today = currentDate.substring(0,8)+"0"+ i;
				//System.out.println(today);
			}
			else {
				today = currentDate.substring(0,8)+ i;
			}
			//System.out.println(today);
			rst = stmt.executeQuery("SELECT * FROM transaction_history where Time like '"+ today + "%'");
			while (rst.next()) {
				monthSalesTotal += rst.getInt(2);
			}
			//System.out.println(today + " "+ monthSalesTotal);
		}*/
	}
	
	public void getSalesTotalForToday() throws SQLException {
		getCurrentDate();
		//con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("SELECT * FROM transaction_history where Time like '" +currentDate+ "%'");
		while (rst.next()) {
			salesTotal += rst.getInt(2);
		}
	}
	
	public void getCurrentDate() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("SELECT curdate()");
		while (rst.next()) {
			currentDate = rst.getObject(1).toString();
		}
	}
	
	public void getDates() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("select * from sales");
		while (rst.next()) {
			dates.add(rst.getObject(5).toString().substring(0, 10));
		}
	}
	
	List<Vector<String>> listOfVectorsTrans = new LinkedList<Vector<String>>();
	Vector<String> dataTrans;
	
	public void loadTransactionTable() throws SQLException {
		con = connect();
		stmt = con.createStatement();
		rst = stmt.executeQuery("select * from transaction_history");
		ResultSetMetaData rmd = rst.getMetaData();
		while (rst.next()) {
			dataTrans = new Vector<String>();
			for (int j = 1; j <= rmd.getColumnCount(); j++) {
				dataTrans.add(rst.getObject(j).toString());
			}
			totalForPeriod = totalForPeriod + rst.getInt(2);
			listOfVectorsTrans.add(dataTrans);
		}
	}
	
	List<Vector<String>> listOfVectorsVarTrans = new LinkedList<Vector<String>>();
	Vector<String> dataVarTrans;
	List<String> fromTo = new LinkedList<String>();
	int fromIndex, toIndex, totalForPeriod = 0;
	String fromDate, toDate;
	
	public void loadVariableTransactionTable() throws SQLException {
		String date = "";
		//getDatesCount() populates a collection(listDates) with dates in which transactions took place
		getDatesCount();
		stmt = con.createStatement();
		for (int i = 0; i < listDates.size(); i++) {
			if(listDates.get(i).equals(fromDate)) {
				fromIndex = i;
			}
			else if(listDates.get(i).equals(toDate)) {
				toIndex = i;
			}
		}
		//fromTo is a collection containing all the selected dates i.e. between the FROM date to the TO date
		fromTo = listDates.subList(fromIndex, toIndex + 1);
		for (int i = 0; i < fromTo.size(); i++) {
			if(fromDate.equals(toDate)) {
				date = toDate;
			}
			else {
				date = fromTo.get(i);
			}
			rst = stmt.executeQuery("select * from transaction_history where time like '"+ date +"%'");
			ResultSetMetaData rmd = rst.getMetaData();
			while (rst.next()) {
				dataVarTrans = new Vector<String>();
				for (int j = 1; j <= rmd.getColumnCount(); j++) {
					dataVarTrans.add(rst.getObject(j).toString());
				}
				totalForPeriod = totalForPeriod + rst.getInt(2);
				listOfVectorsVarTrans.add(dataVarTrans);
			}
			date = "";
		}
	}

	Map<String, Integer> profitMap = new HashMap<String, Integer>();
	
	public void calculateProfitForEachItem() throws SQLException {
		//con = connect();
		int bp = 0;
		int salesTotal = 0;
		loadItems();
		stmt = con.createStatement();
		for (int i = 0; i < items.size(); i++) {
			rst = stmt.executeQuery("select quantity, buying_price from items where product = '"+ items.get(i) +"'");
			while(rst.next()) {
				bp = rst.getInt(1) * rst.getInt(2);
			}
			rst = con.createStatement().executeQuery("select total from sales where product = '"+ items.get(i) +"'");
			while(rst.next()) {
				salesTotal = salesTotal + rst.getInt(1);
			}
			profitMap.put(items.get(i), salesTotal - bp);
		}
		salesTotal = 0;
		bp = 0;
		//System.out.println(profitMap);
	}
	
	List<Vector<String>> listOfVectorsItems = new LinkedList<Vector<String>>();
	Vector<String> dataItems;
	
	public void loadItemsTable() throws SQLException {
		int quantity = 0, total = 0, profit = 0;
		//con = connect();
		calculateProfitForEachItem();
		stmt = con.createStatement();
		for (int i = 0; i < items.size(); i++) {
			dataItems = new Vector<String>();
			rst = stmt.executeQuery("select product, quantity, total from sales where product = '"+ items.get(i)+"'");
			while (rst.next()) {
				quantity = quantity + rst.getInt(2);
				total = total + rst.getInt(3);
			}
			profit = profitMap.get(items.get(i));
			dataItems.add(items.get(i));
			dataItems.add(String.valueOf(quantity));
			dataItems.add(String.valueOf(total));
			dataItems.add(String.valueOf(profit));
			quantity = 0;
			total = 0;
			profit = 0;
			listOfVectorsItems.add(dataItems);
		}
		items.clear();
	}

	List<Vector<String>> listOfVectorsItemsVar = new LinkedList<Vector<String>>();
	Vector<String> dataItemsVar;
	
	public void loadVariableItemsTable() throws SQLException {
		int quantity = 0, total = 0, profit = 0;
		calculateProfitForEachItem();
		getDatesCount();
		for (int i = 0; i < listDates.size(); i++) {
			if(listDates.get(i).equals(fromDate)) {
				fromIndex = i;
			}
			else if(listDates.get(i).equals(toDate)) {
				toIndex = i;
			}
		}
		fromTo = listDates.subList(fromIndex, toIndex + 1);
		stmt = con.createStatement();
		for (int i = 0; i < fromTo.size(); i++) {
			rst = stmt.executeQuery("select product, quantity, total from sales where time like '"+ fromTo.get(i) +"%'");
			for (int j = 0; j < items.size(); j++) {
				dataItemsVar = new Vector<String>();
				while (rst.next()) {
					if (rst.getString(1).equals(items.get(j))) {
						quantity = quantity + rst.getInt(2);
						total = total + rst.getInt(3);
						count++;
					}
				}
				System.out.println(items.get(j)+" "+count);
			}
			profit = profitMap.get(items.get(i)) * quantity;
			dataItemsVar.add(items.get(i));
			dataItemsVar.add(String.valueOf(quantity));
			dataItemsVar.add(String.valueOf(total));
			dataItemsVar.add(String.valueOf(profit));
			quantity = 0;
			total = 0;
			profit = 0;
			listOfVectorsItemsVar.add(dataItemsVar);
		}
		items.clear();
	}
	
	//View project tables..............

	List<Vector<String>> listOfVectorsProjTbls = new LinkedList<Vector<String>>();
	Vector<String> dataProjTbls;
	
	public void getTables() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("show tables");
		while (rst.next()) {
			tables.add(rst.getString(1));
		}
	}
	
	int cols = 0;
	
	public void loadTable() throws SQLException {
		con = connect();
		stmt = con.createStatement();
		rst = stmt.executeQuery("select * from " + table);
		ResultSetMetaData rmd = rst.getMetaData();
		cols =rmd.getColumnCount();
		columns = new LinkedList<String>();
		for (int j = 1; j <= cols; j++) {
			columns.add(rmd.getColumnName(j));
		}
		while (rst.next()) {
			dataProjTbls = new Vector<String>();
			for (int j = 1; j <= rmd.getColumnCount(); j++) {
				dataProjTbls.add(rst.getObject(j).toString());
			}
			listOfVectorsProjTbls.add(dataProjTbls);
		}
	}
	
	//Set up project tables........
	
	public void setUpProject() throws SQLException {
		createDatabase();
		createSalesTable();
		createExpenseHistoryTable();
		createExpenseReasonsTable();
		createExpensesTable();
		createInventoryTable();
		createNewerInventoryTable();
		createItemsTable();
		createTransactionHistoryTable();
	}
	
	public void createDatabase() throws SQLException {
		con = setUpConnection();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS pos");
		stmt.executeQuery("use pos");
		//stmt.close();
		//con.close();
	}
	
	public void createSalesTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sales (product varchar(30),quantity varchar(45)," +
				"price double,total double,time datetime) ENGINE = InnoDB");
		//stmt.close();
		//con.close();
	}
	
	public void createInventoryTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS inventory (product varchar(40) primary key, quantity integer(10), units varchar(20),"+
				"buying_price integer(10), selling_price integer(10), date datetime) ENGINE = InnoDB");
		//stmt.close();
		//con.close();
	}
	

	public void createNewerInventoryTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS newer_inventory (product varchar(40) primary key, quantity integer(10), units varchar(20),"+
				"buying_price integer(10), selling_price integer(10), date datetime) ENGINE = InnoDB");
	}
	
	public void createItemsTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS items (product varchar(40) primary key, quantity integer(10)," +
				"buying_price integer(10), date datetime)ENGINE = InnoDB");
		//stmt.close();
		//con.close();
	}
	
	public void createExpensesTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS expenses (expense int(10)  NOT NULL,reason varchar(45) NOT NULL," +
				"time varchar(45) NOT NULL) ENGINE = InnoDB");
		//stmt.close();
		//con.close();
	}
	
	public void createExpenseReasonsTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS expense_reasons (no int(10)  NOT NULL,reasons varchar(45) NOT NULL PRIMARY KEY) ENGINE = InnoDB");
		//stmt.close();
		//con.close();
	}
	
	public void createExpenseHistoryTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS expenses_history (transaction_no int(10)  NOT NULL AUTO_INCREMENT PRIMARY KEY," +
				"total_amount int(10)  NOT NULL,amount_paid int(10)  NOT NULL," +
				"balance int(10)  NOT NULL,time datetime NOT NULL) ENGINE = InnoDB");
		//stmt.close();
		//con.close();
	}
	
	public void createTransactionHistoryTable() throws SQLException {
		//con = connect();
		stmt = con.createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS transaction_history (transaction_id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY," +
				"total_amount int(10),amount_paid int(10),balance int(10)," +
				"time datetime,discount int(10)) ENGINE = InnoDB");
		//stmt.close();
		//con.close();
	}
	
	//inventory..........
	
	public void addInventory() throws SQLException {
		con = connect();
		String query = "insert into inventory values (?, ?, ?, ?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setString(1, name);
		psmt.setInt(2, quantity);
		psmt.setString(3, unit);
		psmt.setInt(4, buyPrice);
		psmt.setInt(5, sellPrice);
		psmt.executeUpdate();
		
		//add inventory purchase as an expense
		
		addInventoryAsExpense(quantity * buyPrice);
		
		//add into the expenses_history table
		
		query = "insert into expenses_history (total_amount, amount_paid, balance, time) values (?, ?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, quantity * buyPrice);
		psmt.setInt(2, quantity * buyPrice);
		psmt.setInt(3, 0);
		psmt.executeUpdate();
		
		//add reason for expense as "inventory"
		
		query = "insert into expense_reasons (reasons) values (?)";
		psmt = con.prepareStatement(query);
		psmt.setString(1, "Inventory");
		psmt.executeUpdate();
		
		//psmt.close();
		//con.close();
	}
	public void addInventoryAsExpense(int bp) throws SQLException {
		//con = connect();
		String query = "insert into expenses (expense, reason, time) values (?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, bp);
		psmt.setString(2, "Inventory");
		psmt.executeUpdate();
	}

	public void addNewerInventory() throws SQLException {
		con = connect();
		String query = "insert into newer_inventory values (?, ?, ?, ?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setString(1, name);
		psmt.setInt(2, quantity);
		psmt.setString(3, unit);
		psmt.setInt(4, buyPrice);
		psmt.setInt(5, sellPrice);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	

	public void addToItemsTable() throws SQLException {
		con = connect();
		String query = "insert into items values (?, ?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setString(1, name);
		psmt.setInt(2, quantity);
		psmt.setInt(3, buyPrice);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	List<Vector<String>> listOfVectors = new LinkedList<Vector<String>>();

	public void loadInventoryTable() throws SQLException {
		con = connect();
		stmt = con.createStatement();
		rst = stmt.executeQuery("select * from inventory");
		ResultSetMetaData rmd = rst.getMetaData();
		cols =rmd.getColumnCount();
		columns = new LinkedList<String>();
		for (int j = 1; j <= cols; j++) {
			columns.add(rmd.getColumnName(j));
		}
		while (rst.next()) {
			data = new Vector<String>();
			for (int j = 1; j <= rmd.getColumnCount(); j++) {
				data.add(rst.getObject(j).toString());
			}
			listOfVectors.add(data);
		}
	}
	
	public void deleteInventoryRecord() throws SQLException {
		String date = "";
		rst = con.createStatement().executeQuery("select date from inventory where product = '" +name+ "'");
		while(rst.next()) {
			date = rst.getString(1);
		}
		
		con = connect();
		psmt = con.prepareStatement("delete from inventory where product = ?");
		psmt.setString(1, name);
		psmt.executeUpdate();
		
		//delete from expenses
		
		psmt = con.prepareStatement("delete from expenses where time = ?");
		psmt.setString(1, date);
		psmt.executeUpdate();

		//delete from expenses history
		
		psmt = con.prepareStatement("delete from expenses_history where time = ?");
		psmt.setString(1, date);
		psmt.executeUpdate();
		
	}
	
	String col, newValue;
	
	public void editInventoryRecord() throws SQLException {
		con = connect();
		psmt = con.prepareStatement("update inventory set "+ col + " = ? where product = ?");
		psmt.setString(1, newValue);
		psmt.setString(2, name);
		psmt.executeUpdate();

		psmt.close();
		con.close();
	}
	
	List<String> inventory = new LinkedList<String>();

	public void getNewerInventory() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("select * from newer_inventory where product = '"+ name +"'");
		ResultSetMetaData rmd = rst.getMetaData();
		while (rst.next()) {
			for (int i = 1; i <= rmd.getColumnCount(); i++) {
				if (rmd.getColumnTypeName(i) == "VARCHAR" || rmd.getColumnTypeName(i) == "DATETIME") {
					inventory.add(rst.getString(i));
				}
				else {
					inventory.add(String.valueOf(rst.getInt(i)));
				}
			}
		}
	}

	public void replenishInventory() throws SQLException {
		//con = connect();
		getNewerInventory();
		deleteInventoryRecord();
		String query = "insert into inventory values(?,?,?,?,?,?)";
		psmt = con.prepareStatement(query);
		psmt.setString(1, inventory.get(0));
		psmt.setInt(2, Integer.parseInt(inventory.get(1)));
		psmt.setString(3, inventory.get(2));
		psmt.setInt(4, Integer.parseInt(inventory.get(3)));
		psmt.setInt(5, Integer.parseInt(inventory.get(4)));
		psmt.setString(6, inventory.get(5));
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	//Sales............

	public void readRecords() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("select * from inventory");
		while (rst.next()) {
			barcodes.add((String) rst.getObject(1));
		}
	}
	
	public void updateRecords() throws SQLException {
		con = connect();
		stmt1 = con.createStatement();
		rst1 = stmt1.executeQuery("select quantity, selling_price from inventory where product = '"+name+"'");
		while(rst1.next()) {
			quantity = rst1.getInt(1);
			price = rst1.getInt(2);
		}
		
		stmt1.close();
		
		String query = "update inventory set quantity = quantity - ? where product = '"+name+"'";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, enteredQuantity);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	List<String> time = new LinkedList<String>();
	Map<String, String> productMap = new HashMap<String, String>();
	
	public void recordSales() throws SQLException {
		con = connect();
		String query = "insert into sales values (?, ?, ?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setString(1, name);
		psmt.setInt(2, enteredQuantity);
		psmt.setDouble(3, price);
		psmt.setDouble(4, (enteredQuantity * price));
		psmt.executeUpdate();
		
		stmt = con.createStatement();
		rst = stmt.executeQuery("select now()");
		while(rst.next()) {
			time.add(rst.getString(1));
			productMap.put(rst.getString(1), name);
		}
		psmt.close();
		con.close();
	}
	
	String dateToDelete;
	
	public void deleteSalesRecord() throws SQLException {
		con = connect();
		psmt = con.prepareStatement("delete from sales where time = ?");
		psmt.setString(1, dateToDelete);
		psmt.executeUpdate();
	}
	
	public void updateInventoryAfterEdit() throws SQLException {
		con = connect();
		rst = con.createStatement().executeQuery("select quantity from sales where time = '"+ dateToEdit+"'");
		while(rst.next()) {
			quantity = rst.getInt(1);
		}
		
		String query = "update inventory set quantity = quantity + ? where product = '"+name+"'";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, quantity);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	public void updateInventoryAfterDelete() throws SQLException {
		con = connect();
		String query = "update inventory set quantity = quantity + ? where product = '"+name+"'";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, enteredQuantity);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	String dateToEdit;
	int newTotal;
	public void editSalesRecord(int price) throws SQLException {
		con = connect();
		String query = "update sales set quantity = ?, price = ?, total = ? where time = '"+dateToEdit+"'";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, enteredQuantity);
		psmt.setInt(2, price);
		psmt.setInt(3, newTotal);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	Map<String, Integer> priceMap = new HashMap<String, Integer>();
	
	public void mapProductToPrice() throws SQLException {
		con = connect();
		rst = con.createStatement().executeQuery("select product, selling_price from inventory");
		while(rst.next()) {
			priceMap.put(rst.getString(1), rst.getInt(2));
		}

	}
	
	public void recordTransaction() throws SQLException {
		con = connect();
		String query = "insert into transaction_history (total_amount, amount_paid, balance, time, discount) values (?, ?, ?, now(),?)";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, total - discount);
		psmt.setInt(2, amount_paid);
		psmt.setInt(3, balance);
		psmt.setInt(4, discount);
		psmt.executeUpdate();
		
		
		psmt.close();
		con.close();
	}
	
	//Postpaid sales ....
	
	public void registerPostpaidCustomer() {
		con = connect();
		try {
			String query = "insert into postpaid_customers (national_id, name, address, limit, due_date) values (?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, natID);
			psmt.setString(2, customerName);
			psmt.setString(3, address);
			psmt.setDouble(4, limit);
			psmt.setString(5, dueDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Expenses .....
	
	public void addReasons() throws SQLException {
		con = connect();
		String query = "insert into expense_reasons (reasons) values (?)";
		psmt = con.prepareStatement(query);
		psmt.setString(1, newReason);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	public void loadReasons() throws SQLException {
		con = connect();
		stmt =con.createStatement();
		rst = stmt.executeQuery("select * from expense_reasons");
		while (rst.next()) {
			reasons.add(rst.getString(2));
		}
	}
	List<String> xpDates = new LinkedList<String>();
	public void recordExpense() throws SQLException {
		con = connect();
		String query = "insert into expenses (expense, reason, time) values (?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, xpense);
		psmt.setString(2, reason);
		psmt.executeUpdate();
		
		rst = con.createStatement().executeQuery("select now()");
		while(rst.next()) {
			xpDates.add(rst.getString(1));
		}
		
		psmt.close();
		con.close();
	}

	String xpDate;
	public void deleteExpenseRecord() throws SQLException {
		con = connect();
		psmt = con.prepareStatement("delete from expenses where time = ?");
		psmt.setString(1, xpDate);
		psmt.executeUpdate();
	}
	
	public void editExpenseRecord(int xp, String reason, String time) throws SQLException {
		con = connect();
		psmt = con.prepareStatement("update expenses set expense = ?, reason = ? where time = '"+time+"'");
		psmt.setInt(1, xp);
		psmt.setString(2, reason);
		psmt.executeUpdate();
	}
	
	public void recordTransactionXp() throws SQLException {
		con = connect();
		String query = "insert into expenses_history (total_amount, amount_paid, balance, time) values (?, ?, ?, now())";
		psmt = con.prepareStatement(query);
		psmt.setInt(1, xpenseTotal);
		psmt.setInt(2, xpPaid);
		psmt.setInt(3, xpBalance);
		psmt.executeUpdate();
		
		psmt.close();
		con.close();
	}
	
	/*public static void main(String [] args) throws SQLException {
		MursalDB db = new MursalDB();
		db.calculateProfitForEachItem();
	}*/
	
}
