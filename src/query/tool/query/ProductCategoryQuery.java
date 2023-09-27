package query.tool.query;

import query.tool.model.ProductCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryQuery {

    private final String SELECT = "SELECT * FROM tblProductCategory WHERE ProductCtgID = ?";
    private final String INSERT = "EXEC proc_InsertProductCategory @CategoryName = ?";
    private final String UPDATE = "EXEC proc_UpdateProductCategory @ProductCtgID = ?, @CategoryName = ?";
    private final String DELETE = "EXEC proc_DeleteProductCategory @ProductCtgID = ?";
    private Connection conn;

    public ProductCategoryQuery(Connection conn) {
        this.conn = conn;
    }

    public List<ProductCategory> selectProductCategory(List<Integer> productCategoryIDs) throws SQLException {
        try {
            List<ProductCategory> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < productCategoryIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, productCategoryIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new ProductCategory(rs.getInt("ProductCtgID"), rs.getString("CategoryName")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get product category ID: " + productCategoryIDs.toString());
        }
    }

    public int insertProductCategory(String productCategoryName) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, productCategoryName);// Category Name

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the product category " + productCategoryName);
        }
        return -1;
    }

    public int updateProductCategory(ProductCategory productCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, productCategory.getProductCtgID());// ProductCtgID
            preSt.setString(2, productCategory.getCategoryName());// CategoryName

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the product category: " + productCategory.toString());
        }
        return -1;
    }

    public int deleteProductCategory(ProductCategory productCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, productCategory.getProductCtgID());// ProductCtgID
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException(
                    "Failed to delete the product category with id: " + productCategory.getProductCtgID());
        }
        return -1;
    }

    public List<ProductCategory> selectProductCategoryList() throws SQLException {
        List<ProductCategory> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT * FROM tblProductCategory");
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                res.add(new ProductCategory(rs.getInt("ProductCtgID"), rs.getString("CategoryName")));
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get product category list");
        }
        return res;
    }
    
    public List<ProductCategory> selectProductCategoryByName(String name) throws SQLException {
        List<ProductCategory> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT * FROM tblProductCategory WHERE [CategoryName] = ?");
            preSt.setString(1, name);
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                res.add(new ProductCategory(rs.getInt("ProductCtgID"), rs.getString("CategoryName")));
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get product category list");
        }
        return res;
    }
}
