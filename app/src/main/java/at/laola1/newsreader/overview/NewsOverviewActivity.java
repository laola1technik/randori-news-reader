package at.laola1.newsreader.overview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.laola1.newsreader.R;

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
        List<NewsItemViewModel> newsItems = getNewsItems();
        setNewsItems(newsItems);
    }

    public void setNewsItems(List<NewsItemViewModel> newsItems) {
        adapter.setNewsItems(newsItems);
    }


    @NonNull
    private List<NewsItemViewModel> getNewsItems() {
        List<NewsItemViewModel> newsItems = new ArrayList<>();
        NewsItemViewModel itemModel = new NewsItemViewModel();
        itemModel.setTitle("title1");
        itemModel.setImageUrl("https://www.laola1.at/images/redaktion/images/Fussball/Bundesliga/Rapid/korkmaz-karriere_eed54_f_940x529.jpg");
        //newsItems.add(itemModel);
        NewsItemViewModel itemModel2 = new NewsItemViewModel();
        itemModel2.setTitle("title2");
        itemModel2.setImageUrl("https://www.laola1.at/images/redaktion/images/Fussball/Bundesliga/Rapid/korkmaz-karriere_eed54_f_940x529.jpg");
        //newsItems.add(itemModel2);
        return newsItems;
    }


}
