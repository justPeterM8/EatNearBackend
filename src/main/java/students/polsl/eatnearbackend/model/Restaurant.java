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
    private String address;
    private String restaurantType;

    @Transient
    private long distance;

    @OneToMany
    private Set<Review> reviews;

    @Transient
    private double overallRating;

    public Restaurant() {
    }

    public Restaurant(String name, double localizationLatitude, double localizationLongitude, String address, String restaurantType) {
        this.name = name;
        this.localizationLongitude = localizationLongitude;
        this.localizationLatitude = localizationLatitude;
        this.address = address;
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

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Restaurant o) {
        return Double.compare(this.getDistance(), o.getDistance());
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", localizationLongitude=" + localizationLongitude +
                ", localizationLatitude=" + localizationLatitude +
                ", restaurantType='" + restaurantType + '\'' +
                ", distance=" + distance +
                ", reviews=" + reviews +
                '}';
    }
}