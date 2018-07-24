package at.laola1.newsreader.overview;

import java.util.ArrayList;
import java.util.List;

import at.laola1.newsreader.feed.model.NewsFeed;

public class NewsOverviewPresenter {

    public List<NewsItemViewModel> parse(NewsFeed newsFeed) {

        List<NewsItemViewModel> viewModels = new ArrayList<>();

        for(NewsFeed.NewsItem item : newsFeed){
            viewModels.add(parseItem(item));
        }

        return viewModels;
    }

    public NewsItemViewModel parseItem(NewsFeed.NewsItem item) {
        NewsItemViewModel viewModel = new NewsItemViewModel();
        viewModel.setTitle(item.stitle);
        viewModel.setImageUrl(item.thumb54x40.url);

        return viewModel;
    }
}
