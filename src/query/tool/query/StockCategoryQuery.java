package query.tool.query;

import query.tool.model.StockCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StockCategoryQuery {
    private final String SELECT = "SELECT * FROM tblStockCategory WHERE StockCtgID = ?";
    private final String INSERT = "EXEC proc_InsertStockCategory @StockCtgName = ?";
    private final String UPDATE = "EXEC proc_UpdateStockCategory @StockCtgID = ?, @StockCtgName = ?";
    private final String DELETE = "EXEC proc_DeleteStockCategory @StockCtgID = ?";
    private Connection conn;

    public StockCategoryQuery(Connection conn) {
        this.conn = conn;
    }

    public List<StockCategory> selectStockCategory(List<Integer> stockCtgIDs) throws SQLException {
        try {
            List<StockCategory> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < stockCtgIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, stockCtgIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new StockCategory(rs.getInt("StockCtgID"), rs.getString("StockCtgName")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get stock category ID: " + stockCtgIDs.toString());
        }
    }

    public int insertStockCategory(StockCategory stockCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, stockCategory.getStockCtgName());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the stock category " + stockCategory.toString());
        }
        return -1;
    }

    public int updateStockCategory(StockCategory stockCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, stockCategory.getStockCtgID());
            preSt.setString(2, stockCategory.getStockCtgName());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the stock category " + stockCategory.toString());
        }
        return -1;
    }

    public int deleteStockCategory(StockCategory stockCategory) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, stockCategory.getStockCtgID());
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the stock with id: " + stockCategory.getStockCtgID());
        }
        return -1;
    }
}
