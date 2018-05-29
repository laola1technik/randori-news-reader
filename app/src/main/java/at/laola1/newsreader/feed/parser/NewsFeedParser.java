package at.laola1.newsreader.feed.parser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import at.laola1.newsreader.InvalidFeedException;
import at.laola1.newsreader.feed.model.NewsFeed;

public class NewsFeedParser {

    private String newsFeedJson;

    public NewsFeedParser(String newsFeedJson) {
        this.newsFeedJson = newsFeedJson;
    }

    @NonNull
    public NewsFeed parse() throws InvalidFeedException {
        Gson jsonParser = new Gson();
        try {
            return jsonParser.fromJson(newsFeedJson, NewsFeed.class);
        } catch (Exception e) {
            throw new InvalidFeedException(e.getMessage());
        }
    }
}
