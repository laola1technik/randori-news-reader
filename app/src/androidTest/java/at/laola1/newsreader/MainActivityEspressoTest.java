package at.laola1.newsreader;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import at.laola1.newsreader.overview.NewsItemViewModel;
import at.laola1.newsreader.overview.NewsOverviewActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    // https://stackoverflow.com/questions/31394569/how-to-assert-inside-a-recyclerview-in-espresso
    @Rule
    public ActivityTestRule<NewsOverviewActivity> mActivityRule =
            new ActivityTestRule<>(NewsOverviewActivity.class);

    @Test
    public void shouldSetNewsItemTitle() throws Throwable {
        setNewsItems(generateNewsItemList("title1"));

        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("title1"))));
    }

    private void setNewsItems(final List<NewsItemViewModel> newsItems) throws Throwable {
        mActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().setNewsItems(newsItems);
            }
        });
    }

    @Test
    public void shouldSetMultipleNewsItemTitles() throws Throwable {
        setNewsItems(generateNewsItemList("title1", "title2"));

        onView(withId(R.id.recyclerView)).check(matches(Matchers.allOf(
                hasDescendant(withText("title1")),
                hasDescendant(withText("title2"))
        )));
    }

    @NonNull
    private List<NewsItemViewModel> generateNewsItemList(String ... titles) {
        List<NewsItemViewModel> newsItems = new ArrayList<>();
        for (String title : titles) {
            NewsItemViewModel itemModel = generateNewsItem(title);
            newsItems.add(itemModel);
        }
        return newsItems;
    }

    @NonNull
    private NewsItemViewModel generateNewsItem(String title) {
        NewsItemViewModel model = new NewsItemViewModel();
        model.setTitle(title);
        return model;
    }
}
