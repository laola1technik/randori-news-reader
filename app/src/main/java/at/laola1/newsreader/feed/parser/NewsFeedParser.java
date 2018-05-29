package at.laola1.newsreader.feed.parser;

import android.support.annotation.NonNull;

import at.laola1.newsreader.feed.model.NewsFeed;

public class NewsFeedParser {

    public NewsFeedParser(String newsfeed) {

    }

    @NonNull
    public NewsFeed parse() {
        return new NewsFeed();
    }
}
