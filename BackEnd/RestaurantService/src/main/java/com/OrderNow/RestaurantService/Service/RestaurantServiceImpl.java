package com.OrderNow.RestaurantService.Service;

import com.OrderNow.RestaurantService.Domain.FoodMenu;
import com.OrderNow.RestaurantService.Domain.Restaurant;
import com.OrderNow.RestaurantService.Exception.FoodNotFoundException;
import com.OrderNow.RestaurantService.Exception.ItemNotFoundException;
import com.OrderNow.RestaurantService.Exception.RestaurantAlreadyExistsException;
import com.OrderNow.RestaurantService.Exception.RestaurantNotFoundException;
import com.OrderNow.RestaurantService.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class RestaurantServiceImpl implements RestaurantService{


    private RestaurantRepository restaurantRepository;
    @Autowired
    public RestaurantServiceImpl( RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public Restaurant registerRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        // Register a new user
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurant.getRestaurantId()    );
        if (existingRestaurant.isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return savedRestaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurant() throws RestaurantNotFoundException {
            List <Restaurant> restaurants=restaurantRepository.findAll();
            if (restaurantRepository.findAll().isEmpty())
            {
                throw new RestaurantNotFoundException();
            }

        return restaurants;
    }

    @Override
    public List<FoodMenu> getAllFoodMenu(String restaurantId) throws FoodNotFoundException {
        Optional<Restaurant> restaurantOptional =restaurantRepository.findById(restaurantId);
        System.out.println(restaurantOptional);

        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return restaurant.getFoodMenu();
        } else {
            throw new FoodNotFoundException();
        }
    }



    @Override
    public List<FoodMenu> getFoodMenuById(int itemId) throws ItemNotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(String.valueOf(itemId));

        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            return restaurant.getFoodMenu();
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public Optional<Restaurant> getRestaurant(String restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    @Override
    public FoodMenu addFoodItem(String restaurantId, FoodMenu foodMenu) throws RestaurantNotFoundException{
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException());

// Ensure the list is initialized (in case it's null)
        List<FoodMenu> currentFoodMenu = restaurant.getFoodMenu();
        if (currentFoodMenu == null) {
            currentFoodMenu = new ArrayList<>();
        }

        // Check if a food item with the same itemId already exists in this restaurant
        boolean itemExists = currentFoodMenu.stream()
                .anyMatch(existingItem -> existingItem.getItemId() == foodMenu.getItemId());

        if (itemExists) {
            throw new IllegalArgumentException("Food item with ID " + foodMenu.getItemId() + " already exists in this restaurant.");
        }

        // Add the new food item to the restaurant's food menu
        currentFoodMenu.add(foodMenu);
        restaurant.setFoodMenu(currentFoodMenu); // Update the restaurant's food menu

        // Save the updated restaurant with the new food item
        restaurantRepository.save(restaurant);

        return foodMenu;
    }

    @Override
    public boolean deleteById(String restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant.isEmpty()) {
            System.out.println("Restaurant with ID " + restaurantId + " not found.");
            throw new RestaurantNotFoundException();
        } else {
            restaurantRepository.deleteById(restaurantId);
            System.out.println("Restaurant with ID " + restaurantId + " deleted successfully.");
            return true;
        }
    }


    @Override
    public void deleteFoodMenu(String restaurantId, int itemId) throws RestaurantNotFoundException, ItemNotFoundException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            List<FoodMenu> foodMenuList = restaurant.getFoodMenu();

            for (FoodMenu foodMenu : foodMenuList) {
                if (foodMenu.getItemId() == itemId) {
                    foodMenuList.remove(foodMenu);
                    restaurantRepository.save(restaurant);
                    return; // Assuming itemId is unique across all food menus
                }
            }

            throw new ItemNotFoundException();
        } else {
            throw new RestaurantNotFoundException();
        }
    }
    
    }


