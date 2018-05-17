package students.polsl.eatnearbackend.services;

import org.springframework.stereotype.Service;
import students.polsl.eatnearbackend.exceptions.NoSuchRestaurantException;
import students.polsl.eatnearbackend.model.Restaurant;
import students.polsl.eatnearbackend.model.Review;
import java.util.Set;

@Service
public class ReviewService extends BaseService {
    public void performReviewCreation(String restaurantName, Review review){
        Restaurant restaurantToUpdate = restaurantRepository.findByName(restaurantName);
        if (restaurantToUpdate == null)
            throw new NoSuchRestaurantException();
        Set<Review> reviewSet = restaurantToUpdate.getReviews();
        reviewSet.add(review);
        reviewRepository.save(review);
        restaurantRepository.save(restaurantToUpdate);
    }
}
