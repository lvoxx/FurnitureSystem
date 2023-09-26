package query.tool.query;

import query.tool.model.Discount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DiscountQuery {
    private final String SELECT = "SELECT * FROM tblDiscount WHERE DiscountID = ?";
    private final String INSERT = "EXEC proc_InsertDiscount @Discount = ?, @CustomerType = ?";
    private final String UPDATE = "EXEC proc_UpdateDiscount @DiscountID = ?, @Discount = ?, @CustomerType = ?";
    private final String DELETE = "EXEC proc_DeleteDiscount @DiscountID = ?";
    private Connection conn;

    public DiscountQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Discount> selectDiscount(List<Integer> discountIDs) throws SQLException {
        try {
            List<Discount> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < discountIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, discountIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new Discount(rs.getInt("DiscountID"), rs.getInt("Discount"), rs.getString("CustomerType")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Discount ID: " + discountIDs.toString());
        }
    }
    
    public List<Discount> selectDiscountList() throws SQLException {
        List<Discount> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn.prepareStatement("SELECT * FROM tblDiscount");
            ResultSet rs = preSt.executeQuery();
            
            while(rs.next()){
                int discountID = rs.getInt("DiscountID");
                int discount = rs.getInt("Discount");
                String customerType = rs.getString("CustomerType");
                
                res.add(new Discount(discountID, discount, customerType));
            }
            
            
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Discount List");
        }
        return res;
    }

    public int insertDiscount(Discount discount) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, discount.getDiscount());// Discount
            preSt.setString(2, discount.getCustomerType());// CustomerType

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the discount " + discount.toString());
        }
        return -1;
    }

    public int updateDiscount(Discount discount) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, discount.getDiscountID());// DiscountID
            preSt.setInt(2, discount.getDiscount());// Discount
            preSt.setString(3, discount.getCustomerType());// CustomerType

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the discount: " + discount.toString());
        }
        return -1;
    }

    public int deleteDiscount(Discount discount) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, discount.getDiscountID());// DiscountID
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the discount with id: " + discount.getDiscountID());
        }
        return -1;
    }
}
