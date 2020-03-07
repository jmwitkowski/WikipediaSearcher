package pl.jw.WikipediaSearcher.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WikiArticleServiceImpl implements WikiArticleService {
    @Value("${amount.of.search.articles}")
    private int amountOfSearchArticles;

    @Override
    public String getUrlResultForSearchedPhrase(String phrase) {
        RestTemplate restTemplate = new RestTemplate();
        String wikiJsonResponse = restTemplate.getForObject(createSearchQueryForWiki(phrase), String.class);

        if (wikiJsonResponse.isEmpty()) {
            //TODO throw ...
        }

        return "";
    }

    @Override
    public String createSearchQueryForWiki(String phrase) {
        StringBuilder query = new StringBuilder("https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srsearch=");
        query.append(phrase)
                .append("&srlimit=")
                .append(amountOfSearchArticles);
        return query.toString();
    }
}
