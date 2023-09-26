package query.tool.query;

import query.tool.model.ShippingProviderCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ShippingProviderCategoryQuery {
    private final String SELECT = "SELECT * FROM tblShippingProviderCategory WHERE PriceID = ?";
    private final String INSERT = "EXEC proc_InsertShippingProviderCategory @Price = ?";
    private final String UPDATE = "EXEC proc_UpdateShippingProviderCategory @PriceID = ?, @Price = ?";
    private final String DELETE = "EXEC proc_DeleteShippingProviderCategory @PriceID = ?";
    private Connection conn;

    public ShippingProviderCategoryQuery(Connection conn) {
        this.conn = conn;
    }

    public List<ShippingProviderCategory> selectShippingProviderCategory(List<Integer> shippingPrvCtgIDs)
            throws SQLException {
        try {
            List<ShippingProviderCategory> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < shippingPrvCtgIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, shippingPrvCtgIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new ShippingProviderCategory(rs.getInt("PriceID"), rs.getInt("Price")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get shipping provider category ID: " + shippingPrvCtgIDs.toString());
        }
    }

    public int insertShippingProviderCategory(ShippingProviderCategory shippingProviderCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, shippingProviderCategory.getPriceID());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException(
                    "Failed to insert the shipping provider category " + shippingProviderCategory.toString());
        }
        return -1;
    }

    public int updateShippingProviderCategory(ShippingProviderCategory shippingProviderCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, shippingProviderCategory.getPriceID());
            preSt.setInt(2, shippingProviderCategory.getPrice());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException(
                    "Failed to update the shipping provider category " + shippingProviderCategory.toString());
        }
        return -1;
    }

    public int deleteShippingProviderCategory(ShippingProviderCategory shippingProviderCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, shippingProviderCategory.getPriceID());
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException(
                    "Failed to delete the shipping provider category with id: "
                            + shippingProviderCategory.getPriceID());
        }
        return -1;
    }
}
