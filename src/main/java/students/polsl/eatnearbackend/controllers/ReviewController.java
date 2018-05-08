package students.polsl.eatnearbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import students.polsl.eatnearbackend.model.Restaurant;
import students.polsl.eatnearbackend.model.Review;
import students.polsl.eatnearbackend.repositories.RestaurantRepository;
import students.polsl.eatnearbackend.repositories.ReviewRepository;
import java.util.Set;

@RestController
public class ReviewController {

    private ReviewRepository reviewRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/addReview/{restaurantName}")
    public ResponseEntity<Review> createNewRestaurant(@PathVariable("restaurantName") String restaurantName, @RequestBody Review review){
        Restaurant restaurantToUpdate = restaurantRepository.findByName(restaurantName);
        if (restaurantToUpdate == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        Set<Review> reviewSet = restaurantToUpdate.getReviews();
        reviewSet.add(review);
        Review savedReview = reviewRepository.save(review);
        restaurantRepository.save(restaurantToUpdate);
        return ResponseEntity.ok(savedReview);
    }
}
