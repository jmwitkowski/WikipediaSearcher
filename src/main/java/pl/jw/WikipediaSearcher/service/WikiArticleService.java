package pl.jw.WikipediaSearcher.service;

import pl.jw.WikipediaSearcher.model.WikiArticle;

import java.util.List;

public interface WikiArticleService {

    /**
     * For given phrase, search articles on wikipedia and return first fitted with property
     * param (snippetExpression) result as a URL to article
     *
     * @param phrase given as a parameter from controller endpoint
     * @return String with url to wikipedia article
     */
    String getUrlResultForSearchedPhrase(String phrase);

    /**
     * Create url with query for wikipedia api needed to search articles
     *
     * @param phrase given as a parameter from controller endpoint
     * @return String with url to wikipedia search query
     */
    String createSearchQueryForWiki(String phrase);

    /**
     * By given Json in String parse Json form response from wikipedia to list of WikiArticles objects
     * @param wikiJsonResponse - response from wikipedia
     * @return List of WikiArticles
     */
    List<WikiArticle> parseJsonResponseToWikiArticleList(String wikiJsonResponse);
}
