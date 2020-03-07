package pl.jw.WikipediaSearcher.exception;

public class EmptyResponseException extends Exception {

    public EmptyResponseException(String message) {
        super(message);
    }
}
