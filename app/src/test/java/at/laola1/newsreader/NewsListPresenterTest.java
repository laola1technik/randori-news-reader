package at.laola1.newsreader;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import at.laola1.newsreader.feed.model.NewsFeed;
import at.laola1.newsreader.overview.NewsItemViewModel;
import at.laola1.newsreader.overview.NewsOverviewPresenter;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewsListPresenterTest {
    @Test
    public void shouldGiveEmptyListForEmptyNewsItem() {
        NewsFeed newsFeed = new NewsFeed();
        NewsOverviewPresenter presenter = new NewsOverviewPresenter();

        List<NewsItemViewModel> viewModels = presenter.parse(newsFeed);

        assertTrue(viewModels.isEmpty());
    }

    @Test
    public void shouldGiveEmptyListForNewsItemWithoutTitle() {
        NewsFeed.NewsItem item = new NewsFeed.NewsItem();
        item.thumb54x40 = new NewsFeed.NewsItem.Thumb();
        item.thumb54x40.url = "https://www.laola1.at/images/redaktion/images/Fussball/2-Liga/FAC/oberhammer-fac-trainer_dd543_f_603x340.jpg";
        NewsFeed newsFeed = new NewsFeed();
        newsFeed.add(item);
        NewsOverviewPresenter presenter = new NewsOverviewPresenter();

        List<NewsItemViewModel> viewModels = presenter.parse(newsFeed);

        assertTrue(viewModels.isEmpty());
    }

    @Test
    public void shouldGiveEmptyListForNewsItemWithoutImage() {
        NewsFeed.NewsItem item = new NewsFeed.NewsItem();
        item.stitle = "FAK fixiert Eröffnungs-Kracher";
        NewsFeed newsFeed = new NewsFeed();
        newsFeed.add(item);
        NewsOverviewPresenter presenter = new NewsOverviewPresenter();

        List<NewsItemViewModel> viewModels = presenter.parse(newsFeed);

        assertTrue(viewModels.isEmpty());
    }

    @Test
    public void shouldGiveListWithOneValidNewsItem() {
        NewsFeed.NewsItem item = new NewsFeed.NewsItem();
        item.stitle = "FAK fixiert Eröffnungs-Kracher";
        item.thumb54x40 = new NewsFeed.NewsItem.Thumb();
        item.thumb54x40.url = "https://www.laola1.at/images/redaktion/images/Fussball/2-Liga/FAC/oberhammer-fac-trainer_dd543_f_603x340.jpg";

        NewsFeed newsFeed = new NewsFeed();
        newsFeed.add(item);
        NewsOverviewPresenter presenter = new NewsOverviewPresenter();

        List<NewsItemViewModel> viewModels = presenter.parse(newsFeed);

        assertEquals(item.thumb54x40.url, viewModels.get(0).getImageUrl());
        assertEquals(item.stitle, viewModels.get(0).getTitle());
    }

    @Test
    public void shouldParseSingleNewsItem() {
        NewsFeed.NewsItem item = new NewsFeed.NewsItem();
        item.stitle = "FAK fixiert Eröffnungs-Kracher";
        item.thumb54x40 = new NewsFeed.NewsItem.Thumb();
        item.thumb54x40.url = "https://www.laola1.at/images/redaktion/images/Fussball/2-Liga/FAC/oberhammer-fac-trainer_dd543_f_603x340.jpg";

        NewsOverviewPresenter presenter = new NewsOverviewPresenter();

        NewsItemViewModel viewModel = presenter.parseItem(item);

        assertEquals(item.thumb54x40.url, viewModel.getImageUrl());
        assertEquals(item.stitle, viewModel.getTitle());
    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldFailIfNewsItemIsInvalid() {
        NewsFeed.NewsItem item = new NewsFeed.NewsItem();
        item.stitle = "FAK fixiert Eröffnungs-Kracher";
        NewsOverviewPresenter presenter = new NewsOverviewPresenter();

        presenter.parseItem(item);
    }
}
