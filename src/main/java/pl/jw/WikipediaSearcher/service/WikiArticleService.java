package pl.jw.WikipediaSearcher.service;

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
}
