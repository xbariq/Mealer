package com.example.mealerapp;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private String meal;
    private String description;
    private String id;
    private String cookId;
    private boolean offeredMeal;
    private String mealType;
    private String cuisineType;

    public String getMealType() {
        return mealType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public boolean isOfferedMeal() {
        return offeredMeal;
    }

    public void setOfferedMeal(boolean offeredMeal) {
        this.offeredMeal = offeredMeal;
    }

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

    public MenuItem(String meal, String description, String cookId, boolean offeredMeal, String mealType, String cuisineType) {
        this.description = description;
        this.meal= meal;
        this.cookId= cookId;
        this.offeredMeal= offeredMeal;
        this.mealType= mealType;
        this.cuisineType= cuisineType;
    }
    public MenuItem (){}

}
