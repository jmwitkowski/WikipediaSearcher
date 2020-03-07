# WikipediaSearcher
Find wikipedia's article for searched result

App that by given parameter, search for a football club name on the first ten pages of Wikipedia AP, and than redirect to found Wikipedia page.

Make get request to localhost:8080/{searched club name} to get wikipedia page witch searched football team 
(where {searched club name} is your phrase to search). You can also change amount of search articles (actual set on 10)
or expression to search in article snippet (actual set for "Football Club") in application properties file.

Project is a WebService Maven project and use Spring-boot, Lombok, and Junit for tests.
