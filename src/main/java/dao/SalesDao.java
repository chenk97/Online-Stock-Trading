package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import model.RevenueItem;
import model.Stock;

public class SalesDao {

//    public static void main(String[] args) {
//        List<RevenueItem> items = new ArrayList<RevenueItem>();
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
//            String query = "SELECT DISTINCT Tr.AccountId, S. StockSymbol, O.DateNTime, O.PricePerShare, O.NumShares, ROUND(O.PricePerShare*O.NumShares) AS Amount\n" +
//                    "From Stock S, Trade Tr, Transaction T, Name N, Orders O\n" +
//                    "Where (S. StockSymbol = O. StockSymbol and T. TransactionId = Tr. TransactionId\n" +
//                    "and O.OrderId = Tr. OrderId and Tr. AccountId = N. AccountId and S. StockSymbol = O.StockSymbol AND S.StockSymbol like ?)\n" +
//                    "OR(S. StockSymbol = O. StockSymbol and T. TransactionId = Tr. TransactionId\n" +
//                    "and O.OrderId = Tr. OrderId and Tr. AccountId = N. AccountId and S. StockSymbol = O.StockSymbol AND S.Type like ?)\n" +
//                    "OR (S. StockSymbol = O. StockSymbol and T. TransactionId = Tr. TransactionId\n" +
//                    "and O.OrderId = Tr. OrderId and Tr. AccountId = N. AccountId and S. StockSymbol = O.StockSymbol AND CONCAT(FirstName,' ',LastName)like ?)";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1,"%karin chen%");
//            ps.setString(2,"%karin chen%");
//            ps.setString(3,"%karin chen%");
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()) {
//                RevenueItem item = new RevenueItem();
//                item.setDate(rs.getTimestamp("DateNTime"));
//                item.setNumShares(rs.getInt("NumShares"));
//                item.setAccountId(Integer.toString(rs.getInt("AccountId")));
//                item.setPricePerShare(rs.getDouble("PricePerShare"));
//                item.setStockSymbol(rs.getString("StockSymbol"));
//                item.setAmount(rs.getInt("Amount"));
//                items.add(item);
//            }
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }catch (SQLException s){
//            s.printStackTrace();
//        }
//        System.out.println(items.get(0).getStockSymbol());
//    }

    private List<RevenueItem> getDummyRevenueItems()
    {
        List<RevenueItem> items = new ArrayList<RevenueItem>();

		/*Sample data begins*/
        for (int i = 0; i < 10; i++) {
            RevenueItem item = new RevenueItem();
            item.setDate(new Date());
            item.setNumShares(5);
            item.setAccountId("foo");
            item.setPricePerShare(50.0);
            item.setStockSymbol("AAPL");
            item.setAmount(150.0);
            items.add(item);
        }
        /*Sample data ends*/

        return items;
    }
    public List<RevenueItem> getSalesReport(String month, String year) {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get sales report for a particular month and year
		 */
        List<RevenueItem> items = new ArrayList<RevenueItem>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "Select O. NumShares, T.PricePerShare, T. DateNTime, Tr.StockSymbol, Tr. AccountId, ROUND(O.PricePerShare*O.NumShares) AS Amount From Transaction T, OrderS O, Trade Tr " +
                    "WHERE YEAR(T.DateNTime) = ? AND MONTH(T.DateNTime) = ? and O. OrderId = TR.OrderId and T.TransactionId = Tr.TransactionId";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(year));
            ps.setInt(2, Integer.parseInt(month));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                RevenueItem item = new RevenueItem();
                item.setDate(rs.getTimestamp("DateNTime"));
                item.setNumShares(rs.getInt("NumShares"));
                item.setAccountId(Integer.toString(rs.getInt("AccountId")));
                item.setPricePerShare(rs.getDouble("PricePerShare"));
                item.setStockSymbol(rs.getString("StockSymbol"));
                item.setAmount(rs.getInt("Amount"));
                items.add(item);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return items;
    }



    public List<RevenueItem> getSummaryListing(String searchKeyword) {
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to fetch details of summary listing of revenue generated by
		 * a particular stock, stock type or customer must be implemented
		 * Store the revenue generated by an item in the amount attribute
		 */
        List<RevenueItem> items = new ArrayList<RevenueItem>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "Select distinct Tr.AccountId, S. StockSymbol, O.DateNTime, O.PricePerShare, O.NumShares, ROUND(O.PricePerShare*O.NumShares) AS Amount\n" +
                    "From Stock S, Trade Tr, Transaction T, Name N, Orders O\n" +
                    "Where (S. StockSymbol = O. StockSymbol and T. TransactionId = Tr. TransactionId\n" +
                    "and O.OrderId = Tr. OrderId and Tr. AccountId = N. AccountId and S. StockSymbol = O.StockSymbol AND S.StockSymbol like ?)\n" +
                    "OR(S. StockSymbol = O. StockSymbol and T. TransactionId = Tr. TransactionId\n" +
                    "and O.OrderId = Tr. OrderId and Tr. AccountId = N. AccountId and S. StockSymbol = O.StockSymbol AND S.Type like ?)\n" +
                    "OR (S. StockSymbol = O. StockSymbol and T. TransactionId = Tr. TransactionId\n" +
                    "and O.OrderId = Tr. OrderId and Tr. AccountId = N. AccountId and S. StockSymbol = O.StockSymbol AND CONCAT(FirstName,' ',LastName)like ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,"%"+searchKeyword+"%");
            ps.setString(2,"%"+searchKeyword+"%");
            ps.setString(3,"%"+searchKeyword+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                RevenueItem item = new RevenueItem();
                item.setDate(rs.getTimestamp("DateNTime"));
                item.setNumShares(rs.getInt("NumShares"));
                item.setAccountId(Integer.toString(rs.getInt("AccountId")));
                item.setPricePerShare(rs.getDouble("PricePerShare"));
                item.setStockSymbol(rs.getString("StockSymbol"));
                item.setAmount(rs.getInt("Amount"));
                items.add(item);
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return items;
//        return getDummyRevenueItems();
    }
}
