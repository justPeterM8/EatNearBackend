package students.polsl.eatnearbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import students.polsl.eatnearbackend.model.Review;
import students.polsl.eatnearbackend.services.ReviewService;

import java.util.List;

@RestController
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>>createNewRestaurant(@RequestParam("restaurantName") String restaurantName){
        return ResponseEntity.ok(reviewService.getReviewsFromSpecificRestaurant(restaurantName));
    }

    @PostMapping("/addReview")
    public ResponseEntity createNewRestaurant(@RequestParam("restaurantName") String restaurantName, @RequestBody Review review){
        reviewService.performReviewCreation(restaurantName, review);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
