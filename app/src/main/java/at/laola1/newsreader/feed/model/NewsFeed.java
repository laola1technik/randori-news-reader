package at.laola1.newsreader.feed.model; // TODO PK part of feed, no need for extra package with 1 class

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsFeed extends ArrayList<NewsFeed.NewsItem> { // TODO PK do not extend list, contain list
    public static class NewsItem {

        // TODO PK discuss: set SerializedName for everything
        public String pubDate;
        public int id;
        public String coremid;
        public String category;
        public String title;
        public String stitle;
        public String comments;
        public String description;
        public Enclosure enclosure;
        public Thumb thumb;
        @SerializedName("thumb_54x40")
        public Thumb thumb54x40;

        public static class Enclosure {
            @SerializedName("-url")
            public String url;
            @SerializedName("-type")
            public String type;
        }

        public static class Thumb {
            @SerializedName("-url")
            public String url;
            @SerializedName("-type")
            public String type;
        }

    }
}
