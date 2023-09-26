package query.tool.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import query.tool.model.Cost;

public class CostQuery {
    private final String SELECT = "SELECT * FROM tblCost WHERE CostID = ?";
    private final String INSERT = "EXEC proc_InsertCost @UserIDCreated = ?, @CostCtgID = ?, @Expense = ?";
    private final String UPDATE = "EXEC proc_UpdateCost @CostID = ?, @UserIDCreated = ?, @CostCtgID = ?, @Expense = ?";
    private final String DELETE = "EXEC proc_DeleteCost @CostID = ?";
    private Connection conn;

    public CostQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Cost> selectCost(List<Integer> costIDs) throws SQLException {
        try {
            List<Cost> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < costIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, costIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new Cost(rs.getInt("CostID"),
                            rs.getInt("UserIDCreated"),
                            rs.getInt("CostCtgID"),
                            rs.getInt("Expense"),
                            rs.getDate("DateCreated")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Cost ID: " + costIDs.toString());
        }
    }

    public int insertCost(Cost cost) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setInt(1, cost.getUserIDCreated());
            preSt.setInt(2, cost.getCostCtgID());
            preSt.setInt(3, cost.getExpense());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the cost " + cost.toString());
        }
        return -1;
    }

    public int updateCost(Cost cost) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, cost.getCostID());
            preSt.setInt(2, cost.getUserIDCreated());
            preSt.setInt(3, cost.getCostCtgID());
            preSt.setInt(4, cost.getExpense());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the cost " + cost.toString());
        }
        return -1;
    }

    public int deleteCost(Cost cost) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, cost.getCostID());
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the cost with id: " + cost.getCostID());
        }
        return -1;
    }
}
