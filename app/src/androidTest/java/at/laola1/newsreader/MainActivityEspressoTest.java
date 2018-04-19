package at.laola1.newsreader;

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
        mActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<NewsItemViewModel> newsItems = new ArrayList<>();
                NewsItemViewModel itemModel = new NewsItemViewModel();
                itemModel.setTitle("title1");
                itemModel.setImageUrl("https://www.laola1.at/images/redaktion/images/Fussball/Bundesliga/Rapid/korkmaz-karriere_eed54_f_940x529.jpg");
                newsItems.add(itemModel);

                mActivityRule.getActivity().setNewsItems(newsItems);
            }
        });

        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("title1"))));
    }

    @Test
    public void shouldSetMultipleNewsItemTitles() throws Throwable {
        mActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<NewsItemViewModel> newsItems = new ArrayList<>();
                NewsItemViewModel itemModel = new NewsItemViewModel();
                itemModel.setTitle("title1");
                itemModel.setImageUrl("https://www.laola1.at/images/redaktion/images/Fussball/Bundesliga/Rapid/korkmaz-karriere_eed54_f_940x529.jpg");
                newsItems.add(itemModel);
                NewsItemViewModel itemModel2 = new NewsItemViewModel();
                itemModel2.setTitle("title2");
                itemModel2.setImageUrl("https://www.laola1.at/images/redaktion/images/Fussball/Bundesliga/Rapid/korkmaz-karriere_eed54_f_940x529.jpg");
                newsItems.add(itemModel2);

                mActivityRule.getActivity().setNewsItems(newsItems);
            }
        });

        onView(withId(R.id.recyclerView)).check(matches(Matchers.allOf(
                hasDescendant(withText("title1")),
                hasDescendant(withText("title2"))
        )));
    }
}
