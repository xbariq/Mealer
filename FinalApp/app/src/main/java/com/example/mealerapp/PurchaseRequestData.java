package com.example.mealerapp;

import java.io.Serializable;

public class PurchaseRequestData implements Serializable {
    private String clientId;
    private String clientName;
    private String cookId;
    private String cookName;
    private String status;
    private MenuItem menuItem;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PurchaseRequestData(String clientId, String clientName, String cookId, String cookName, MenuItem menuItem, String id) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.cookId = cookId;
        this.cookName = cookName;
        this.status = Constants.PROCESSING;
        this.menuItem = menuItem;
        this.id= id;
    }

    public PurchaseRequestData (){

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
