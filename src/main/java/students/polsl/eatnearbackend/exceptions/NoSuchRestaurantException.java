package students.polsl.eatnearbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,  reason = "Restaurant with such name was not found")
public class NoSuchRestaurantException extends RuntimeException{}
