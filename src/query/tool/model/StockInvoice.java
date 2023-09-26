package query.tool.model;

import java.sql.Date;

public class StockInvoice {
    private int stkInvID;
    private int userIDCreated;
    private Date dateAdded;

    public StockInvoice(int stkInvID, int userIDCreated, Date dateAdded) {
        this.stkInvID = stkInvID;
        this.userIDCreated = userIDCreated;
        this.dateAdded = dateAdded;
    }

    public int getStkInvID() {
        return this.stkInvID;
    }

    public void setStkInvID(int stkInvID) {
        this.stkInvID = stkInvID;
    }

    public int getUserIDCreated() {
        return this.userIDCreated;
    }

    public void setUserIDCreated(int userIDCreated) {
        this.userIDCreated = userIDCreated;
    }

    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

}
