package at.laola1.newsreader.overview;

import java.util.ArrayList;
import java.util.List;

import at.laola1.newsreader.feed.model.NewsFeed;

public class NewsOverviewPresenter {

    public List<NewsItemViewModel> parse(NewsFeed newsFeed) {

        List<NewsItemViewModel> viewModels = new ArrayList<>();

        for (NewsFeed.NewsItem item : newsFeed) {
           try{
               NewsItemViewModel news = parseItem(item);
               viewModels.add(news);
           } catch (IllegalArgumentException e){
               // Todo: handle error
           }
        }

        return viewModels;
    }

    public NewsItemViewModel parseItem(NewsFeed.NewsItem item) {
        if (item.stitle == null || item.stitle.isEmpty()) {
            throw new IllegalArgumentException("Title not set.");
        }

        if (item.thumb == null || item.thumb.url == null || item.thumb.url.isEmpty()) {
            throw new IllegalArgumentException("Image Url not set.");
        }

        NewsItemViewModel viewModel = new NewsItemViewModel();
        viewModel.setTitle(item.stitle);
        viewModel.setImageUrl(item.thumb.url);

        return viewModel;
    }
}
