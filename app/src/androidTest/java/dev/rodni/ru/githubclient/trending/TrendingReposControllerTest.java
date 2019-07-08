package dev.rodni.ru.githubclient.trending;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.data.TestRepoService;
import dev.rodni.ru.githubclient.home.MainActivity;

@RunWith(AndroidJUnit4.class)
public class TrendingReposControllerTest {

    @Inject
    TestRepoService repoService;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false
    );
}
