package students.polsl.eatnearbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students.polsl.eatnearbackend.exceptions.NoReviewsException;
import students.polsl.eatnearbackend.exceptions.NoSuchRestaurantException;
import students.polsl.eatnearbackend.model.Restaurant;
import students.polsl.eatnearbackend.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReviewService extends BaseService {

    private RestaurantService restaurantService;

    @Autowired
    public ReviewService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public void performReviewCreation(String restaurantName, Review review) {
        Restaurant restaurantToUpdate = restaurantService.getRestaurantFromDbByName(restaurantName);
        Set<Review> reviewSet = restaurantToUpdate.getReviews();
        reviewSet.add(review);
        reviewRepository.save(review);
        restaurantRepository.save(restaurantToUpdate);
    }

    public List<Review> getReviewsFromSpecificRestaurant(String restaurantName) {
        Restaurant restaurantToGetReviewsFrom = restaurantService.getRestaurantFromDbByName(restaurantName);
        Set<Review> reviews = restaurantToGetReviewsFrom.getReviews();
        if ((reviews.size() == 0)) {//no reviews available
            throw new NoReviewsException();
        } else
            return new ArrayList<>(reviews);
    }
}
