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
        Restaurant piwniczka = new Restaurant(
                "Piwniczka",
                50.291233,
                18.672021,
                "Łużycka 2e, 44-100 Gliwice", "Italian"
        );
        Review good = new Review(
                "Quite good food",
                "Jan",
                3.5,
                Date.from(
                        LocalDate.now()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()));
        Review bad = new Review(
                "The food was bad",
                "Barbara",
                3.5,
                Date.from(
                        LocalDate.of(2000, 10, 11)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()));

        Set<Review> reviewsPiwniczka = new HashSet<>();
        reviewsPiwniczka.add(good);
        reviewsPiwniczka.add(bad);
        piwniczka.setReviews(reviewsPiwniczka);
        reviewRepository.save(good);
        reviewRepository.save(bad);
        restaurantRepository.save(piwniczka);

        //second restaurant
        Restaurant szamma = new Restaurant("Szamma",
                50.294006,
                18.664711,
                "NU1, Raciborska 1, Gliwice", "International"
        );
        Review superGood = new Review(
                "The food was fantastic",
                "Marek",
                5,
                Date.from(
                        LocalDate.of(2017, 12, 2)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()));
        Review reallyBad = new Review(
                "Didn't enjoy it at all, terrible food",
                "Katarzyna",
                1,
                Date.from(
                        LocalDate.of(2010, 11, 11)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()));

        Set<Review> reviewsSzamma  = new HashSet<>();
        reviewsSzamma.add(superGood);
        reviewsSzamma.add(reallyBad);
        szamma.setReviews(reviewsSzamma);
        reviewRepository.save(superGood);
        reviewRepository.save(reallyBad);
        restaurantRepository.save(szamma);

        //third restaurant
        Restaurant trattoria = new Restaurant("Trattoria",
                50.279670,
                18.586554,
                "Daszyńskiego 540, 44-100 Gliwice", "Italian"
        );
        Review reallyGood = new Review(
                "The food was quite good, but could be better",
                "Marcin",
                4,
                Date.from(
                        LocalDate.of(2018, 1, 4)
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()));
        Review quiteBad = new Review(
                "It was't awful, but I did't finish my meal",
                "Rafal",
                1,
                Date.from(
                        LocalDate.of(2010, 11, 11)
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()));

        Set<Review> reviewsTrattoria  = new HashSet<>();
        reviewsTrattoria.add(reallyGood);
        reviewsTrattoria.add(quiteBad);
        trattoria.setReviews(reviewsTrattoria);
        reviewRepository.save(reallyGood);
        reviewRepository.save(quiteBad);
        restaurantRepository.save(trattoria);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
