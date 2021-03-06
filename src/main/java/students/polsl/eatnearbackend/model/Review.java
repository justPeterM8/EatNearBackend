package students.polsl.eatnearbackend.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String author;
    private double rating;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Review() {}

    public Review(String description, String nick, double rating, Date date) {
        this.description = description;
        this.author = nick;
        this.rating = rating;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
