package pl.jw.WikipediaSearcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import java.net.URISyntaxException;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = RestClientException.class)
    public ResponseEntity<String> handleException(RestClientException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = EmptyResponseException.class)
    public ResponseEntity<String> handleException(EmptyResponseException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NoMatchedArticleException.class)
    public ResponseEntity<String> handleException(NoMatchedArticleException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = URISyntaxException.class)
    public ResponseEntity<String> handleException(URISyntaxException exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
