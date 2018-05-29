package at.laola1.newsreader.overview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import at.laola1.newsreader.R;
import at.laola1.newsreader.feed.DownloaderAsyncTask;
import at.laola1.newsreader.feed.FinishedDownloadCallback;

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
        setNewsItems(getNewsItems());
    }

    @NonNull
    private List<NewsItemViewModel> getNewsItems() {
        String content = "";
        try {
            new DownloaderAsyncTask(new FinishedDownloadCallback() {
                @Override
                public void onDownloadFinish(String response) {

                }

            }).execute(new URL("http://appsdata.laola1.at/data/probetag/news.json"));
        } catch (IOException e) {
            e.printStackTrace(); // TODO PK Error Handling
        }
        List<NewsItemViewModel> newsItems = new ArrayList<>();
        NewsItemViewModel itemModel = new NewsItemViewModel();
        itemModel.setTitle(content);
        itemModel.setImageUrl("https://www.laola1.at/images/redaktion/images/Fussball/Bundesliga/Rapid/korkmaz-karriere_eed54_f_940x529.jpg");
        newsItems.add(itemModel);

        NewsItemViewModel itemModel2 = new NewsItemViewModel();
        itemModel2.setTitle("title2");
        itemModel2.setImageUrl("https://www.laola1.at/images/redaktion/images/Fussball/Bundesliga/Rapid/korkmaz-karriere_eed54_f_940x529.jpg");
        //newsItems.add(itemModel2);
        return newsItems;
    }

    public /* for testing */ void setNewsItems(List<NewsItemViewModel> newsItems) {
        adapter.setNewsItems(newsItems);
    }
}
