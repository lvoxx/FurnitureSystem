package query.tool.model;

import java.sql.Date;
import java.util.List;

public class CostInvoice {
    private int costInvID;
    private int userIDCreated;
    private List<CostInvoiceDetails> costInvDetails;
    private Date dateAdded;

    public CostInvoice(int costInvID, int userIDCreated, List<CostInvoiceDetails> costInvDetails, Date dateAdded) {
        this.costInvID = costInvID;
        this.userIDCreated = userIDCreated;
        this.costInvDetails = costInvDetails;
        this.dateAdded = dateAdded;
    }

    public int getCostInvID() {
        return this.costInvID;
    }

    public void setCostInvID(int costInvID) {
        this.costInvID = costInvID;
    }

    public int getUserIDCreated() {
        return this.userIDCreated;
    }

    public void setUserIDCreated(int userIDCreated) {
        this.userIDCreated = userIDCreated;
    }

    public List<CostInvoiceDetails> getCostInvDetails() {
        return this.costInvDetails;
    }

    public void setCostInvDetails(List<CostInvoiceDetails> costInvDetails) {
        this.costInvDetails = costInvDetails;
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "{" +
                " costInvID='" + getCostInvID() + "'" +
                ", userIDCreated='" + getUserIDCreated() + "'" +
                ", costInvDetails='" + getCostInvDetails() + "'" +
                ", dateAdded='" + getDateAdded() + "'" +
                "}";
    }

}
