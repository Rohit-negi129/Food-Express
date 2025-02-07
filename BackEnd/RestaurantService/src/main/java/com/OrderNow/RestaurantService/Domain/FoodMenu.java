package com.OrderNow.RestaurantService.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class FoodMenu {
    @Id
    private int itemId;
    private String itemName;
    private String description;
    private String foodImage;
    private int price;

    public FoodMenu(String itemName, int itemId, String description, String foodImage, int price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.description = description;
        this.foodImage = foodImage;
        this.price = price;
    }


    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public FoodMenu() {
    }

    @Override
    public String toString() {
        return "FoodMenu{" +
                "itemName='" + itemName + '\'' +
                ", itemId=" + itemId +
                ", description='" + description + '\'' +
                ", foodImage='" + foodImage + '\'' +
                ", price=" + price +
                '}';
    }

}
