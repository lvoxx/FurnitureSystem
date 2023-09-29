package query.tool.query;

import dashboard.components.model.MOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import query.tool.model.Order;

public class OrderQuery {

    private final String SELECT = "SELECT * FROM tblOrder  WHERE [OrderID] = ?";

    private final String INSERT = "EXEC proc_InsertOrder @CustomerID = ?, @ShippingID = ?, @UserIDCreated = ?";
    private final String UPDATE = "EXEC proc_UpdateOrder @OrderID = ?, @ShippingID = ?, @Status = ?";
    private final String DELETE = "EXEC proc_DeleteOrder @OrderID = ?";
    private Connection conn;

    public OrderQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Order> selectOrder(int orderID) throws SQLException {
        List<Order> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn.prepareStatement(SELECT);
            preSt.setInt(1, orderID);
            ResultSet rs = preSt.executeQuery();
            // Get Order
            if (rs.next()) {
                res.add(new Order(rs.getInt("OrderID"), rs.getInt("CustomerID"), rs.getInt("ShippingID"),
                        rs.getString("Status"),
                        rs.getDate("DateOrder"), rs.getDate("DatePaid"), rs.getInt("UserIDCreated")));
            }

        } catch (SQLException ex) {
            throw new SQLException("Failed to get Order ID: " + orderID);
        }
        return res;
    }

    public List<MOrder> selectOrderUnPaid() throws SQLException {
        List<MOrder> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn.prepareStatement("SELECT O.[OrderID], O.[CustomerID], C.[Name] AS [CustomerName], O.ShippingID, S.[ShippingName], [Status], [DateOrder], [DatePaid], O.UserIDCreated, U.[Name] AS [UserName] FROM tblOrder AS O INNER JOIN tblCustomer AS C ON O.CustomerID = C.CustomerID INNER JOIN tblShippingProvider AS S ON O.ShippingID = S.ShippingID INNER JOIN tblUser AS U ON O.UserIDCreated = U.UserID WHERE [Status] = 'UnPaid'");
            ResultSet rs = preSt.executeQuery();

            // Get Order Unpaid
            rs = preSt.executeQuery();
            while (rs.next()) {
                res.add(new MOrder(
                        new Order(rs.getInt("OrderID"),
                                rs.getInt("CustomerID"),
                                rs.getInt("ShippingID"),
                                rs.getString("Status"),
                                rs.getDate("DateOrder"),
                                rs.getDate("DatePaid"),
                                rs.getInt("UserIDCreated")),
                        rs.getString("CustomerName"),
                        rs.getString("ShippingName"),
                        rs.getString("UserName")));
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Unpaid Order");
        }
        return res;
    }
    
    public List<MOrder> selectOrderPaid() throws SQLException {
        List<MOrder> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn.prepareStatement("SELECT O.[OrderID], O.[CustomerID], C.[Name] AS [CustomerName], O.ShippingID, S.[ShippingName], [Status], [DateOrder], [DatePaid], O.UserIDCreated, U.[Name] AS [UserName] FROM tblOrder AS O INNER JOIN tblCustomer AS C ON O.CustomerID = C.CustomerID INNER JOIN tblShippingProvider AS S ON O.ShippingID = S.ShippingID INNER JOIN tblUser AS U ON O.UserIDCreated = U.UserID WHERE [Status] = 'Paid'");
            ResultSet rs = preSt.executeQuery();

            // Get Order Unpaid
            rs = preSt.executeQuery();
            while (rs.next()) {
                res.add(new MOrder(
                        new Order(rs.getInt("OrderID"),
                                rs.getInt("CustomerID"),
                                rs.getInt("ShippingID"),
                                rs.getString("Status"),
                                rs.getDate("DateOrder"),
                                rs.getDate("DatePaid"),
                                rs.getInt("UserIDCreated")),
                        rs.getString("CustomerName"),
                        rs.getString("ShippingName"),
                        rs.getString("UserName")));
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Unpaid Order");
        }
        return res;
    }
    
    public List<MOrder> selectOrderUnPaidSearch(String name) throws SQLException {
        List<MOrder> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn.prepareStatement("SELECT O.[OrderID], O.[CustomerID], C.[Name] AS [CustomerName], O.ShippingID, S.[ShippingName], [Status], [DateOrder], [DatePaid], O.UserIDCreated, U.[Name] AS [UserName] FROM tblOrder AS O INNER JOIN tblCustomer AS C ON O.CustomerID = C.CustomerID INNER JOIN tblShippingProvider AS S ON O.ShippingID = S.ShippingID INNER JOIN tblUser AS U ON O.UserIDCreated = U.UserID WHERE [Status] = 'UnPaid' AND (C.[Name] LIKE ? OR S.ShippingName LIKE ? OR U.Username LIKE ?)");
            preSt.setString(1, "%" + name + "%");
            preSt.setString(2, "%" + name + "%");
            preSt.setString(3, "%" + name + "%");
            ResultSet rs = preSt.executeQuery();

            // Get Order Unpaid
            rs = preSt.executeQuery();
            while (rs.next()) {
                res.add(new MOrder(
                        new Order(rs.getInt("OrderID"),
                                rs.getInt("CustomerID"),
                                rs.getInt("ShippingID"),
                                rs.getString("Status"),
                                rs.getDate("DateOrder"),
                                rs.getDate("DatePaid"),
                                rs.getInt("UserIDCreated")),
                        rs.getString("CustomerName"),
                        rs.getString("ShippingName"),
                        rs.getString("UserName")));
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Unpaid Order");
        }
        return res;
    }
    
    public List<MOrder> selectOrderPaidSearch(String name) throws SQLException {
        List<MOrder> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn.prepareStatement("SELECT O.[OrderID], O.[CustomerID], C.[Name] AS [CustomerName], O.ShippingID, S.[ShippingName], [Status], [DateOrder], [DatePaid], O.UserIDCreated, U.[Name] AS [UserName] FROM tblOrder AS O INNER JOIN tblCustomer AS C ON O.CustomerID = C.CustomerID INNER JOIN tblShippingProvider AS S ON O.ShippingID = S.ShippingID INNER JOIN tblUser AS U ON O.UserIDCreated = U.UserID WHERE [Status] = 'Paid' AND (C.[Name] LIKE ? OR S.ShippingName LIKE ? OR U.Username LIKE ?)");
            preSt.setString(1, "%" + name + "%");
            preSt.setString(2, "%" + name + "%");
            preSt.setString(3, "%" + name + "%");
            ResultSet rs = preSt.executeQuery();

            // Get Order Unpaid
            rs = preSt.executeQuery();
            while (rs.next()) {
                res.add(new MOrder(
                        new Order(rs.getInt("OrderID"),
                                rs.getInt("CustomerID"),
                                rs.getInt("ShippingID"),
                                rs.getString("Status"),
                                rs.getDate("DateOrder"),
                                rs.getDate("DatePaid"),
                                rs.getInt("UserIDCreated")),
                        rs.getString("CustomerName"),
                        rs.getString("ShippingName"),
                        rs.getString("UserName")));
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Unpaid Order");
        }
        return res;
    }

    public int insertOrder(int customerID, int shippingID, int userIDCreated) throws SQLException {
        int orderID = -1;
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, customerID);// CustomerID
            preSt.setInt(2, shippingID);// ShippingID
            preSt.setInt(3, userIDCreated);// UserIDCreated
            preSt.executeUpdate();
            // Get Instance ID key
            try (ResultSet generatedKeys = preSt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating Order failed, no ID obtained.");
                }
            }

        } catch (SQLException ex) {
            throw new SQLException("Failed to insert new order");
        }
        return orderID;
    }

    public int updateOrder(Order order) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, order.getOrderID());// OrderID
            preSt.setInt(2, order.getShippingID());// ShippingID
            preSt.setString(3, order.getStatus());// Status

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the order: " + order.toString());
        }
        return -1;
    }

    public int deleteOrderDetails(Order order) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, order.getOrderID());// OrderID
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the order with id: " + order.getOrderID());
        }
        return -1;
    }
}
