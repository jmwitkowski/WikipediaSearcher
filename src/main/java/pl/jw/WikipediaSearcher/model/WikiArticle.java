package pl.jw.WikipediaSearcher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Pojo with information about articles gets from wikipedia API
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WikiArticle {

    private String title;
    private int pageId;
    private String snippet;

}
