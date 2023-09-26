package query.tool.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import query.tool.model.CostCategory;

public class CostCategoryQuery {
    private final String SELECT = "SELECT * FROM tblCostCategory WHERE CostCtgID = ?";
    private final String SELECT_ALL = "SELECT * FROM tblCostCategory";
    private final String INSERT = "EXEC proc_InsertCostCategory @CostCtgName = ?";
    private final String UPDATE = "EXEC proc_UpdateCostCategory @CostCtgID = ?, @CostCtgName = ?";
    private final String DELETE = "EXEC proc_DeleteCostCategory @CostCtgID = ?";
    private Connection conn;

    public CostCategoryQuery(Connection conn) {
        this.conn = conn;
    }

    public List<CostCategory> selectCostCategory(List<Integer> costCtgIDs) throws SQLException {
        List<CostCategory> res = new ArrayList<>();
        try {
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < costCtgIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, costCtgIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new CostCategory(rs.getInt("CostCtgID"), rs.getString("CostCtgName")));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Cost Category ID: " + costCtgIDs.toString());
        }
        return res;
    }
    
    public List<CostCategory> selectCostCategoryList() throws SQLException {
        List<CostCategory> res = new ArrayList<>();
        try {
            PreparedStatement preSt = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = preSt.executeQuery();
            while(rs.next()){
                res.add(new CostCategory(rs.getInt("CostCtgID"), rs.getString("CostCtgName")));
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Cost Category List");
        }
        return res;
    }

    public int insertCostCategory(CostCategory costCtgIDs) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, costCtgIDs.getCostCtgName());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the cost category " + costCtgIDs.toString());
        }
        return -1;
    }

    public int updateCostCategory(CostCategory costCtgIDs) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, costCtgIDs.getCostCtgID());
            preSt.setString(2, costCtgIDs.getCostCtgName());
            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the cost category " + costCtgIDs.toString());
        }
        return -1;
    }

    public int deleteCostCategory(CostCategory costCtgIDs) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, costCtgIDs.getCostCtgID());
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the cost with id: " + costCtgIDs.getCostCtgID());
        }
        return -1;
    }
}
