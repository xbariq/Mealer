package com.example.mealerapp;

public class MenuItem {
    private String meal;
    private String description;
    private String id;
    private String cookId;

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuItem(String meal, String description, String cookId) {
        this.description = description;
        this.meal= meal;
        this.cookId= cookId;
    }
    public MenuItem (){}

}
