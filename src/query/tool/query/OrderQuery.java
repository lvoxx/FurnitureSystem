package query.tool.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import query.tool.model.CostInvoice;
import query.tool.model.CostInvoiceDetails;
import query.tool.model.Order;
import query.tool.model.OrderDetails;

public class OrderQuery {
    private final String SELECT = "SELECT * FROM tblOrder  WHERE [OrderID] = ?";
    private final String SUB_SELECT = "SELECT * FROM tblOrderDetails  WHERE [OrderID] = ?";

    private final String INSERT = "EXEC proc_InsertOrder @CustomerID = ?, @ShippingID = ?, @UserIDCreated = ?";
    private final String UPDATE = "EXEC proc_UpdateOrder @OrderID = ?, @ShippingID = ?, @Status = ?";
    private final String DELETE = "EXEC proc_DeleteOrder @OrderID = ?";
    private Connection conn;

    public OrderQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Order> selectOrder(List<Integer> orderIDs) throws SQLException {
        try {
            List<Order> res = new ArrayList<>();
            List<OrderDetails> subRes;

            PreparedStatement preSt, subPreSt;
            ResultSet rs, subRs;

            for (int i = 0; i < orderIDs.size(); ++i) {
                subRes = new ArrayList<>();
                subPreSt = this.conn.prepareStatement(SUB_SELECT);
                subPreSt.setInt(1, orderIDs.get(i));
                subRs = subPreSt.executeQuery();
                // Get List of Cost Invoice Details by ID
                while (subRs.next()) {
                    subRes.add(
                            new OrderDetails(subRs.getInt("OrderID"), subRs.getInt("ProductID"),
                                    subRs.getInt("Quantity"),
                                    subRs.getInt("FixedPrice")));
                }
                // Get Cost Invoice
                preSt = this.conn.prepareStatement(SELECT);
                preSt.setInt(1, orderIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new Order(rs.getInt("OrderID"), rs.getInt("CustomerID"), rs.getInt("ShippingID"),
                            rs.getString("Status"),
                            rs.getDate("DateOrder"), rs.getDate("DatePaid"), subRes, rs.getInt("UserIDCreated")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Order ID: " + orderIDs.toString());
        }
    }

    public int insertOrder(Order order) throws SQLException {
        try {
            boolean isInsertOrder = false, isInsertOrderDetails = false;
            int orderID = -1;
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, order.getCustomerID());// CustomerID
            preSt.setInt(2, order.getShippingID());// ShippingID
            preSt.setInt(3, order.getUserIDCreated());// UserIDCreated
            if (preSt.executeUpdate() == 1)
                isInsertOrder = true;

            // Get Instance ID key
            try (ResultSet generatedKeys = preSt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating Order failed, no ID obtained.");
                }
            }
            for (OrderDetails item : order.getOrderDetails()) {
                OrderDetailsQuery orderDetailsQuery = new OrderDetailsQuery(conn);
                if (orderDetailsQuery.insertOrderDetails(item) == 1)
                    isInsertOrderDetails = true;
                else
                    isInsertOrderDetails = false;
            }
            // If Order and OrderDetails are successfully inserted, return 1
            if (isInsertOrder && isInsertOrderDetails)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the order: " + order.toString());
        }
        return -1;
    }

    public int updateOrder(Order order) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, order.getOrderID());// OrderID
            preSt.setInt(2, order.getShippingID());// ShippingID
            preSt.setString(3, order.getStatus());// Status

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the order: " + order.toString());
        }
        return -1;
    }

    public int deleteOrderDetails(Order order) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, order.getOrderID());// OrderID
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the order with id: " + order.getOrderID());
        }
        return -1;
    }
}
