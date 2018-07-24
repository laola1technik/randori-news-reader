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
}
