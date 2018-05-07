package students.polsl.eatnearbackend.model;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Restaurant implements Comparable<Restaurant>{

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double localizationLongitude;
    private double localizationLatitude;
    private String restaurantType;

    @Transient
    private long distance;

    @OneToMany
    private Set<Review> reviews;

    public Restaurant() {
    }

    public Restaurant(String name, double localizationLatitude, double localizationLongitude, String restaurantType) {
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

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Restaurant o) {
        return Double.compare(this.getDistance(), o.getDistance());
    }
}