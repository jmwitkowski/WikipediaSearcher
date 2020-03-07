package pl.jw.WikipediaSearcher.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.jw.WikipediaSearcher.exception.EmptyResponseException;
import pl.jw.WikipediaSearcher.exception.NoMatchedArticleException;
import pl.jw.WikipediaSearcher.service.WikiArticleService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class WikipediaSearcherController {
    private final WikiArticleService wikiArticleService;

    public WikipediaSearcherController(WikiArticleService wikiArticleService) {
        this.wikiArticleService = wikiArticleService;
    }

    @GetMapping("/{phrase}")
    public ResponseEntity<HttpHeaders> redirectToWikipediaFittedArticle(@PathVariable String phrase) throws EmptyResponseException, NoMatchedArticleException, URISyntaxException {
        String urlToArticle = wikiArticleService.getUrlResultForSearchedPhrase(phrase);
        URI articleUri = new URI(urlToArticle);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(articleUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
