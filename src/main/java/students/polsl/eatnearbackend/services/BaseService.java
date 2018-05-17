package students.polsl.eatnearbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import students.polsl.eatnearbackend.repositories.RestaurantRepository;
import students.polsl.eatnearbackend.repositories.ReviewRepository;

public class BaseService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ReviewRepository reviewRepository;
}
