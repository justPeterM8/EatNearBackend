package students.polsl.eatnearbackend.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import students.polsl.eatnearbackend.model.Restaurant;
import students.polsl.eatnearbackend.model.Review;
import students.polsl.eatnearbackend.repositories.RestaurantRepository;
import students.polsl.eatnearbackend.repositories.ReviewRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private RestaurantRepository restaurantRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public DevBootstrap(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    private void initData(){
        //first restaurant
//        Restaurant piwniczka = new Restaurant("Piwniczka", 50.291301, 18.672197, "Italian");
        Restaurant piwniczka = new Restaurant("Piwniczka", 50.309855, 18.785851, "Italian");
        Review good = new Review(
                "Quite good food",
                "Jan",
                3.5,
                Date.from(LocalDate.now()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()));
        Review bad = new Review(
                "Didn't enjoy it at all",
                "Barbara",
                3.5,
                Date.from(LocalDate.of(2000, 10, 11)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()));

        Set<Review> reviews = new HashSet<>();
        reviews.add(good);
        reviews.add(bad);
        piwniczka.setReviews(reviews);
        reviewRepository.save(good);
        reviewRepository.save(bad);
        restaurantRepository.save(piwniczka);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
