package students.polsl.eatnearbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurantsSortedByDistance(50.294492, 18.671380);
    }

    @GetMapping("/nearRestaurants")
    public List<Restaurant> getAllNearRestaurants(){
        return restaurantService.getAllRestaurantsSortedByDistance(50.294492, 18.671380, 500);
    }
}
