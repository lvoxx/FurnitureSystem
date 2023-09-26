package query.tool.query;

import query.tool.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductQuery {
    private final String SELECT = "SELECT * FROM tblProduct WHERE ProductID = ?";
    private final String INSERT = "EXEC proc_InsertProduct @ProductName = ?, @InStockQuantity = ?, @Price = ?, @ProductCtgID = ?";
    private final String UPDATE = "EXEC proc_UpdateProduct @ProductID = ?, @ProductName = ?, @InStockQuantity = ?, @Price = ?, @ProductCtgID = ?";
    private final String DELETE = "EXEC proc_DeleteProduct @ProductID = ?";
    private Connection conn;

    public ProductQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Product> selectProduct(List<Integer> productIDs) throws SQLException {
        try {
            List<Product> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < productIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, productIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new Product(rs.getInt("ProductID"), rs.getString("ProductName"),
                            rs.getInt("InStockQuantity"), rs.getInt("Price"), rs.getInt("ProductCtgID"),
                            rs.getDate("DateAdded")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Product ID: " + productIDs.toString());
        }
    }

    public int insertProduct(Product product) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, product.getProductName());// ProductName
            preSt.setInt(2, product.getInStockQuantity());// InStockQuantity
            preSt.setInt(3, product.getPrice());// Price
            preSt.setInt(4, product.getProductCtgID());// ProductCtgID

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the product " + product.toString());
        }
        return -1;
    }

    public int updateProduct(Product product) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, product.getProductID());// ProductID
            preSt.setString(2, product.getProductName());// ProductName
            preSt.setInt(3, product.getInStockQuantity());// InStockQuantity
            preSt.setInt(4, product.getPrice());// Price
            preSt.setInt(5, product.getProductCtgID());// ProductCtgID

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the product: " + product.toString());
        }
        return -1;
    }

    public int deleteProduct(Product product) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, product.getProductID());// ProductID
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the product with id: " + product.getProductID());
        }
        return -1;
    }
}
