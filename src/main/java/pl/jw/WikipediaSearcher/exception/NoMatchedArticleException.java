package pl.jw.WikipediaSearcher.exception;

public class NoMatchedArticleException extends Exception{

    public NoMatchedArticleException(String message) {
        super(message);
    }
}
