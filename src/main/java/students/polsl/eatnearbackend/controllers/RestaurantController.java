package students.polsl.eatnearbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import students.polsl.eatnearbackend.model.Restaurant;
import students.polsl.eatnearbackend.repositories.RestaurantRepository;
import students.polsl.eatnearbackend.services.RestaurantService;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class RestaurantController {
    private RestaurantService restaurantService;
    private RestaurantRepository restaurantRepository;
    private Logger logger = Logger.getLogger("RestaurantController.class");

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/allRestaurants/{latitude}/{longitude}")
    public List<Restaurant> getAllRestaurants(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude){
        return restaurantService.getAllRestaurantsSortedByDistance(latitude, longitude);
    }

    @GetMapping("/nearRestaurants/{latitude}/{longitude}/{distance}")
    public List<Restaurant> getAllNearRestaurants(@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude, @PathVariable("distance") long distance){
        return restaurantService.getAllRestaurantsSortedByDistance(latitude, longitude, distance);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<Restaurant> createNewRestaurant(@RequestBody Restaurant restaurant){
        if (!restaurantService.isRestaurantlreadyAvailable(restaurant)){
            Restaurant savedRestaurant = restaurantRepository.save(restaurant);
            return ResponseEntity.ok(savedRestaurant);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
