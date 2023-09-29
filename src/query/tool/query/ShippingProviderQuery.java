package query.tool.query;

import query.tool.model.ShippingProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShippingProviderQuery {

    private final String SELECT = "SELECT * FROM tblShippingProvider";
    private final String INSERT = "EXEC proc_InsertProduct @ShippingName = ?, @PriceID = ?, @Address = ?, @ContactNo = ?";
    private final String UPDATE = "EXEC proc_UpdateProduct @ShippingID = ?, @ShippingName = ?, @PriceID = ?, @Address = ?, @ContactNo = ?";
    private final String DELETE = "EXEC proc_DeleteProduct @ShippingID = ?";
    private Connection conn;

    public ShippingProviderQuery(Connection conn) {
        this.conn = conn;
    }

    public List<ShippingProvider> selectShippingProvider() throws SQLException {
        List<ShippingProvider> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(SELECT);
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                res.add(new ShippingProvider(rs.getInt("ShippingID"), rs.getString("ShippingName"),
                        rs.getInt("PriceID"), rs.getString("Address"),
                        rs.getString("ContactNo"), rs.getDate("DateAdded")));
            }

        } catch (SQLException ex) {
            throw new SQLException("Failed to get shipping provider list");
        }
        return res;
    }

    public ShippingProvider selectShippingProviderByName(String name) throws SQLException {
        ShippingProvider res = null;
        try {

            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT * FROM tblShippingProvider WHERE ShippingName = ?");
            preSt.setString(1, name);
            ResultSet rs = preSt.executeQuery();

            if (rs.next()) {
                res = new ShippingProvider(rs.getInt("ShippingID"), rs.getString("ShippingName"),
                        rs.getInt("PriceID"), rs.getString("Address"),
                        rs.getString("ContactNo"), rs.getDate("DateAdded"));

            }

        } catch (SQLException ex) {
            throw new SQLException("Failed to get shipping provider: " + name);
        }
        return res;
    }

    public int insertShippingProvider(ShippingProvider shippingProvider) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, shippingProvider.getShippingName());// ShippingName
            preSt.setInt(2, shippingProvider.getPriceID());// PriceID
            preSt.setString(3, shippingProvider.getAddress());// Address
            preSt.setString(4, shippingProvider.getContactNo());// ContactNo

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the shipping provider " + shippingProvider.toString());
        }
        return -1;
    }

    public int updateShippingProvider(ShippingProvider shippingProvider) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, shippingProvider.getShippingID()); // ShippingID
            preSt.setString(2, shippingProvider.getShippingName());// ShippingName
            preSt.setInt(3, shippingProvider.getPriceID());// PriceID
            preSt.setString(4, shippingProvider.getAddress());// Address
            preSt.setString(5, shippingProvider.getContactNo());// ContactNo
            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the shipping provider: " + shippingProvider.toString());
        }
        return -1;
    }

    public int deleteShippingProvider(ShippingProvider shippingProvider) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, shippingProvider.getShippingID());// ShippingID
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException(
                    "Failed to delete the shipping provider with id: " + shippingProvider.getShippingID());
        }
        return -1;
    }
}
