package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDao {
//    public static void main(String[] args) {
//        String orderId="1";
//        List<OrderPriceEntry> orderPriceHistory = new ArrayList<OrderPriceEntry>();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
//            String query = "Select O.PriceType FROM Orders O, ConditionalOrderHistory C " +
//                    "WHERE O.OrderId=C.OrderId AND C.OrderId=?";
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setInt(1, Integer.parseInt(orderId));
//            ResultSet rs = ps.executeQuery();
//            String PriceType = "";
//            if (rs.next()) {
//                PriceType = rs.getString("PriceType");
//                System.out.println(PriceType);
//                if (PriceType.equals("TrailingStop")) {
//                    System.out.println("test2");
//                    String query_2 = "Select C.OrderId, C.DateNTime, C.StockSymbol,C. PricePerShare, " +
//                            "( C.PricePerShare - C.PricePerShare * T. Percentage) AS Price\n" +
//                            "From ConditionalOrderHistory C,TrailingStop T\n" +
//                            "Where C.OrderId = T. OrderId AND T.OrderId = ?";
//                    PreparedStatement ps_2 = con.prepareStatement(query_2);
//                    ps_2.setInt(1, Integer.parseInt(orderId));
//                    ResultSet rs_2 = ps_2.executeQuery();
//                    while (rs_2.next()) {
//                        System.out.println("test");
//                        OrderPriceEntry entry = new OrderPriceEntry();
//                        entry.setOrderId(Integer.toString(rs_2.getInt("OrderId")));
//                        entry.setDate(rs_2.getTimestamp("DateNTime"));
//                        entry.setStockSymbol(rs_2.getString("StockSymbol"));
//                        entry.setPricePerShare(rs_2.getDouble("PricePerShare"));
//                        entry.setPrice(rs_2.getDouble("Price"));
//                        orderPriceHistory.add(entry);
//                    }
//                } else if (PriceType.equals("HiddenStop")) {
//                    String query_2 = "Select C.OrderId, C.StockSymbol, C.PricePerShare, H.StopPrice, C.DateNTime\n" +
//                            "From ConditionalOrderHistory C, HiddenStop H\n" +
//                            "Where C. OrderId = H. OrderId";
//                    PreparedStatement ps_2 = con.prepareStatement(query_2);
//                    ResultSet rs_2 = ps_2.executeQuery();
//                    while (rs_2.next()) {
//                        OrderPriceEntry entry = new OrderPriceEntry();
//                        entry.setOrderId(Integer.toString(rs_2.getInt("OrderId")));
//                        entry.setDate(rs_2.getTimestamp("DateNTime"));
//                        entry.setStockSymbol(rs_2.getString("StockSymbol"));
//                        entry.setPricePerShare(rs_2.getDouble("PricePerShare"));
//                        entry.setPrice(rs_2.getDouble("Price"));
//                        orderPriceHistory.add(entry);
//                    }
//                }
//            }
//        } catch(ClassNotFoundException e){
//            e.printStackTrace();
//        }catch(SQLException s){
//            s.printStackTrace();
//        }
//        System.out.println(orderPriceHistory.get(0).getStockSymbol());
//    }

    public Order getDummyTrailingStopOrder() {
        TrailingStopOrder order = new TrailingStopOrder();

        order.setId(1);
        order.setDatetime(new Date());
        order.setNumShares(5);
        order.setPercentage(12.0);
        return order;
    }

    public Order getDummyMarketOrder() {
        MarketOrder order = new MarketOrder();

        order.setId(1);
        order.setDatetime(new Date());
        order.setNumShares(5);
        order.setBuySellType("buy");
        return order;
    }

    public Order getDummyMarketOnCloseOrder() {
        MarketOnCloseOrder order = new MarketOnCloseOrder();

        order.setId(1);
        order.setDatetime(new Date());
        order.setNumShares(5);
        order.setBuySellType("buy");
        return order;
    }

    public Order getDummyHiddenStopOrder() {
        HiddenStopOrder order = new HiddenStopOrder();

        order.setId(1);
        order.setDatetime(new Date());
        order.setNumShares(5);
        order.setPricePerShare(145.0);
        return order;
    }

    public List<Order> getDummyOrders() {
        List<Order> orders = new ArrayList<Order>();

        for (int i = 0; i < 3; i++) {
            orders.add(getDummyTrailingStopOrder());
        }

        for (int i = 0; i < 3; i++) {
            orders.add(getDummyMarketOrder());
        }

        for (int i = 0; i < 3; i++) {
            orders.add(getDummyMarketOnCloseOrder());
        }

        for (int i = 0; i < 3; i++) {
            orders.add(getDummyHiddenStopOrder());
        }
        return orders;
    }

    public String submitOrder(Order order, Customer customer, Employee employee, Stock stock) {
        /*
         * Student code to place stock order
         * Employee can be null, when the order is placed directly by Customer
         * */
        /*Sample data begins*/
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            //String PriceType = order.getPriceType();
            Class cl = order.getClass();
            String a = cl.getName();
            if(a.equals("model.MarketOrder")){
                MarketOrder mo= (MarketOrder) order;
                int numShare = order.getNumShares();
                int stockShare = stock.getNumShares();
                if(stockShare < numShare && mo.getBuySellType().equals("Buy")){
                    return "failure";
                }
                String buyselltype = mo.getBuySellType();
                PreparedStatement ps_out = con.prepareStatement("Select AccountId From Account A Where A.ClientId = ?");
                int cid = Integer.parseInt(customer.getClientId());
                ps_out.setInt(1, cid);
                ResultSet rs = ps_out.executeQuery();
                int b = 0;
                if(rs.next()){
                    b = rs.getInt("AccountId");
                }
                if(buyselltype.equals("Buy")){
                    int share = stockShare - numShare;
                    String stock_update = ("UPDATE Stock SET NumShares=? WHERE StockSymbol=? ");
                    PreparedStatement ps_stock = con.prepareStatement(stock_update);
                    ps_stock.setInt(1, share);
                    ps_stock.setString(2, stock.getSymbol());
                    ps_stock.execute();
                }else{
                    String q = "Select sum(NumShares) from Portfolio P WHERE P.AccountId = ? AND StockSymbol= ?";
                    PreparedStatement ps = con.prepareStatement(q);
                    ps.setInt(1, b);
                    ps.setString(2, stock.getSymbol());
                    ResultSet rs_nums = ps.executeQuery();
                    int sum = 0;
                    if(rs_nums.next()){
                        sum = rs_nums.getInt(1);
                    }
                    if(sum < numShare){
                        return "failure";
                    }else{

                        int share = stockShare + numShare;
                        String stock_update = ("UPDATE Stock SET NumShares=? WHERE StockSymbol= ?");
                        PreparedStatement ps_stock = con.prepareStatement(stock_update);
                        ps_stock.setInt(1, share);
                        ps_stock.setString(2, stock.getSymbol());
                        ps_stock.execute();
                        numShare = -numShare;
                    }
                }


                String query = "insert into Orders(NumShares, PricePerShare, DateNTime, OrderType, PriceType, StockSymbol) Values(?,?,default,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, order.getNumShares());
                ps.setDouble(2, stock.getPrice());
                ps.setString(3, mo.getBuySellType());
                ps.setString(4, mo.getPriceType());
                ps.setString(5, stock.getSymbol());
                ps.execute();


                String query_1 = "INSERT INTO Transaction(TransFee, DateNTime, PricePerShare) Values(?,default,?)";
                PreparedStatement ps_1 = con.prepareStatement(query_1);
                Double d = stock.getPrice() * order.getNumShares();
                d = d * 0.05;
                ps_1.setDouble(1, d);
                ps_1.setDouble(2, stock.getPrice());
                ps_1.execute();
                //PreparedStatement ps_out = con.prepareStatement("Select AccountId From Account A Where A.ClientId = ?");
//                    int r = 1;//////////////////////////////////////////////hard code
//                    ps_out.setInt(1,r );
//                    int cid = Integer.parseInt(customer.getClientId());
//                    ps_out.setInt(1, cid);
//                    ResultSet rs = ps_out.executeQuery();
//                    int b = 0;
//                    if(rs.next()){
//                        b = rs.getInt("AccountId");
//                    }

                PreparedStatement ps_order = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_order.setString(1, "jiarchen");
                ps_order.setString(2,"Orders");
                ResultSet rs_order = ps_order.executeQuery();
                int orderid = 0;
                if(rs_order.next()){
                    orderid = rs_order.getInt(1);
                    orderid--;
                }
                //Statement st_1 = con.createStatement();
                PreparedStatement ps_trans = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_trans.setString(1, "jiarchen");
                ps_trans.setString(2,"Transaction");
                ResultSet rs_transid = ps_trans.executeQuery();
                int transid = 0;
                if(rs_transid.next()){
                    transid = rs_transid.getInt(1);
                    transid--;
                }
                String em = "";
                if(employee == null){
                    em = null;
                }else{
                    em = employee.getEmployeeID();
                }
                String query_2 = "INSERT INTO Trade(AccountId, BrokerId, OrderId, TransactionId, StockSymbol) Values(?,?,?,?,?)";
                PreparedStatement ps_2 = con.prepareStatement(query_2);
                ps_2.setInt(1, b);
                ps_2.setString(2, em);
                ps_2.setInt(3, orderid); ////////ordetid
                ps_2.setInt(4, transid);
                ps_2.setString(5, stock.getSymbol());
                ps_2.execute();

                String query_3 = "INSERT INTO Portfolio(AccountId, StockSymbol, NumShares) Values(?,?,?)";
                PreparedStatement ps_3 = con.prepareStatement(query_3);
                ps_3.setInt(1, b);
                ps_3.setString(2, stock.getSymbol());
                ps_3.setInt(3, numShare);
                ps_3.execute();
            }else if(a.equals("model.MarketOnCloseOrder")){
                MarketOnCloseOrder moco = (MarketOnCloseOrder)order;
                PreparedStatement ps_out = con.prepareStatement("Select AccountId From Account A Where A.ClientId = ?");
//                    int r = 1;//////////////////////////////////////////////hard code
//                    ps_out.setInt(1,r );
                int cid = Integer.parseInt(customer.getClientId());
                ps_out.setInt(1, cid);
                ResultSet rs = ps_out.executeQuery();
                int b = 0;
                if(rs.next()){
                    b = rs.getInt("AccountId");
                }
                int numShare = order.getNumShares();
                int stockShare = stock.getNumShares();
                if(stockShare < numShare && moco.getBuySellType().equals("Buy")){
                    return "failure";
                }
                String buyselltype = moco.getBuySellType();
                if(buyselltype.equals("Buy")){
                    int share = stockShare - numShare;
                    String stock_update = ("UPDATE Stock SET NumShares=? WHERE StockSymbol=? ");
                    PreparedStatement ps_stock = con.prepareStatement(stock_update);
                    ps_stock.setInt(1, share);
                    ps_stock.setString(2, stock.getSymbol());
                    ps_stock.execute();
                }else{
                    String q = "Select sum(NumShares) from Portfolio P WHERE P.AccountId = ? AND StockSymbol= ?";
                    PreparedStatement ps = con.prepareStatement(q);
                    ps.setInt(1, b);
                    ps.setString(2, stock.getSymbol());
                    ResultSet rs_nums = ps.executeQuery();
                    int sum = 0;
                    if(rs_nums.next()){
                        sum = rs_nums.getInt(1);
                    }
                    if(sum < numShare){
                        return "failure";
                    }else{

                        int share = stockShare + numShare;
                        String stock_update = ("UPDATE Stock SET NumShares=? WHERE StockSymbol= ?");
                        PreparedStatement ps_stock = con.prepareStatement(stock_update);
                        ps_stock.setInt(1, share);
                        ps_stock.setString(2, stock.getSymbol());
                        ps_stock.execute();
                        numShare = -numShare;
                    }
                }
                String query = "insert into Orders(NumShares, PricePerShare, DateNTime, OrderType, PriceType, StockSymbol) Values(?,?,default,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, order.getNumShares());
                ps.setDouble(2, stock.getPrice());
                ps.setString(3, moco.getBuySellType());
                ps.setString(4, moco.getPriceType());
                ps.setString(5, stock.getSymbol());
                ps.execute();

                String query_1 = "INSERT INTO Transaction(TransFee, DateNTime, PricePerShare) Values(?,default,?)";
                PreparedStatement ps_1 = con.prepareStatement(query_1);
                Double d = stock.getPrice() * order.getNumShares();
                d = d * 0.05;
                ps_1.setDouble(1, d);
                ps_1.setDouble(2, stock.getPrice());
                ps_1.execute();


//                    PreparedStatement ps_out = con.prepareStatement("Select AccountId From Account A Where A.ClientId = ?");
////                    int r = 1;//////////////////////////////////////////////hard code
////                    ps_out.setInt(1,r );
//                    int cid = Integer.parseInt(customer.getClientId());
//                    ps_out.setInt(1, cid);
//                    ResultSet rs = ps_out.executeQuery();
//                    int b = 0;
//                    if(rs.next()){
//                        b = rs.getInt("AccountId");
//                    }

                PreparedStatement ps_order = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_order.setString(1, "jiarchen");
                ps_order.setString(2,"Orders");
                ResultSet rs_order = ps_order.executeQuery();
                int orderid = 0;
                if(rs_order.next()){
                    orderid = rs_order.getInt(1);
                    orderid--;
                }

                PreparedStatement ps_trans = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_trans.setString(1, "jiarchen");
                ps_trans.setString(2,"Transaction");
                ResultSet rs_transid = ps_trans.executeQuery();
                int transid = 0;
                if(rs_transid.next()){
                    transid = rs_transid.getInt(1);
                    transid--;
                }
                String em = "";
                if(employee == null){
                    em = null;
                }else{
                    em = employee.getEmployeeID();
                }
                String query_2 = "INSERT INTO Trade(AccountId, BrokerId, OrderId, TransactionId, StockSymbol) Values(?,?,?,?,?)";
                PreparedStatement ps_2 = con.prepareStatement(query_2);
                ps_2.setInt(1, b);
                ps_2.setString(2, em);
                ps_2.setInt(3, orderid); ////////ordetid
                ps_2.setInt(4, transid);
                ps_2.setString(5, stock.getSymbol());
                ps_2.execute();

                String query_3 = "INSERT INTO Portfolio(AccountId, StockSymbol, NumShares) Values(?,?,?)";
                PreparedStatement ps_3 = con.prepareStatement(query_3);
                ps_3.setInt(1, b);
                ps_3.setString(2, stock.getSymbol());
                ps_3.setInt(3, numShare);
                ps_3.execute();

            }else if(a.equals("model.TrailingStopOrder")){
                TrailingStopOrder tso =(TrailingStopOrder) order;
                int numShare = order.getNumShares();
                int stockShare = stock.getNumShares();
                PreparedStatement ps_out = con.prepareStatement("Select AccountId From Account A Where A.ClientId = ?");
                //int r = 1;//////////////////////////////////////////////hard code
                int cid = Integer.parseInt(customer.getClientId());
                ps_out.setInt(1, cid);
                ResultSet rs = ps_out.executeQuery();
                int accountid = 0;
                if(rs.next()){
                    accountid = rs.getInt("AccountId");
                }
                String q = "Select sum(NumShares) from Portfolio P WHERE P.AccountId = ? AND StockSymbol= ?";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, accountid);
                ps.setString(2, stock.getSymbol());
                ResultSet rs_nums = ps.executeQuery();
                int sum = 0;
                if(rs_nums.next()){
                    sum = rs_nums.getInt(1);
                }
                if(sum < numShare){
                    return "failure";
                }else{

                    int share = stockShare + numShare;
                    String stock_update = ("UPDATE Stock SET NumShares=? WHERE StockSymbol= ?");
                    PreparedStatement ps_stock = con.prepareStatement(stock_update);
                    ps_stock.setInt(1, share);
                    ps_stock.setString(2, stock.getSymbol());
                    ps_stock.execute();
                    numShare = -numShare;
                }
//                    int share = stockShare + numShare;
//                        String stock_update = ("UPDATE Stock SET NumShares=? WHERE StockSymbol= ?");
//                        PreparedStatement ps_stock = con.prepareStatement(stock_update);
//                        ps_stock.setInt(1, share);
//                        ps_stock.setString(2, stock.getSymbol());
//                        ps_stock.execute();

                //numShare = 0 - numShare;
                String query = "insert into Orders(NumShares, PricePerShare, DateNTime, OrderType, PriceType, StockSymbol) Values(?,?,default,?,?,?)";
                PreparedStatement ps_o = con.prepareStatement(query);
                ps_o.setInt(1, order.getNumShares());
                ps_o.setDouble(2, stock.getPrice());
                ps_o.setString(3, "Sell");
                ps_o.setString(4, tso.getPriceType());
                ps_o.setString(5, stock.getSymbol());
                ps_o.execute();


                String query_1 = "INSERT INTO Transaction(TransFee, DateNTime, PricePerShare) Values(?,default,?)";
                PreparedStatement ps_1 = con.prepareStatement(query_1);
                Double d = stock.getPrice() * order.getNumShares();
                d = d * 0.05;
                ps_1.setDouble(1, d);
                ps_1.setDouble(2, stock.getPrice());
                ps_1.execute();

//                    PreparedStatement ps_out = con.prepareStatement("Select AccountId From Account A Where A.ClientId = ?");
//                    //int r = 1;//////////////////////////////////////////////hard code
//                    int cid = Integer.parseInt(customer.getClientId());
//                    ps_out.setInt(1, cid);
//                    ResultSet rs = ps_out.executeQuery();
//                    int accountid = 0;
//                    if(rs.next()){
//                        accountid = rs.getInt("AccountId");
//                    }

                PreparedStatement ps_order = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_order.setString(1, "jiarchen");
                ps_order.setString(2,"Orders");
                ResultSet rs_order = ps_order.executeQuery();
                int orderid = 0;
                if(rs_order.next()){
                    orderid = rs_order.getInt(1);
                    orderid --;
                }

                PreparedStatement ps_trans = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_trans.setString(1, "jiarchen");
                ps_trans.setString(2,"Transaction");
                ResultSet rs_transid = ps_trans.executeQuery();
                int transid = 0;
                if(rs_transid.next()){
                    transid = rs_transid.getInt(1);
                    transid --;
                }
                String em = "";
                if(employee == null){
                    em = null;
                }else{
                    em = employee.getEmployeeID();
                }
                String query_2 = "INSERT INTO Trade(AccountId, BrokerId, OrderId, TransactionId, StockSymbol) Values(?,?,?,?,?)";
                PreparedStatement ps_2 = con.prepareStatement(query_2);
                ps_2.setInt(1, accountid);
                ps_2.setString(2, em);
                ps_2.setInt(3, orderid); ////////ordetid
                ps_2.setInt(4, transid);
                ps_2.setString(5, stock.getSymbol());
                ps_2.execute();

                String query_3 = "INSERT INTO Portfolio(AccountId, StockSymbol, NumShares) Values(?,?,?)";
                PreparedStatement ps_3 = con.prepareStatement(query_3);
                ps_3.setInt(1, accountid);
                ps_3.setString(2, stock.getSymbol());
                ps_3.setInt(3, numShare);
                ps_3.execute();

                String query_tsp = "INSERT INTO TrailingStop (OrderId, Percentage)VALUES(?, ?)";
                PreparedStatement ps_tsp = con.prepareStatement(query_tsp);
                Double c= tso.getPercentage();
                ps_tsp.setInt(1, orderid);
                ps_tsp.setDouble(2, c);
                ps_tsp.execute();

                String query_coh = "INSERT INTO ConditionalOrderHistory (OrderId, StockSymbol, PricePerShare, DateNTime) values(?,?,?,default)";
                PreparedStatement coh = con.prepareStatement(query_coh);
                coh.setInt(1, orderid);
                coh.setString(2, stock.getSymbol());
                coh.setDouble(3, stock.getPrice());
                coh.execute();
            }else{
                HiddenStopOrder hso = (HiddenStopOrder)order;
                int numShare = order.getNumShares();
                int stockShare = stock.getNumShares();

                PreparedStatement ps_out = con.prepareStatement("Select AccountId From Account A Where A.ClientId = ?");
//                    int r = 1;//////////////////////////////////////////////hard code
//                    ps_out.setInt(1, 1);
                int cid = Integer.parseInt(customer.getClientId());
                ps_out.setInt(1, cid);
                ResultSet rs = ps_out.executeQuery();
                int accountid = 0;
                if(rs.next()){
                    accountid = rs.getInt("AccountId");
                }
                String q = "Select sum(NumShares) from Portfolio P WHERE P.AccountId = ? AND StockSymbol= ?";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, accountid);
                ps.setString(2, stock.getSymbol());
                ResultSet rs_nums = ps.executeQuery();
                int sum = 0;
                if(rs_nums.next()){
                    sum = rs_nums.getInt(1);
                }
                if(sum < numShare){
                    return "failure";
                }else{

                    int share = stockShare + numShare;
                    String stock_update = ("UPDATE Stock SET NumShares=? WHERE StockSymbol= ?");
                    PreparedStatement ps_stock = con.prepareStatement(stock_update);
                    ps_stock.setInt(1, share);
                    ps_stock.setString(2, stock.getSymbol());
                    ps_stock.execute();
                    numShare = -numShare;
                }

                String query = "insert into Orders(NumShares, PricePerShare, DateNTime, OrderType, PriceType, StockSymbol) Values(?,?,default,?,?,?)";
                PreparedStatement ps_o = con.prepareStatement(query);
                ps_o.setInt(1, order.getNumShares());
                ps_o.setDouble(2, stock.getPrice());
                ps_o.setString(3, "Sell");
                ps_o.setString(4, hso.getPriceType());
                ps_o.setString(5, stock.getSymbol());
                ps_o.execute();

                String query_1 = "INSERT INTO Transaction(TransFee, DateNTime, PricePerShare) Values(?,default,?)";
                PreparedStatement ps_1 = con.prepareStatement(query_1);
                Double d = stock.getPrice() * order.getNumShares();
                d = d * 0.05;
                ps_1.setDouble(1, d);
                ps_1.setDouble(2, stock.getPrice());
                ps_1.execute();

                PreparedStatement ps_order = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_order.setString(1, "jiarchen");
                ps_order.setString(2,"Orders");
                ResultSet rs_order = ps_order.executeQuery();
                int orderid = 0;
                if(rs_order.next()){
                    orderid = rs_order.getInt(1);
                    orderid--;
                }

                PreparedStatement ps_trans = con.prepareStatement("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA =? AND TABLE_NAME =?");
                ps_trans.setString(1, "jiarchen");
                ps_trans.setString(2,"Transaction");
                ResultSet rs_transid = ps_trans.executeQuery();
                int transid = 0;
                if(rs_transid.next()){
                    transid = rs_transid.getInt(1);
                    transid --;
                }
                String em = "";
                if(employee == null){
                    em = null;
                }else{
                    em = employee.getEmployeeID();
                }

                String query_2 = "INSERT INTO Trade(AccountId, BrokerId, OrderId, TransactionId, StockSymbol) Values(?,?,?,?,?)";
                PreparedStatement ps_2 = con.prepareStatement(query_2);
                ps_2.setInt(1, accountid);
                ps_2.setString(2, em);
                ps_2.setInt(3, orderid); ////////ordetid
                ps_2.setInt(4, transid);
                ps_2.setString(5, stock.getSymbol());
                ps_2.execute();

                String query_3 = "INSERT INTO Portfolio(AccountId, StockSymbol, NumShares) Values(?,?,?)";
                PreparedStatement ps_3 = con.prepareStatement(query_3);
                ps_3.setInt(1, accountid);
                ps_3.setString(2, stock.getSymbol());
                ps_3.setInt(3, numShare);
                ps_3.execute();

                String query_tsp = "INSERT INTO HiddenStop (OrderId, StopPrice)VALUES(?, ?)";
                PreparedStatement ps_tsp = con.prepareStatement(query_tsp);
                ps_tsp.setInt(1, orderid);
                ps_tsp.setDouble(2, hso.getPricePerShare());
                ps_tsp.execute();

                String query_coh = "INSERT INTO ConditionalOrderHistory (OrderId, StockSymbol, PricePerShare, DateNTime) values(?,?,?,default)";
                PreparedStatement coh = con.prepareStatement(query_coh);
                coh.setInt(1, orderid);
                coh.setString(2, stock.getSymbol());
                coh.setDouble(3, stock.getPrice());
                coh.execute();
            }


        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return "success";
        /*Sample data ends*/
    }

    public List<Order> getOrderByStockSymbol(String stockSymbol) {
        /*
         * Student code to get orders by stock symbol
         */
        List<Order> orders = new ArrayList<Order>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            PreparedStatement ps = con.prepareStatement("Select O.OrderId, O.DateNTime, O.NumShares, O.DateNTime, O.OrderType, O.PriceType From Orders O Where O.StockSymbol = ?");
            ps.setString(1, stockSymbol);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getString("PriceType").equals("Market")){
                    MarketOrder order=new MarketOrder();
                    order.setBuySellType(rs.getString("OrderType"));
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    order.setBuySellType(rs.getString("OrderType"));
                    System.out.println(order.getPriceType());
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("MarketOnClose")){
                    MarketOnCloseOrder order=new MarketOnCloseOrder();
                    order.setBuySellType(rs.getString("OrderType"));
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    order.setBuySellType(rs.getString("OrderType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("TrailingStop")) {
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT T.Percentage from TrailingStop T, Orders O where O.OrderId=T.OrderId");
                    Double percentage=0.0;
                    if(rs2.next()){
                        percentage = rs2.getDouble("Percentage");
                    }
                    TrailingStopOrder order = new TrailingStopOrder();
                    order.setPercentage(percentage);
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("HiddenStop")) {
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT H.StopPrice from HiddenStop H, Orders O where O.OrderId=H.OrderId");
                    Double pricepershare=0.0;
                    if(rs2.next()){
                        pricepershare = rs2.getDouble("StopPrice");
                    }
                    HiddenStopOrder order = new HiddenStopOrder();
                    order.setPricePerShare(pricepershare);
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    orders.add(order);
                }
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrderByCustomerName(String customerName) {
        /*
         * Student code to get orders by customer name
         */
        List<Order> orders = new ArrayList<Order>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "Select O. OrderId, O. NumShares, O. PricePerShare, O. DateNTime, O. OrderType, O.PriceType" +
                    "                    From Orders O, Person P, Client C, Account A, Trade Tr" +
                    "                    Where O. OrderId = Tr. OrderId and Tr. AccountId= A. AccountId and A.ClientId = C.ClientId and" +
                    "                    C. SSN = P. SSN and CONCAT(P.FirstName,?,P.LastName) = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, " ");
            ps.setString(2, customerName);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getString("PriceType").equals("Market")){
                    MarketOrder order=new MarketOrder();
                    order.setBuySellType(rs.getString("OrderType"));
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    order.setBuySellType(rs.getString("OrderType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("MarketOnClose")){
                    MarketOnCloseOrder order=new MarketOnCloseOrder();
                    order.setBuySellType(rs.getString("OrderType"));
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    order.setBuySellType(rs.getString("OrderType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("TrailingStop")) {
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT T.Percentage from TrailingStop T, Orders O where O.OrderId=T.OrderId");
                    Double percentage=0.0;
                    if(rs2.next()){
                        percentage = rs2.getDouble("Percentage");
                    }
                    TrailingStopOrder order = new TrailingStopOrder();
                    order.setPercentage(percentage);
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("HiddenStop")) {
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT H.StopPrice from HiddenStop H, Orders O where O.OrderId=H.OrderId");
                    Double pricepershare=0.0;
                    if(rs2.next()){
                        pricepershare = rs2.getDouble("StopPrice");
                    }
                    HiddenStopOrder order = new HiddenStopOrder();
                    order.setPricePerShare(pricepershare);
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    orders.add(order);
                }
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return orders;
//        return getDummyOrders();
    }

    public List<Order> getOrderHistory(String customerId) {
        /*
         * The students code to fetch data from the database will be written here
         * Show orders for given customerId
         */
        List<Order> orders = new ArrayList<Order>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "Select DISTINCT O. OrderId, O. NumShares, O. PricePerShare, O. DateNTime, O. OrderType, O.PriceType" +
                    "                    From Orders O, Client C, Account A, Trade Tr" +
                    "                    Where O. OrderId = Tr. OrderId and Tr. AccountId= A. AccountId and A.ClientId = C.ClientId and" +
                    "                    C.ClientId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(customerId));
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getString("PriceType").equals("Market")){
                    MarketOrder order=new MarketOrder();
                    order.setBuySellType(rs.getString("OrderType"));
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    order.setBuySellType(rs.getString("OrderType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("MarketOnClose")){
                    MarketOnCloseOrder order=new MarketOnCloseOrder();
                    order.setBuySellType(rs.getString("OrderType"));
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    order.setBuySellType(rs.getString("OrderType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("TrailingStop")) {
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT T.Percentage from TrailingStop T, Orders O where O.OrderId=T.OrderId");
                    Double percentage=0.0;
                    if(rs2.next()){
                        percentage = rs2.getDouble("Percentage");
                    }
                    TrailingStopOrder order = new TrailingStopOrder();
                    order.setPercentage(percentage);
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    orders.add(order);
                }else if(rs.getString("PriceType").equals("HiddenStop")) {
                    Statement st = con.createStatement();
                    ResultSet rs2 = st.executeQuery("SELECT H.StopPrice from HiddenStop H, Orders O where O.OrderId=H.OrderId");
                    Double pricepershare=0.0;
                    if(rs2.next()){
                        pricepershare = rs2.getDouble("StopPrice");
                    }
                    HiddenStopOrder order = new HiddenStopOrder();
                    order.setPricePerShare(pricepershare);
                    order.setId(rs.getInt("OrderId"));
                    order.setDatetime(rs.getTimestamp("DateNTime"));
                    order.setNumShares(rs.getInt("NumShares"));
                    order.setPriceType(rs.getString("PriceType"));
                    orders.add(order);
                }
            }
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException s){
            s.printStackTrace();
        }
        return orders;

//        return getDummyOrders();
    }


    public List<OrderPriceEntry> getOrderPriceHistory(String orderId) {

        /*
         * The students code to fetch data from the database will be written here
         * Query to view price history of hidden stop order or trailing stop order
         * Use setPrice to show hidden-stop price and trailing-stop price
         */
        List<OrderPriceEntry> orderPriceHistory = new ArrayList<OrderPriceEntry>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/jiarchen", "jiarchen", "Cjr19971117");
            String query = "Select O.PriceType FROM Orders O, ConditionalOrderHistory C " +
                    "WHERE O.OrderId=C.OrderId AND C.OrderId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(orderId));
            ResultSet rs = ps.executeQuery();
            String PriceType = "";
            if (rs.next()) {
                PriceType = rs.getString("PriceType");
                System.out.println(PriceType);
                if (PriceType.equals("TrailingStop")) {
                    System.out.println("test2");
                    String query_2 = "Select C.OrderId, C.DateNTime, C.StockSymbol,C. PricePerShare, " +
                            "( C.PricePerShare - C.PricePerShare * (T. Percentage*0.01)) AS Price\n" +
                            "From ConditionalOrderHistory C,TrailingStop T\n" +
                            "Where C.OrderId = T. OrderId AND T.OrderId = ?";
                    PreparedStatement ps_2 = con.prepareStatement(query_2);
                    ps_2.setInt(1, Integer.parseInt(orderId));
                    ResultSet rs_2 = ps_2.executeQuery();
                    while (rs_2.next()) {
                        System.out.println("test");
                        OrderPriceEntry entry = new OrderPriceEntry();
                        entry.setOrderId(Integer.toString(rs_2.getInt("OrderId")));
                        entry.setDate(rs_2.getTimestamp("DateNTime"));
                        entry.setStockSymbol(rs_2.getString("StockSymbol"));
                        entry.setPricePerShare(rs_2.getDouble("PricePerShare"));
                        entry.setPrice(rs_2.getDouble("Price"));
                        orderPriceHistory.add(entry);
                    }
                } else if (PriceType.equals("HiddenStop")) {
                    String query_2 = "Select C.OrderId, C.StockSymbol, C.PricePerShare, H.StopPrice, C.DateNTime\n" +
                            "From ConditionalOrderHistory C, HiddenStop H\n" +
                            "Where C. OrderId = H. OrderId";
                    PreparedStatement ps_2 = con.prepareStatement(query_2);
                    ResultSet rs_2 = ps_2.executeQuery();
                    while (rs_2.next()) {
                        OrderPriceEntry entry = new OrderPriceEntry();
                        entry.setOrderId(Integer.toString(rs_2.getInt("OrderId")));
                        entry.setDate(rs_2.getTimestamp("DateNTime"));
                        entry.setStockSymbol(rs_2.getString("StockSymbol"));
                        entry.setPricePerShare(rs_2.getDouble("PricePerShare"));
                        entry.setPrice(rs_2.getDouble("Price"));
                        orderPriceHistory.add(entry);
                    }
                }
            }
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException s){
            s.printStackTrace();
        }
        return orderPriceHistory;
//        for (int i = 0; i < 10; i++) {
//            OrderPriceEntry entry = new OrderPriceEntry();
//            entry.setOrderId(orderId);
//            entry.setDate(new Date());
//            entry.setStockSymbol("aapl");
//            entry.setPricePerShare(150.0);
//            entry.setPrice(100.0);
//            orderPriceHistory.add(entry);
//        }
//        return orderPriceHistory;
    }
}
