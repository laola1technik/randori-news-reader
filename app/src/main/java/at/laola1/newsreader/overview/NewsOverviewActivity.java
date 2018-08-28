package at.laola1.newsreader.overview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import at.laola1.newsreader.InvalidFeedException;
import at.laola1.newsreader.R;
import at.laola1.newsreader.feed.DownloaderAsyncTask;
import at.laola1.newsreader.feed.FinishedDownloadCallback;
import at.laola1.newsreader.feed.model.NewsFeed;
import at.laola1.newsreader.feed.parser.NewsFeedParser;

public class NewsOverviewActivity extends AppCompatActivity {

    private NewsOverviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        loadContent();
    }

    private void initializeView() {
        setContentView(R.layout.activity_news_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NewsOverviewAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void loadContent() {
        try {
            new DownloaderAsyncTask(new FinishedDownloadCallback() {
                @Override
                public void onDownloadFinish(String response) {
                    bindNews(response);
                }

            }).execute(new URL("http://appsdata.laola1.at/data/probetag/news.json"));
        } catch (IOException e) {
            e.printStackTrace(); // TODO PK Error Handling
        }
    }

    public /* for testing */ void setNewsItems(List<NewsItemViewModel> newsItems) {
        adapter.setNewsItems(newsItems);
    }

    private void bindNews(String response) {
        NewsFeedParser parser = new NewsFeedParser(response);
        try {
            NewsFeed feed = parser.parse();

            NewsOverviewPresenter presenter = new NewsOverviewPresenter();
            List<NewsItemViewModel> viewModels = presenter.parse(feed);

            setNewsItems(viewModels);

        } catch (InvalidFeedException e) {
            e.printStackTrace(); // TODO PK Error Handling
        }
    }
}
