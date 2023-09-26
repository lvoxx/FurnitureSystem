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
    private final String DELETE = "EXEC proc_DeleteOrderDetails @OrderID = ?, @ProductID = ?";
    private Connection conn;

    public OrderDetailsQuery(Connection conn) {
        this.conn = conn;
    }

    public List<OrderDetails> selectOrderDetails(List<Integer> orderDetailsIDs) throws SQLException {
        try {
            List<OrderDetails> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < orderDetailsIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, orderDetailsIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new OrderDetails(rs.getInt("OrderID"), rs.getInt("ProductID"), rs.getInt("Quantity"),
                            rs.getInt("FixedPrice")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Order Details ID: " + orderDetailsIDs.toString());
        }
    }

    public int insertOrderDetails(OrderDetails orderDetails) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, orderDetails.getOrderID());// OrderID
            preSt.setInt(2, orderDetails.getProductID());// ProductID
            preSt.setInt(3, orderDetails.getQuantity());// Quantity

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the order details " + orderDetails.toString());
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

            if (preSt.executeUpdate() == 1)
                return 1;
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
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the order details with id: " + orderDetails.getOrderID());
        }
        return -1;
    }
}
