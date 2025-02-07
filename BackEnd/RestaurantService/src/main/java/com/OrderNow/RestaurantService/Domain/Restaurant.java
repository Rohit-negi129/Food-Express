package com.OrderNow.RestaurantService.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Restaurant {
    @Id
    private String restaurantId;
    private String restaurantName;
    private String description;
    private String location;
    private int rating;
    private String restaurantImage;
    private List<FoodMenu> foodMenu;

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        rating = rating;
    }

    public List<FoodMenu> getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(List<FoodMenu> foodMenu) {
        this.foodMenu = foodMenu;
    }


    public Restaurant(String restaurantId, String restaurantName, String description, String location, int rating, String restaurantImage) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.description = description;
        this.location = location;
        this.rating = rating;
        this.restaurantImage = restaurantImage;
    }

    public Restaurant() {
        this.foodMenu=new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId='" + restaurantId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", restaurantImage='" + restaurantImage + '\'' +
                ", foodMenu=" + foodMenu +
                '}';
    }
}
