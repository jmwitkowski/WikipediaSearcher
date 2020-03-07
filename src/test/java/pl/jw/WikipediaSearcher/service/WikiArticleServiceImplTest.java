package pl.jw.WikipediaSearcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

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
}