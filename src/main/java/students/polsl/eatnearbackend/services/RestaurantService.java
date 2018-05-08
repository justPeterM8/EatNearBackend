package students.polsl.eatnearbackend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students.polsl.eatnearbackend.model.Restaurant;
import students.polsl.eatnearbackend.repositories.RestaurantRepository;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private Logger logger = Logger.getLogger("RestaurantService.class");
    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurantsSortedByDistance(double usersLatitude, double usersLongitude, long... maxDistance){
        List<Restaurant> restaurantsWithDistances = injectDistancesIntoRestaurants(restaurantRepository.findAll(), usersLatitude, usersLongitude);
        if (maxDistance.length != 0)
            restaurantsWithDistances = filterNearRestaurantsByDistance(restaurantsWithDistances, maxDistance[0]);
        Collections.sort(restaurantsWithDistances);
        return restaurantsWithDistances;
    }

    private List<Restaurant> filterNearRestaurantsByDistance(List<Restaurant> restaurants, long distance){
        List<Restaurant> filteredRestaurants = restaurants
                .stream()
                .filter(restaurant -> restaurant.getDistance() <= distance)
                .collect(Collectors.toList());
        return filteredRestaurants;
    }

    private List<Restaurant> injectDistancesIntoRestaurants(List<Restaurant> restaurants, double usersLatitude, double usersLongitude){
            return restaurants
                    .stream()
                    .peek(restaurant -> restaurant.setDistance(
                            getDistanceFromLatLong(
                                    restaurant.getLocalizationLatitude(),
                                    restaurant.getLocalizationLongitude(),
                                    usersLatitude,
                                    usersLongitude
                            )
                    ))
                    .collect(Collectors.toList());
    }

    public boolean isRestaurantlreadyAvailable(Restaurant restaurant){
        return restaurantRepository//checking if restaurant with such name already exists
                .findAll()
                .stream()
                .filter(listRestaurant -> listRestaurant.getName().equals(restaurant.getName()))
                .anyMatch(listRestaurant -> getDistanceFromLatLong(
                        listRestaurant.getLocalizationLatitude(),
                        listRestaurant.getLocalizationLongitude(),
                        restaurant.getLocalizationLatitude(),
                        restaurant.getLocalizationLongitude()
                        ) <= 50);
    }

    private long getDistanceFromLatLong(double lat1, double lon1, double lat2, double lon2) {
        double R = 6378.137; // Radius of earth in KM
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return (long) (d * 1000);
    }
}
