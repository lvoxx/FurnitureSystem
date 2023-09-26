package query.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralQuery {

    private Connection conn;

    public GeneralQuery(Connection conn) {
        this.conn = conn;
    }

    // GET ORDER PROC
    public boolean getLatestUserIndex() throws SQLException {
        boolean res = false;
        try {
            PreparedStatement preSt = conn.prepareStatement("EXEC proc_GetLatestUser");
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                res = rs.getInt(1) == 1 ? true : false;
            }
            preSt.close();
        } catch (SQLException ex) {
            throw new SQLException("Failed to load lastest user index.");
        } finally {
            return res;
        }
    }

    public boolean getOrderLogCount() throws SQLException {
        boolean res = false;
        try {
            PreparedStatement preSt = conn.prepareStatement("EXEC proc_GetOrderLogCount");
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                res = rs.getInt(1) == 1 ? true : false;
            }
            preSt.close();
        } catch (SQLException ex) {
            throw new SQLException("Failed to get lastest log index.");
        } finally {
            return res;
        }
    }

    public boolean isUserAuth(String username, String password) throws SQLException {
        boolean res = false;
        try {
            PreparedStatement preSt = conn
                    .prepareStatement("EXEC proc_IsUserAuth @Username = ?, @Password = ?");
            preSt.setString(1, username);
            preSt.setString(2, password);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                res = rs.getInt(1) == 1 ? true : false;
            }
            preSt.close();
        } catch (SQLException ex) {
            throw new SQLException("Failed to load user auth.");
        }
        return res;
    }

    public boolean isUserRecovery(String recoveryKey) throws SQLException {
        boolean res = false;
        try {
            PreparedStatement preSt = conn
                    .prepareStatement("EXEC proc_IsUserRecovery @RecoveryKey = ?");
            preSt.setString(1, recoveryKey);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                res = rs.getInt(1) == 1 ? true : false;
            }
            preSt.close();
        } catch (SQLException ex) {
            throw new SQLException("Failed to load user recovery key.");
        } finally {
            return res;
        }
    }

    public List<String> getUserAuth(String recoveryKey) throws SQLException {
        List<String> res = null;
        try {
            PreparedStatement preSt = conn
                    .prepareStatement(
                            "SELECT [Username], [Password] FROM tblUser WHERE RecoveryKey = ?");
            preSt.setString(1, recoveryKey);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                res = new ArrayList<>();
                res.add(rs.getString(1));
                res.add(rs.getString(2));
            }
            preSt.close();
        } catch (SQLException ex) {
            throw new SQLException("Failed to load user recovery key.");
        }
        return res;
    }

    public int getUserID(String username, String password) throws SQLException {
        int userID = -1;
        try {
            PreparedStatement preSt = conn
                    .prepareStatement(
                            "SELECT [UserID] FROM tblUser WHERE Username = ? AND Password = ?");
            preSt.setString(1, username);
            preSt.setString(2, password);
            ResultSet rs = preSt.executeQuery();
            if (rs.next()) {
                userID = rs.getInt(1);
            }
            preSt.close();
        } catch (SQLException ex) {
            throw new SQLException("Failed to load user id.");
        }
        return userID;
    }
}
