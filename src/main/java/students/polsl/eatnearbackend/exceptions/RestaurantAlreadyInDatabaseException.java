package students.polsl.eatnearbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,  reason = "Restaurant with such name is already saved in database")
public class RestaurantAlreadyInDatabaseException extends RuntimeException{}
