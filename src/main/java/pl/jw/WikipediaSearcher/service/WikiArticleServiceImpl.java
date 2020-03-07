package pl.jw.WikipediaSearcher.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.jw.WikipediaSearcher.model.WikiArticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WikiArticleServiceImpl implements WikiArticleService {
    @Value("${amount.of.search.articles}")
    private int amountOfSearchArticles;
    @Value("${snippet.expression}")
    private String snippetExpression;

    @Override
    public String getUrlResultForSearchedPhrase(String phrase) {
        RestTemplate restTemplate = new RestTemplate();
        String wikiJsonResponse = restTemplate.getForObject(createSearchQueryForWiki(phrase), String.class);

        if (wikiJsonResponse.isEmpty()) {
            //TODO throw ...
        }

        List<WikiArticle> wikiArticleList = parseJsonResponseToWikiArticleList(wikiJsonResponse);
        Optional<WikiArticle> searchedArticleOpt = wikiArticleList.stream()
                .filter(wikiArticleListElemnt -> checkIfArticleSnippetContainsExpression(wikiArticleListElemnt))
                .findFirst();
        if(!searchedArticleOpt.isPresent()){
            //TODO throw
        }

        return  "https://en.wikipedia.org/wiki/" + searchedArticleOpt.get().getTitle().replace(" ","_");
    }

    @Override
    public String createSearchQueryForWiki(String phrase) {
        StringBuilder query = new StringBuilder("https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srsearch=");
        query.append(phrase)
                .append("&srlimit=")
                .append(amountOfSearchArticles);
        return query.toString();
    }

    @Override
    public List<WikiArticle> parseJsonResponseToWikiArticleList(String wikiJsonResponse) {
        List<WikiArticle> wikiArticleList = new ArrayList<>();
        new JsonParser().parse(wikiJsonResponse).getAsJsonObject()
                .get("query").getAsJsonObject()
                .get("search").getAsJsonArray()
                .forEach(jsonElement -> {
                    WikiArticle wikiArticle = new WikiArticle();
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    wikiArticle.setTitle(jsonObject.get("title").getAsString());
                    wikiArticle.setPageId(jsonObject.get("pageid").getAsInt());
                    wikiArticle.setSnippet(jsonObject.get("snippet").getAsString());
                    wikiArticleList.add(wikiArticle);
                });
        return wikiArticleList;
    }

    private Boolean checkIfArticleSnippetContainsExpression(WikiArticle article){
        return article.getSnippet().toLowerCase().contains(snippetExpression.toLowerCase());
    }
}
