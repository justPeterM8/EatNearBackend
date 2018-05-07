package students.polsl.eatnearbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import students.polsl.eatnearbackend.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
}
