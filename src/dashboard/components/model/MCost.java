/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dashboard.components.model;

import query.tool.model.*;

/**
 *
 * @author Admin
 */
public class MCost {
    private Cost cost;
    private int userIDCreated;
    private String userNameCreated;
    private CostCategory costCategory;

    public MCost(Cost cost, int userIDCreated, String userNameCreated, CostCategory costCategory) {
        this.cost = cost;
        this.userIDCreated = userIDCreated;
        this.userNameCreated = userNameCreated;
        this.costCategory = costCategory;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public int getUserIDCreated() {
        return userIDCreated;
    }

    public void setUserIDCreated(int userIDCreated) {
        this.userIDCreated = userIDCreated;
    }

    public String getUserNameCreated() {
        return userNameCreated;
    }

    public void setUserNameCreated(String userNameCreated) {
        this.userNameCreated = userNameCreated;
    }

    public CostCategory getCostCategory() {
        return costCategory;
    }

    public void setCostCategory(CostCategory costCategory) {
        this.costCategory = costCategory;
    }

    

    
}
