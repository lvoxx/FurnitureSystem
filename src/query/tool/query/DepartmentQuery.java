package query.tool.query;

import query.tool.model.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartmentQuery {
    private final String SELECT = "SELECT * FROM tblDepartment WHERE DpmID = ?";
    private final String INSERT = "EXEC proc_InsertCustomer @Department = ?";
    private final String UPDATE = "EXEC proc_UpdateCustomer @DpmID = ?, @Department = ?";
    private final String DELETE = "EXEC proc_DeleteCustomer @DpmID = ?";
    private Connection conn;

    public DepartmentQuery(Connection conn) {
        this.conn = conn;
    }

    public List<Department> selectDepartment(List<Integer> departmentIDs) throws SQLException {
        try {
            List<Department> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < departmentIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, departmentIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new Department(rs.getInt("DpmID"), rs.getString("Department")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Department ID: " + departmentIDs.toString());
        }
    }

    public int insertDepartment(Department department) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, department.getDepartmentName());// Department

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the department " + department.toString());
        }
        return -1;
    }

    public int updateDepartment(Department department) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE);
            preSt.setInt(1, department.getDpmID());// DepartmentID
            preSt.setString(2, department.getDepartmentName());// Department

            if (preSt.executeUpdate() == 1)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the department: " + department.toString());
        }
        return -1;
    }

    public int deleteDepartment(Department department) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, department.getDpmID());
            if (preSt.execute())
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the department with id: " + department.getDpmID());
        }
        return -1;
    }
}
