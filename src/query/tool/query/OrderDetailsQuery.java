package query.tool.query;

import query.tool.model.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsQuery {

    private final String SELECT = "SELECT * FROM tblOrderDetails WHERE OrderID = ?";
    private final String INSERT = "EXEC proc_InsertOrderDetails @OrderID = ?, @ProductID = ?, @Quantity = ?";
    private final String UPDATE = "EXEC proc_UpdateOrderDetails @OrderID = ?, @ProductID = ?, @Quantity = ?, @FixedPrice = ?";
    private final String UPSERT = "EXEC proc_UpsertOrderDetails @OrderID = ?, @ProductID = ?, @Quantity = ?, @FixedPrice = ?";
    private final String DELETE = "EXEC proc_DeleteOrderDetails @OrderID = ?, @ProductID = ?";
    private Connection conn;

    public OrderDetailsQuery(Connection conn) {
        this.conn = conn;
    }

    public List<OrderDetails> selectOrderDetails(int orderDetailsID) throws SQLException {
        List<OrderDetails> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(SELECT);
            preSt.setInt(1, orderDetailsID);
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                res.add(new OrderDetails(rs.getInt("OrderID"), rs.getInt("ProductID"), rs.getInt("Quantity"),
                        rs.getInt("FixedPrice")));
            }

            
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Order Details ID: " + orderDetailsID);
        }
        return res;
    }

    public int insertOrderDetails(int orderID, int productID, int quantity) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, orderID);// OrderID
            preSt.setInt(2, productID);// ProductID
            preSt.setInt(3, quantity);// Quantity

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the order details");
        }
        return -1;
    }

    public int updateOrderDetails(OrderDetails orderDetails) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, orderDetails.getOrderID());// OrderID
            preSt.setInt(2, orderDetails.getProductID());// ProductID
            preSt.setInt(3, orderDetails.getQuantity());// Quantity
            preSt.setInt(4, orderDetails.getFixedPrice());// FixedPrice

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the order details: " + orderDetails.toString());
        }
        return -1;
    }
    public int upsertOrderDetails(OrderDetails orderDetails) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPSERT);
            preSt.setInt(1, orderDetails.getOrderID());// OrderID
            preSt.setInt(2, orderDetails.getProductID());// ProductID
            preSt.setInt(3, orderDetails.getQuantity());// Quantity
            preSt.setInt(4, orderDetails.getFixedPrice());// FixedPrice

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the order details: " + orderDetails.toString());
        }
        return -1;
    }

    public int deleteOrderDetails(OrderDetails orderDetails) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, orderDetails.getOrderID());// OrderID
            preSt.setInt(2, orderDetails.getProductID());// ProductID
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the order details with id: " + orderDetails.getOrderID());
        }
        return -1;
    }
    
    public int deleteAllOrderDetails(int orderID) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement("DELETE FROM tblOrderDetails WHERE [OrderID] = ?");
            preSt.setInt(1, orderID);// OrderID
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the order details with id: " + orderID);
        }
        return -1;
    }
}
