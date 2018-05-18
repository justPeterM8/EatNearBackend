package students.polsl.eatnearbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import students.polsl.eatnearbackend.model.Restaurant;
import students.polsl.eatnearbackend.services.RestaurantService;
import java.util.List;

@RestController
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/allRestaurants")
    public List<Restaurant> getAllRestaurants(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude){
        return restaurantService.getAllRestaurantsSortedByDistance(latitude, longitude);
    }

    @GetMapping("/nearRestaurants")
    public List<Restaurant> getAllNearRestaurants(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude, @RequestParam("distance") long distance){
        return restaurantService.getAllRestaurantsSortedByDistance(latitude, longitude, distance);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity createNewRestaurant(@RequestBody Restaurant restaurant){
            restaurantService.performRestaurantCreation(restaurant);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public String test(){
        return "ok";
    }
}

