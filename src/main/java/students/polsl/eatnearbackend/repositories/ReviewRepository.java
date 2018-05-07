package students.polsl.eatnearbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import students.polsl.eatnearbackend.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
}
