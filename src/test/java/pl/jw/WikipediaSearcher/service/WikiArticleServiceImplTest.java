package pl.jw.WikipediaSearcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import pl.jw.WikipediaSearcher.model.WikiArticle;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WikiArticleServiceImplTest {
    @Value("${amount.of.search.articles}")
    private int amountOfSearchArticles;
    private WikiArticleService wikiArticleService = new WikiArticleServiceImpl();

    @Test
    void shouldCreateSearchQueryForWiki() {
        //Given
        String expectedUrl = "https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srsearch=Liverpool&srlimit="
                +amountOfSearchArticles;
        //When
        String gettedUrl = wikiArticleService.createSearchQueryForWiki("Liverpool");
        //Then
        assertThat(gettedUrl).isEqualTo(expectedUrl);
    }

    @Test
    void shouldCreateThreeElementWikiArticleListFromJsonResponse(){
        //Given
        String jsonResponse = "{\n" +
                "    \"batchcomplete\": \"\",\n" +
                "    \"continue\": {\n" +
                "        \"sroffset\": 10,\n" +
                "        \"continue\": \"-||\"\n" +
                "    },\n" +
                "    \"query\": {\n" +
                "        \"searchinfo\": {\n" +
                "            \"totalhits\": 62736\n" +
                "        },\n" +
                "        \"search\": [\n" +
                "            {\n" +
                "                \"ns\": 0,\n" +
                "                \"title\": \"Liverpool F.C.\",\n" +
                "                \"pageid\": 18119,\n" +
                "                \"size\": 104493,\n" +
                "                \"wordcount\": 8486,\n" +
                "                \"snippet\": \"<span class=\\\"searchmatch\\\">Liverpool</span> Football Club is a professional football club in <span class=\\\"searchmatch\\\">Liverpool</span>, England, that competes in the Premier League, the top tier of English football. The\",\n" +
                "                \"timestamp\": \"2020-03-03T12:11:39Z\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ns\": 0,\n" +
                "                \"title\": \"Liverpool\",\n" +
                "                \"pageid\": 18081,\n" +
                "                \"size\": 216415,\n" +
                "                \"wordcount\": 20473,\n" +
                "                \"snippet\": \"<span class=\\\"searchmatch\\\">Liverpool</span> is a city and metropolitan borough in Merseyside, England. As of 2018, the population is approximately 494,814. <span class=\\\"searchmatch\\\">Liverpool</span> is the ninth-largest\",\n" +
                "                \"timestamp\": \"2020-03-01T00:15:21Z\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"ns\": 0,\n" +
                "                \"title\": \"Steven Gerrard\",\n" +
                "                \"pageid\": 547384,\n" +
                "                \"size\": 131438,\n" +
                "                \"wordcount\": 11448,\n" +
                "                \"snippet\": \"spent the majority of his playing career as a central midfielder for <span class=\\\"searchmatch\\\">Liverpool</span>, with most of that time spent as club captain, as well as captaining the\",\n" +
                "                \"timestamp\": \"2020-03-04T21:42:36Z\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        //When
        List<WikiArticle> wikiArticleList = wikiArticleService.parseJsonResponseToWikiArticleList(jsonResponse);

        //Then
        assertThat(wikiArticleList.size()).isEqualTo(3);
    }
}