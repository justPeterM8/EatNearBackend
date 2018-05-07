package students.polsl.eatnearbackend.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double localizationLongitude;
    private double localizationLatitude;
    private String restaurantType;

    @OneToMany(mappedBy = "restaurant")
    private Set<Review> reviews = new HashSet();

    public Restaurant() {
    }

    public Restaurant(String name, double localizationLongitude, double localizationLatitude, String restaurantType) {
        this.name = name;
        this.localizationLongitude = localizationLongitude;
        this.localizationLatitude = localizationLatitude;
        this.restaurantType = restaurantType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLocalizationLongitude() {
        return localizationLongitude;
    }

    public void setLocalizationLongitude(double localizationLongitude) {
        this.localizationLongitude = localizationLongitude;
    }

    public double getLocalizationLatitude() {
        return localizationLatitude;
    }

    public void setLocalizationLatitude(double localizationLatitude) {
        this.localizationLatitude = localizationLatitude;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}