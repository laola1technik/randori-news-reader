package at.laola1.newsreader;

import org.junit.Test;

import at.laola1.newsreader.feed.model.NewsFeed;
import at.laola1.newsreader.feed.parser.NewsFeedParser;

import static org.junit.Assert.assertEquals;

public class NewsFeedParserTest {
    @Test
    public void shouldParseEmptyJsonToNewsFeed() throws InvalidFeedException {
        String newsFeedJson = "[]";
        NewsFeedParser parser = new NewsFeedParser(newsFeedJson);

        NewsFeed newsFeed = parser.parse();

        assertEquals(true, newsFeed.isEmpty());
    }

    @Test(expected = InvalidFeedException.class)
    public void shouldFailIfFeedIsNoValidNewsFeed() throws InvalidFeedException {
        String newsFeedJson = "-";
        NewsFeedParser parser = new NewsFeedParser(newsFeedJson);

        parser.parse();
    }
}
