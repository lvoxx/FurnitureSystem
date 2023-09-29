package query.tool.query;

import dashboard.components.model.MProduct;
import query.tool.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import query.tool.model.ProductCategory;

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

    public List<String> selectAllProductName() throws SQLException {
        List<String> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT * FROM tblProduct");
            ResultSet rs = preSt.executeQuery();
            rs = preSt.executeQuery();
            while (rs.next()) {
                res.add(rs.getString("ProductName"));
            }

        } catch (SQLException ex) {
            throw new SQLException("Failed to get product name list");
        }
        
        return res;
    }

    public List<MProduct> selectMProductList() throws SQLException {
        List<MProduct> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT P.[ProductID], P.[ProductName], P.[InStockQuantity], P.[Price], P.[ProductCtgID], PC.[CategoryName], P.[DateAdded] FROM tblProduct AS P INNER JOIN tblProductCategory AS PC ON P.ProductCtgID = PC.ProductCtgID");
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getInt("InStockQuantity"), rs.getInt("Price"), rs.getInt("ProductCtgID"), rs.getDate("DateAdded"));
                ProductCategory productCategory = new ProductCategory(rs.getInt("ProductCtgID"), rs.getString("CategoryName"));
                res.add(new MProduct(product, productCategory));
            }

        } catch (SQLException ex) {
            throw new SQLException("Failed to get Product list");
        }
        return res;
    }

    public List<MProduct> selectMProductListByName(String name) throws SQLException {
        List<MProduct> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT P.[ProductID], P.[ProductName], P.[InStockQuantity], P.[Price], P.[ProductCtgID], PC.[CategoryName], P.[DateAdded] FROM tblProduct AS P INNER JOIN tblProductCategory AS PC ON P.ProductCtgID = PC.ProductCtgID WHERE P.[ProductName] LIKE ?");
            preSt.setString(1, "%" + name + "%");
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                Product product = new Product(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getInt("InStockQuantity"), rs.getInt("Price"), rs.getInt("ProductCtgID"), rs.getDate("DateAdded"));
                ProductCategory productCategory = new ProductCategory(rs.getInt("ProductCtgID"), rs.getString("CategoryName"));
                res.add(new MProduct(product, productCategory));
            }

        } catch (SQLException ex) {
            throw new SQLException("Failed to get Product list");
        }
        return res;
    }

    public int insertProduct(String productName, int inStockQuantity, int price, int productCtgID) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, productName);// ProductName
            preSt.setInt(2, inStockQuantity);// InStockQuantity
            preSt.setInt(3, price);// Price
            preSt.setInt(4, productCtgID);// ProductCtgID

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the product");
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

            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the product: " + product.toString());
        }
        return -1;
    }

    public int deleteProduct(int productID) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, productID);// ProductID
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the product with id: " + productID);
        }
        return -1;
    }
}
