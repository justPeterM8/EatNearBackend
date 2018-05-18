package students.polsl.eatnearbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,  reason = "There are no reviews for this restaurant")
public class NoReviewsException extends RuntimeException{}
