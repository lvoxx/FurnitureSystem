package query.tool.model;

import java.sql.Date;

public class Cost {
    private int costID;
    private int userIDCreated;
    private int costCtgID;
    private int expense;
    private Date dateCreated;

    @Override
    public String toString() {
        return "{" +
                " costID='" + getCostID() + "'" +
                ", userIDCreated='" + getUserIDCreated() + "'" +
                ", costCtgID='" + getCostCtgID() + "'" +
                ", expense='" + getExpense() + "'" +
                ", dateCreated='" + getDateCreated() + "'" +
                "}";
    }

    public Cost(int costID, int userIDCreated, int costCtgID, int expense, Date dateCreated) {
        this.costID = costID;
        this.userIDCreated = userIDCreated;
        this.costCtgID = costCtgID;
        this.expense = expense;
        this.dateCreated = dateCreated;
    }

    public int getCostID() {
        return this.costID;
    }

    public void setCostID(int costID) {
        this.costID = costID;
    }

    public int getUserIDCreated() {
        return this.userIDCreated;
    }

    public void setUserIDCreated(int userIDCreated) {
        this.userIDCreated = userIDCreated;
    }

    public int getCostCtgID() {
        return this.costCtgID;
    }

    public void setCostCtgID(int costCtgID) {
        this.costCtgID = costCtgID;
    }

    public int getExpense() {
        return this.expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
