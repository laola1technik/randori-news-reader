package at.laola1.newsreader;

import org.junit.Test;

import java.util.List;

import at.laola1.newsreader.feed.model.NewsFeed;
import at.laola1.newsreader.overview.NewsItemViewModel;
import at.laola1.newsreader.overview.NewsOverviewPresenter;

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
}
