package at.laola1.newsreader.feed.parser; // TODO PK part of feed, no need for extra package with 1 class

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import at.laola1.newsreader.InvalidFeedException;
import at.laola1.newsreader.feed.model.NewsFeed;

public class NewsFeedParser {

    private String newsFeedJson; // PK final

    public NewsFeedParser(String newsFeedJson) {
        this.newsFeedJson = newsFeedJson;
    }

    @NonNull
    public NewsFeed parse() throws InvalidFeedException {
        Gson jsonParser = new Gson();
        try {
            NewsFeed newsFeed = jsonParser.fromJson(newsFeedJson, NewsFeed.class);
            if (newsFeed == null) {
                throw new InvalidFeedException("Can not parse News Feed");
            }

            return newsFeed;
        } catch (Exception e) {
            throw new InvalidFeedException(e.getMessage()); // TODO PK this drops original stack trace
        }
    }
}
