package query.tool.query;

import query.tool.model.CostInvoice;
import query.tool.model.CostInvoiceDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CostInvoiceQuery {
    private final String SELECT = "SELECT * FROM tblCostInvoice  WHERE [CostInvID] = ?";
    private final String SUB_SELECT = "SELECT * FROM tblCostInvoiceDetails  WHERE [CostInvID] = ?";

    private final String INSERT_INVOICE = "EXEC proc_InsertCostInvoice @UserIDCreated = ?";
    private final String INSERT_INVOICE_DETAILS = "EXEC proc_InsertCostInvoiceDetails @CostInvID = ?, @CostID = ?, @Quantity = ?";
    private Connection conn;

    public CostInvoiceQuery(Connection conn) {
        this.conn = conn;
    }

    public List<CostInvoice> selectCostInvoice(List<Integer> costInvIDs) throws SQLException {
        try {
            List<CostInvoice> res = new ArrayList<>();
            List<CostInvoiceDetails> subRes;

            PreparedStatement preSt, subPreSt;
            ResultSet rs, subRs;

            for (int i = 0; i < costInvIDs.size(); ++i) {
                subRes = new ArrayList<>();
                subPreSt = this.conn.prepareStatement(SUB_SELECT);
                subPreSt.setInt(1, costInvIDs.get(i));
                subRs = subPreSt.executeQuery();
                // Get List of Cost Invoice Details by ID
                while (subRs.next()) {
                    subRes.add(new CostInvoiceDetails(subRs.getInt("CostInvID"), subRs.getInt("CostID"),
                            subRs.getInt("Quantity")));
                }
                // Get Cost Invoice
                preSt = this.conn.prepareStatement(SELECT);
                preSt.setInt(1, costInvIDs.get(i));
                rs = preSt.executeQuery();
                if (rs.next()) {
                    res.add(new CostInvoice(rs.getInt("CostInvID"), rs.getInt("UserIDCreated"),
                            /* here is the list */ subRes,
                            rs.getDate("DateAdded")));
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new SQLException("Failed to get Cost Invoice ID: " + costInvIDs.toString());
        }
    }

    public int insertCostInvoice(CostInvoice costInv) throws SQLException {
        try {
            boolean isInsertCostInv = false, isInsertCostInvDetails = false;
            int costInvID = -1;
            PreparedStatement preSt = this.conn
                    .prepareStatement(INSERT_INVOICE);
            preSt.setInt(1, costInv.getUserIDCreated());
            if (preSt.executeUpdate() == 1)
                isInsertCostInv = true;

            // Get Instance ID key
            try (ResultSet generatedKeys = preSt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    costInvID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating Cost Invoice failed, no ID obtained.");
                }
            }
            for (CostInvoiceDetails item : costInv.getCostInvDetails()) {
                preSt = this.conn.prepareStatement(INSERT_INVOICE_DETAILS);
                preSt.setInt(1, costInvID);
                preSt.setInt(2, item.getCostID());
                preSt.setLong(3, item.getQuantity());
                if (preSt.executeUpdate() == 1)
                    isInsertCostInvDetails = true;
                else
                    isInsertCostInvDetails = false;
            }
            // If CostInvoice and CostInvoiceDetails are successfully inserted, return 1
            if (isInsertCostInv && isInsertCostInvDetails)
                return 1;
        } catch (SQLException ex) {
            throw new SQLException("Failed to insert the cost invoice: " + costInv.toString());
        }
        return -1;
    }
}
