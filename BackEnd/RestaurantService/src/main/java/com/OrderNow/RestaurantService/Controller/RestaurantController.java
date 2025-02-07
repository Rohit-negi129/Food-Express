package com.OrderNow.RestaurantService.Controller;

import com.OrderNow.RestaurantService.Domain.FoodMenu;
import com.OrderNow.RestaurantService.Domain.Restaurant;
import com.OrderNow.RestaurantService.Exception.FoodNotFoundException;
import com.OrderNow.RestaurantService.Exception.ItemNotFoundException;
import com.OrderNow.RestaurantService.Exception.RestaurantAlreadyExistsException;
import com.OrderNow.RestaurantService.Exception.RestaurantNotFoundException;
import com.OrderNow.RestaurantService.Service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
public class RestaurantController {

    private ResponseEntity<?> responseEntity;
    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping("/restaurant/register")
    public ResponseEntity<?> registerRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {

        try {
            responseEntity = new ResponseEntity<>(restaurantService.registerRestaurant(restaurant), HttpStatus.CREATED);
        } catch (RestaurantAlreadyExistsException e) {
            throw new RestaurantAlreadyExistsException();
        }
        return responseEntity;


    }
    @GetMapping("/restaurants")
    public ResponseEntity<?> getAllRestaurant() throws RestaurantNotFoundException {
        try {
            List<Restaurant> restaurants = restaurantService.getAllRestaurant();
            return new ResponseEntity<>(restaurants, HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<>("No restaurants found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<?> getRestaurant(@PathVariable String restaurantId) {
        try {
            Optional<Restaurant> restaurant = restaurantService.getRestaurant(restaurantId);
            return restaurant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity("Restaurant not found", HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving restaurant details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/restaurants/{restaurantId}/foodMenu")
    public ResponseEntity<?> getAllFoodMenu(@PathVariable String restaurantId) throws FoodNotFoundException{
//        System.out.println(restaurantId);
//        System.out.println("hello");
        try {
            List<FoodMenu> foodMenu = restaurantService.getAllFoodMenu(restaurantId);

            return new ResponseEntity<>(foodMenu, HttpStatus.OK);
        } catch (FoodNotFoundException e) {
            return new ResponseEntity<>("Food menu not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/foodMenu/{itemId}")
    public ResponseEntity<?> getFoodMenuById(@PathVariable int itemId) throws ItemNotFoundException{
        try {
            List<FoodMenu> foodMenu = restaurantService.getFoodMenuById(itemId);
            return new ResponseEntity<>(foodMenu, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>("Food menu item not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{restaurantId}/add-food-menu")
    public ResponseEntity<?> addFoodItem(@PathVariable String restaurantId, @RequestBody FoodMenu foodMenu) {
        try {
            // Add the food item to the restaurant's menu using the service
            FoodMenu addedFoodItem = restaurantService.addFoodItem(restaurantId, foodMenu);

            // Return a success response with the added food item and a CREATED (201) status
            return new ResponseEntity<>(addedFoodItem, HttpStatus.CREATED);
        } catch (RestaurantNotFoundException ex) {
            // Return a NOT FOUND (404) response if the restaurant is not found
            return new ResponseEntity<>("Restaurant not found with ID: " + restaurantId, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException ex) {
            // Return a BAD REQUEST (400) response if a food item with the same ID already exists
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle any other exceptions and return a generic error response
            return new ResponseEntity<>("Error adding food item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete-restaurant/{restaurantId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String restaurantId) {
        try {
            System.out.println("Attempting to delete restaurant with ID: " + restaurantId);
            restaurantService.deleteById(restaurantId);

            // Return 204 No Content for successful deletion
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }
    }




    @DeleteMapping("/restaurants/{restaurantId}/foodMenu/{itemId}")
    public ResponseEntity<?> deleteFoodMenu(@PathVariable String restaurantId, @PathVariable int itemId) {
        try {
            restaurantService.deleteFoodMenu(restaurantId, itemId);
            return new ResponseEntity<>("Food menu item deleted successfully", HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>("Food menu item not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting food menu item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
