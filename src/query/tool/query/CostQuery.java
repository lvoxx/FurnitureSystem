package query.tool.query;

import dashboard.components.model.MCost;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import query.tool.model.Cost;
import query.tool.model.CostCategory;

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
            if (preSt.executeUpdate() == 1) {
                return 1;
            }
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
            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the cost " + cost.toString());
        }
        return -1;
    }

    public int deleteCost(Cost cost) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, cost.getCostID());
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the cost with id: " + cost.getCostID());
        }
        return -1;
    }

    public List<MCost> selectMCostList() throws SQLException {
        List<MCost> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT [CostID], [UserIDCreated], U.[Name], C.[CostCtgID], CC.[CostCtgName], [Expense], [DateAdded] FROM tblCost AS C INNER JOIN tblCostCategory AS CC ON C.CostCtgID = CC.CostCtgID INNER JOIN tblUser AS U ON C.UserIDCreated = U.UserID");
            ResultSet rs = preSt.executeQuery();
            while(rs.next()){
                int useID = rs.getInt("UserIDCreated");
                String userName = rs.getString("Name");
                CostCategory costCategory = new CostCategory(rs.getInt("CostCtgID"), rs.getString("CostCtgName"));
                Cost cost = new Cost(rs.getInt("CostID"), rs.getInt("UserIDCreated"), rs.getInt("CostCtgID"), rs.getInt("Expense"), rs.getDate("DateAdded"));
                
                res.add(new MCost(cost, useID, userName, costCategory));
            }      
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Cost List");
        }
        return res;
    }
    
    public List<MCost> selectMCostListSearch(String inputSearch) throws SQLException {
        List<MCost> res = new ArrayList<>();
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT [CostID], [UserIDCreated], U.[Name], C.[CostCtgID], CC.[CostCtgName], [Expense], [DateAdded] FROM tblCost AS C INNER JOIN tblCostCategory AS CC ON C.CostCtgID = CC.CostCtgID INNER JOIN tblUser AS U ON C.UserIDCreated = U.UserID WHERE  U.[Name] LIKE ?  OR CC.[CostCtgName] LIKE ? OR C.[DateCreated] LIKE ?");
            preSt.setString(1, "%" + inputSearch + "%");
            preSt.setString(2, "%" + inputSearch + "%");
            preSt.setString(3, "%" + inputSearch + "%");
            ResultSet rs = preSt.executeQuery();
            while(rs.next()){
                int useID = rs.getInt("UserIDCreated");
                String userName = rs.getString("Name");
                CostCategory costCategory = new CostCategory(rs.getInt("CostCtgID"), rs.getString("CostCtgName"));
                Cost cost = new Cost(rs.getInt("CostID"), rs.getInt("UserIDCreated"), rs.getInt("CostCtgID"), rs.getInt("Expense"), rs.getDate("DateAdded"));
                
                res.add(new MCost(cost, useID, userName, costCategory));
            }      
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Cost Search");
        }
        return res;
    }
}
