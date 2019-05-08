package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.OrderPriceEntry;
import model.Stock;

//import javax.persistence.criteria.CriteriaBuilder;

public class StockDao {
//    public static void main(String[] args) {
//        List<Stock> stocks = new ArrayList<Stock>();
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
//            String query = "SELECT S.*, count(O. StockSymbol) AS times  FROM Stock S, Orders O, Trade Tr, Account A, Client C" +
//                    "                    WHERE S.StockSymbol = O.StockSymbol AND O. OrderId = Tr. OrderId AND OrderType = 'Buy' AND Tr.AccountId = A.AccountId AND A.ClientId = C.ClientId AND C.ClientId = ?" +
//                    "                    Group By O. StockSymbol Order By count(O. StockSymbol)";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setInt(1, Integer.parseInt("1"));
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                Stock stock = new Stock();
//                stock.setName(rs.getString("CompanyName"));
//                stock.setSymbol(rs.getString("StockSymbol"));
//                stock.setNumShares(rs.getInt("times"));
//                stock.setPrice(rs.getDouble("PricePerShare"));
//                stock.setType(rs.getString("Type"));
//                stocks.add(stock);
//            }
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }catch (SQLException s){
//            s.printStackTrace();
//        }
//        System.out.println(stocks.get(0).getSymbol());
//    }

    public Stock getDummyStock() {
        Stock stock = new Stock();
        stock.setName("Apple");
        stock.setSymbol("AAPL");
        stock.setPrice(150.0);
        stock.setNumShares(1200);
        stock.setType("Technology");
        return stock;
    }

    public List<Stock> getDummyStocks() {
        List<Stock> stocks = new ArrayList<Stock>();
        for(int i = 0; i<10; i++){
            stocks.add(getDummyStock());
        }
        return stocks;
    }

    public List<Stock> getActivelyTradedStocks() {
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of all the stocks has to be implemented
		 * Return list of actively traded stocks
		 */
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select O. StockSymbol, S.CompanyName, S.Type, count(O. StockSymbol) as Freq " +
                    "From Stock S, Orders O, Trade Tr " +
                    "Where O. OrderId = Tr. OrderId AND S.StockSymbol = O.StockSymbol " +
                    "Group By O. StockSymbol Order By count(O. StockSymbol) DESC");
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setType(rs.getString("Type"));
                stock.setNumShares(rs.getInt("Freq"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
//        return getDummyStocks();
    }

	public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Stock");
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setPrice(rs.getInt("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stock.setNumShares(rs.getInt("NumShares"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
	}

    public Stock getStockBySymbol(String stockSymbol) {
        /*
		 * The students code to fetch data from the database will be written here
		 * Return stock matching symbol
		 */
//        String like = "%"+stockSymbol+"%";
        Stock stock = new Stock();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "SELECT * FROM Stock WHERE StockSymbol = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, stockSymbol);
//            ps.setDouble(2, stockPrice);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setNumShares(rs.getInt("NumShares"));
                stock.setPrice(rs.getInt("PricePerShare"));
                stock.setType(rs.getString("Type"));
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stock;
    }

    public String setStockPrice(String stockSymbol, double stockPrice) {
        /*
         * The students code to fetch data from the database will be written here
         * Perform price update of the stock symbol
         */
        try{
            Class.forName("com.mysql.jdbc.Driver");
//            get current price
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query_0 = "SELECT PricePerShare FROM Stock WHERE StockSymbol=?";
            PreparedStatement ps_0 = con.prepareStatement(query_0);
            ps_0.setString(1, stockSymbol);
            ResultSet rs_0 = ps_0.executeQuery();
            double currentPrice = 0;
            if(rs_0.next()){
                currentPrice = rs_0.getDouble("PricePerShare");
            }
//            update price
            String query = "UPDATE Stock SET PricePerShare =? WHERE StockSymbol=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, stockPrice);
            ps.setString(2, stockSymbol);
            ps.execute();
            String query_2 = "INSERT INTO StockPriceHistory(StockSymbol, PricePerShare, DateNTime) VALUES (?, ?, default)";
            PreparedStatement ps_2 = con.prepareStatement(query_2);
            ps_2.setString(1, stockSymbol);
            ps_2.setDouble(2, stockPrice);
            ps_2.execute();
            System.out.println("current price:"+currentPrice);
//            select all order of the stock
            if(currentPrice < stockPrice){ //new price is rising, sell price of trailing order would rise
                String query_3 = "SELECT DISTINCT OrderId FROM ConditionalOrderHistory WHERE StockSymbol = ?";
                PreparedStatement ps_3 = con.prepareStatement(query_3);
                ps_3.setString(1, stockSymbol);
                ResultSet rs_3 = ps_3.executeQuery();
                String query_in="INSERT INTO ConditionalOrderHistory(StockSymbol, PricePerShare, DateNTime, OrderId) values(?,?,default,?)";
                while(rs_3.next()){
                    int orderId = rs_3.getInt("OrderId");
                    System.out.println("orderId"+orderId);
                    PreparedStatement ps_in = con.prepareStatement(query_in);
                    ps_in.setString(1, stockSymbol);
                    ps_in.setDouble(2,stockPrice);
                    ps_in.setInt(3,orderId);
                    ps_in.execute();
                }
            }else if(currentPrice > stockPrice){
//                select all order of this stock
                String query_5 = "SELECT DISTINCT OrderId FROM ConditionalOrderHistory WHERE StockSymbol = ?";
                PreparedStatement ps_5 = con.prepareStatement(query_5);
                ps_5.setString(1, stockSymbol);
                ResultSet rs_5 = ps_5.executeQuery();
                while(rs_5.next()) {
                    int orderId = rs_5.getInt("OrderId");
//                    find the latest update
                    String query_4 = "SELECT O.PriceType, O.NumShares FROM Orders O, ConditionalOrderHistory C " +
                            "WHERE O.OrderId=C.OrderId AND C.OrderId =? ORDER BY C.DateNTime DESC LIMIT 1";
                    PreparedStatement ps_4 = con.prepareStatement(query_4);
                    ps_4.setInt(1, orderId);
                    ResultSet rs_4 = ps_4.executeQuery();
                    while(rs_4.next()){
                        int numShare = rs_4.getInt("NumShares");
                        String priceType = rs_4.getString("PriceType");
                        String query_8 = "SELECT AccountId From Trade WHERE OrderId = ?";
                        PreparedStatement ps_8 = con.prepareStatement(query_8);
                        ps_8.setInt(1, orderId);
                        ResultSet rs_8 = ps_8.executeQuery();
                        int accountid = 0;
                        if(rs_8.next()){
                            accountid = rs_8.getInt("AccountId");
                        }

                        String q = "Select sum(NumShares) from Portfolio P WHERE P.AccountId = ? AND StockSymbol= ?";
                        PreparedStatement ps_sum = con.prepareStatement(q);
                        ps_sum.setInt(1, accountid);
                        ps_sum.setString(2, stockSymbol);
                        ResultSet rs_nums = ps_sum.executeQuery();
                        int sum = 0;
                        if(rs_nums.next()){
                            sum = rs_nums.getInt(1);
                        }
                        if(sum < numShare){
                            return "failure";
                        }else {
                            if (priceType.equals("TrailingStop")) {
//                            calculate the stop price
                                String query_6 = "Select ( C.PricePerShare - C.PricePerShare * (T. Percentage*0.01)) AS Price\n" +
                                        "From ConditionalOrderHistory C,TrailingStop T\n" +
                                        "Where C.OrderId = T. OrderId AND T.OrderId = ? ORDER BY C.DateNTime DESC LIMIT 1";
                                PreparedStatement ps_6 = con.prepareStatement(query_6);
                                ps_6.setInt(1, orderId);
                                ResultSet rs_6 = ps_6.executeQuery();
                                if (rs_6.next()) {
                                    double stopPrice = rs_6.getDouble("Price");
//                                if the stopPrice matches the new price, sell the stock
                                    if (stockPrice<=stopPrice) {
                                        PreparedStatement ps_trans = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                                        ps_trans.setString(1, "jiarchen");
                                        ps_trans.setString(2, "Transaction");
                                        ResultSet rs_transid = ps_trans.executeQuery();
                                        int transid = 0;
                                        if (rs_transid.next()) {
                                            transid = rs_transid.getInt(1);
                                        }
//                                    update the transaction id to trade table
                                        String query_up = "Update Trade SET TransactionId =? WHERE OrderId = ?";
                                        PreparedStatement ps_up = con.prepareStatement(query_up);
                                        ps_up.setInt(1, transid);
                                        ps_up.setInt(2, orderId);
                                        ps_up.execute();
//                                    calculate transfee and insert transaction
                                        String query_1 = "INSERT INTO Transaction(TransFee, DateNTime, PricePerShare) Values(?,default,?)";
                                        PreparedStatement ps_1 = con.prepareStatement(query_1);
                                        Double transfee = stockPrice * numShare;
                                        transfee = transfee * 0.05;
                                        ps_1.setDouble(1, transfee);
                                        ps_1.setDouble(2, stockPrice);
                                        ps_1.execute();
//
                                        String query_3 = "INSERT INTO Portfolio(AccountId, StockSymbol, NumShares) Values(?,?,?)";
                                        PreparedStatement ps_3 = con.prepareStatement(query_3);
                                        ps_3.setInt(1, accountid);
                                        ps_3.setString(2, stockSymbol);
                                        ps_3.setInt(3, -numShare);
                                        ps_3.execute();
                                    }
                                }
                            } else if (priceType.equals("HiddenStop")) {
                                String query_6 = "Select H.StopPrice From ConditionalOrderHistory C, HiddenStop H Where C. OrderId = H. OrderId And H.OrderId = ?";
                                PreparedStatement ps_6 = con.prepareStatement(query_6);
                                ps_6.setInt(1, orderId);
                                ResultSet rs_6 = ps_6.executeQuery();
                                if (rs_6.next()) {
                                    double stopPrice = rs_6.getDouble("StopPrice");
                                    if (stockPrice<=stopPrice) {
                                        PreparedStatement ps_trans = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                                        ps_trans.setString(1, "jiarchen");
                                        ps_trans.setString(2, "Transaction");
                                        ResultSet rs_transid = ps_trans.executeQuery();

                                        int transid = 0;
                                        if (rs_transid.next()) {
                                            transid = rs_transid.getInt(1);
                                        }

                                        String query_up = "Update Trade SET TransactionId =? WHERE OrderId = ?";
                                        PreparedStatement ps_up = con.prepareStatement(query_up);
                                        ps_up.setInt(1, transid);
                                        ps_up.setInt(2, orderId);
                                        ps_up.execute();

                                        String query_1 = "INSERT INTO Transaction(TransFee, DateNTime, PricePerShare) Values(?,default,?)";
                                        PreparedStatement ps_1 = con.prepareStatement(query_1);
                                        Double transfee = stockPrice * numShare;
                                        transfee = transfee * 0.05;
                                        ps_1.setDouble(1, transfee);
                                        ps_1.setDouble(2, stockPrice);
                                        ps_1.execute();

                                        String query_3 = "INSERT INTO Portfolio(AccountId, StockSymbol, NumShares) Values(?,?,?)";
                                        PreparedStatement ps_3 = con.prepareStatement(query_3);
                                        ps_3.setInt(1, accountid);
                                        ps_3.setString(2, stockSymbol);
                                        ps_3.setInt(3, -numShare);
                                        ps_3.execute();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return "success";
    }
	
	public List<Stock> getOverallBestsellers() {
		/*
		 * The students code to fetch data from the database will be written here
		 * Get list of bestseller stocks
		 */
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT S.*, count(O. StockSymbol) AS times FROM Stock S, Orders O, Trade Tr" +
                    "                    WHERE S.StockSymbol = O.StockSymbol AND O.OrderType='Buy' AND O. OrderId = Tr. OrderId " +
                    "                    Group By O. StockSymbol Order By count(O. StockSymbol) DESC");
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setNumShares(rs.getInt("times"));
                stock.setPrice(rs.getDouble("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
	}

    public List<Stock> getCustomerBestsellers(String customerID) {
		/*
		 * The students code to fetch data from the database will be written here.
		 * Get list of customer bestseller stocks
		 */
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "SELECT S.*, count(O. StockSymbol) AS times  FROM Stock S, Orders O, Trade Tr, Account A, Client C" +
                    "                    WHERE S.StockSymbol = O.StockSymbol AND O. OrderId = Tr. OrderId AND OrderType = 'Buy' AND Tr.AccountId = A.AccountId AND A.ClientId = C.ClientId AND C.ClientId = ?" +
                    "                    Group By O. StockSymbol Order By count(O. StockSymbol) DESC";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(customerID));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setNumShares(rs.getInt("times"));
                stock.setPrice(rs.getDouble("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
    }

	public List<Stock> getStocksByCustomer(String customerId) {
		/*
		 * The students code to fetch data from the database will be written here
		 * Get stockHoldings of customer with customerId
		 */
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "Select S.*,  SUM(P.NumShares)AS Bought\n" +
                    "From Portfolio P, Account A, Client C, Stock S\n" +
                    "Where P.StockSymbol = S.StockSymbol and P.AccountId = A.AccountId and A.ClientId = C.ClientId and C.ClientId=?\n" +
                    "GROUP BY P. StockSymbol\n";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(customerId));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setNumShares(rs.getInt("Bought"));
                stock.setPrice(rs.getDouble("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
	}

    public List<Stock> getStocksByName(String name) {

		/*
		 * The students code to fetch data from the database will be written here
		 * Return list of stocks matching "name"
		 */
		String like = "%"+name+"%";
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "SELECT * FROM Stock WHERE CompanyName like ?";//can be symbol anyway
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, like);
//            ps.setDouble(2, stockPrice);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setNumShares(rs.getInt("NumShares"));
                stock.setPrice(rs.getDouble("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
    }

    public List<Stock> getStockSuggestions(String customerId) {
		/*
		 * The students code to fetch data from the database will be written here
		 * Return stock suggestions for given "customerId"
		 */
        System.out.println("id:"+customerId);
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "Select DISTINCT S.StockSymbol, S. CompanyName, S. Type, S.NumShares , S.PricePerShare From Person P, Client C, Account A, Orders O, Transaction T, Stock S, Trade Tr " +
                    "Where P. SSN = C. SSN and C. ClientId = A. ClientId and Tr. AccountId = A. AccountId and Tr. TransactionId = T. TransactionId and Tr. OrderId = O. OrderId and O. StockSymbol = S. StockSymbol and C. ClientId = ?";//can be symbol anyway
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(customerId));
//            ps.setDouble(2, stockPrice);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setNumShares(rs.getInt("NumShares"));
                stock.setPrice(rs.getDouble("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
//        return getDummyStocks();
    }

    public List<Stock> getStockPriceHistory(String stockSymbol) {
		/*
		 * The students code to fetch data from the database
		 * Return list of stock objects, showing price history
		 */
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "SELECT  H.StockSymbol,S.CompanyName,S.Type, H.DateNTime, H.PricePerShare FROM Stock S, StockPriceHistory H WHERE S.StockSymbol=H.StockSymbol AND H.StockSymbol =?";//can be symbol anyway
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, stockSymbol);
//            ps.setDouble(2, stockPrice);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setDatetime(rs.getTimestamp("DateNTime"));
                stock.setPrice(rs.getDouble("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
//        return getDummyStocks();
    }

    public List<String> getStockTypes() {

		/*
		 * The students code to fetch data from the database will be written here.
		 * Populate types with stock types
		 */
        List<String> types = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT Type FROM Stock");
            while(rs.next()) {
                types.add(rs.getString("Type"));
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return types;
    }

    public List<Stock> getStockByType(String stockType) {
		/*
		 * The students code to fetch data from the database will be written here
		 * Return list of stocks of type "stockType"
		 */
        List<Stock> stocks = new ArrayList<Stock>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "SELECT * FROM Stock WHERE Type= ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, stockType);
//            ps.setDouble(2, stockPrice);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Stock stock = new Stock();
                stock.setName(rs.getString("CompanyName"));
                stock.setSymbol(rs.getString("StockSymbol"));
                stock.setNumShares(rs.getInt("NumShares"));
                stock.setPrice(rs.getInt("PricePerShare"));
                stock.setType(rs.getString("Type"));
                stocks.add(stock);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return stocks;
    }
}
