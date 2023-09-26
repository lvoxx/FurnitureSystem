package query.tool.query;

import query.tool.model.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StockQuery {
    private final String SELECT = "SELECT * FROM tblStock WHERE StockID = ?";
    private final String INSERT = "EXEC proc_InsertStock @StockName = ?, @Quantity = ?, @StockCtgID = ?, @SupplierID = ?, @Status = ?";
    private final String UPDATE = "EXEC proc_UpdateStock @StockID = ?, @StockName = ?, @Quantity = ?, @StockCtgID = ?, @SupplierID = ?, @Status = ?";
    private final String DELETE = "EXEC proc_DeleteStock @StockID = ?";
    private Connection conn;

    public StockQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Stock> selectStock(List<Integer> stockIDs) throws SQLException {
        try {
            List<Stock> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < stockIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, stockIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new Stock(rs.getInt("StockID"), rs.getString("StockName"), rs.getInt("Quantity"),
                            rs.getInt("StockCtgID"), rs.getInt("SupplierID"),
                            rs.getString("Status"), rs.getDate("DateAdded")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get stock ID: " + stockIDs.toString());
        }
    }

    public int insertStock(Stock stock) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, stock.getStockName());
            preSt.setInt(2, stock.getQuantity());
            preSt.setInt(3, stock.getStockCtgID());
            preSt.setInt(4, stock.getSupplierID());
            preSt.setString(5, stock.getStatus());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the stock " + stock.toString());
        }
        return -1;
    }

    public int updateStock(Stock stock) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, stock.getStockID());
            preSt.setString(2, stock.getStockName());
            preSt.setInt(3, stock.getQuantity());
            preSt.setInt(4, stock.getStockCtgID());
            preSt.setInt(5, stock.getSupplierID());
            preSt.setString(6, stock.getStatus());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the stock " + stock.toString());
        }
        return -1;
    }

    public int deleteStock(Stock stock) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, stock.getStockID());
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the stock with id: " + stock.getStockID());
        }
        return -1;
    }
}
