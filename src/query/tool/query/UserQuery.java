package query.tool.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import query.tool.model.User;

public class UserQuery {

    private final String SELECT = "SELECT * FROM tblUser WHERE UserID = ?";
    private final String INSERT = "EXEC proc_InsertUser @Name = ?, "
            + "@Address = ?, "
            + "@ContactNo = ?, "
            + "@Role = ?, "
            + "@OrderManagePermission = ?, "
            + "@DpmID = ?, "
            + "@Username = ?, "
            + "@Password = ? "
            + "@RecoveryKey = ?";
    private final String UPDATE_AUTH = "EXEC proc_UpdateUserAuth @UserID = ?, @Username = ?, @Password = ?, @RecoveryKey = ?";
    private final String UPDATE_INFO = "EXEC proc_UpdateCost @UserID = ?,"
            + " @Name = ?,"
            + " @Address = ?,"
            + " @ContactNo = ?,"
            + " @Role = ?,"
            + " @OrderManagePermisson = ?,"
            + " @DpmID = ?";
    private final String DELETE = "EXEC proc_DeleteUser @UserID = ?";
    private Connection conn;

    public UserQuery(Connection conn) {
        this.conn = conn;
    }

    public List<User> selectUser(List<Integer> userIDs) throws SQLException {
        try {
            List<User> res = new ArrayList<>();
            PreparedStatement preSt;
            ResultSet rs;
            for (int i = 0; i < userIDs.size(); ++i) {
                preSt = this.conn
                        .prepareStatement(SELECT);
                preSt.setInt(1, userIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new User(rs.getInt("UserID"),
                            rs.getString("Name"),
                            rs.getString("Address"),
                            rs.getString("ContactNo"),
                            rs.getString("Role"),
                            rs.getDate("DateAdded"),
                            rs.getInt("DpmID"),
                            rs.getBoolean("OrderManagePermission"),
                            rs.getString("Username"),
                            rs.getString("Password"),
                            rs.getString("RecoveryKey")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get User ID: " + userIDs.toString());
        }
    }

    public List<User> selectUserByName(String userName) throws SQLException {
        List<User> res = new ArrayList<>();
        try {
            
            PreparedStatement preSt = this.conn
                    .prepareStatement("SELECT * FROM tblUser WHERE [Name] = ?");
            preSt.setString(1, userName);
            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                res.add(new User(rs.getInt("UserID"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("ContactNo"),
                        rs.getString("Role"),
                        rs.getDate("DateAdded"),
                        rs.getInt("DpmID"),
                        rs.getBoolean("OrderManagePermission"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("RecoveryKey")));
            }
            
        } catch (SQLException ex) {
            throw new SQLException("Failed to get User Name: " + userName);
        }
        return res;
    }

    public int insertUser(User user) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT);
            preSt.setString(1, user.getName());// Name
            preSt.setString(2, user.getAddress());// Address
            preSt.setString(3, user.getContactNo());// ContactNo
            preSt.setString(4, user.getRole());// Role
            preSt.setBoolean(5, user.getOrderManagePermission());// OrderManagePermission
            preSt.setInt(6, user.getDpmID());// DpmID
            preSt.setString(7, user.getUsername());// Username
            preSt.setString(8, user.getPassword());// Password
            preSt.setString(9, user.getRecoveryKey());// RecoveryKey
            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the user " + user.toString());
        }
        return -1;
    }

    public int updateUserAuth(User user) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE_AUTH);
            preSt.setInt(1, user.getUserID());// UserID
            preSt.setString(2, user.getUsername());// Username
            preSt.setString(3, user.getPassword());// Password
            preSt.setString(4, user.getRecoveryKey());// RecoveryKey
            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the user auth: " + user.toString());
        }
        return -1;
    }

    public int updateUserInformation(User user) throws SQLException {
        try {
            PreparedStatement preSt = this.conn
                    .prepareStatement(UPDATE_INFO);
            preSt.setInt(1, user.getUserID());// UserID
            preSt.setString(2, user.getName());// Name
            preSt.setString(3, user.getAddress());// Address
            preSt.setString(4, user.getContactNo());// ContactNo
            preSt.setString(5, user.getRole());// Role
            preSt.setBoolean(6, user.getOrderManagePermission());// OrderManagePermission
            preSt.setInt(7, user.getDpmID());// DpmID
            if (preSt.executeUpdate() == 1) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to update the user info: " + user.toString());
        }
        return -1;
    }

    public int deleteUser(User user) throws SQLException {
        try {
            PreparedStatement preSt = this.conn.prepareStatement(DELETE);
            preSt.setInt(1, user.getUserID());
            if (preSt.execute()) {
                return 1;
            }
        } catch (SQLException ex) {
            throw new SQLException("Failed to delete the user with id: " + user.getUserID());
        }
        return -1;
    }
}
