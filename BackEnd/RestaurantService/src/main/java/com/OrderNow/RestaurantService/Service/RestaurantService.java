package com.OrderNow.RestaurantService.Service;

import com.OrderNow.RestaurantService.Domain.FoodMenu;
import com.OrderNow.RestaurantService.Domain.Restaurant;
import com.OrderNow.RestaurantService.Exception.FoodNotFoundException;
import com.OrderNow.RestaurantService.Exception.ItemNotFoundException;
import com.OrderNow.RestaurantService.Exception.RestaurantAlreadyExistsException;
import com.OrderNow.RestaurantService.Exception.RestaurantNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    Restaurant registerRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    List<Restaurant> getAllRestaurant() throws RestaurantNotFoundException;

    public List<FoodMenu> getAllFoodMenu(String restaurantId) throws FoodNotFoundException;

    public List<FoodMenu> getFoodMenuById(int itemId)throws ItemNotFoundException;

   public Optional<Restaurant> getRestaurant(String restaurantId);

    public FoodMenu addFoodItem(String restaurantId, FoodMenu foodMenu) throws RestaurantNotFoundException;

    public boolean deleteById(String restaurantId) throws RestaurantNotFoundException;

    public void deleteFoodMenu(String restaurantId, int itemId) throws RestaurantNotFoundException, ItemNotFoundException;
}
