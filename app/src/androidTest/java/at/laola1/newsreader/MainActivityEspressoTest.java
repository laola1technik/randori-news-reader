package at.laola1.newsreader;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void shouldSetNewsItemTitle() {
        onView(withId(R.id.recyclerView)).check(matches(hasDescendant(withText("title"))));
    }
}
