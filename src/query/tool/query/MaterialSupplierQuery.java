package query.tool.query;

import query.tool.model.MaterialSupplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MaterialSupplierQuery {
    private final String SELECT = "SELECT * FROM tblMaterialSupplier WHERE SupplierID = ?";
    private final String INSERT = "EXEC proc_InsertMaterialSupplier @SupplierName = ?, @Address = ?, @ContactNo = ?";
    private final String UPDATE = "EXEC proc_UpdateMaterialSupplier @SupplierID = ?, @SupplierName = ?, @Address = ?, @ContactNo = ?";
    private final String DELETE = "EXEC proc_DeleteMaterialSupplier @SupplierID = ?";
    private Connection conn;

    public MaterialSupplierQuery(Connection conn) {
        this.conn = conn;
    }

    public List<MaterialSupplier> selectMaterialSupplier(List<Integer> materialSupplierIDs) throws SQLException {
        try {
            List<MaterialSupplier> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < materialSupplierIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, materialSupplierIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new MaterialSupplier(rs.getInt("SupplierID"), rs.getString("SupplierName"),
                            rs.getString("Address"), rs.getString("ContactNo"), rs.getDate("DateAdded")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get material supplier ID: " + materialSupplierIDs.toString());
        }
    }

    public int insertMaterialSupplier(MaterialSupplier materialSupplier) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, materialSupplier.getSupplierName());// Supplier Name
            preSt.setString(2, materialSupplier.getAddress());// Address
            preSt.setString(3, materialSupplier.getContactNo());// ContactNo

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the material supplier " + materialSupplier.toString());
        }
        return -1;
    }

    public int updateMaterialSupplier(MaterialSupplier materialSupplier) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, materialSupplier.getSupplierID());// SupplierID
            preSt.setString(2, materialSupplier.getSupplierName());// SupplierName
            preSt.setString(3, materialSupplier.getAddress());// Address
            preSt.setString(4, materialSupplier.getContactNo());// ContactNo

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the material supplier: " + materialSupplier.toString());
        }
        return -1;
    }

    public int deleteMaterialSupplier(MaterialSupplier materialSupplier) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, materialSupplier.getSupplierID());// SupplierID
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException(
                    "Failed to delete the material supplier with id: " + materialSupplier.getSupplierID());
        }
        return -1;
    }
}
