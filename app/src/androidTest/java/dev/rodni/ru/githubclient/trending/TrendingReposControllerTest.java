package dev.rodni.ru.githubclient.trending;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.AndroidJUnit4;

import com.bluelinelabs.conductor.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.data.TestRepoService;
import dev.rodni.ru.githubclient.test.ControllerTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class TrendingReposControllerTest extends ControllerTest {

    @Test
    public void loadRepos() {
        repoService.clearErrorFlags();
        launch();

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.repo_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(allOf(withId(R.id.tv_repo_name), withText("RxJava")))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void loadReposError() {
        repoService.setErrorFlags(TestRepoService.FLAG_TRENDING_REPOS);
        launch();

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.repo_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.tv_error))
                .check(matches(allOf(
                        withText(R.string.api_error_repos),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE
                        ))));
    }

    @Override
    public Controller controllerToLaunch() {
        return new TrendingReposController();
    }
}
