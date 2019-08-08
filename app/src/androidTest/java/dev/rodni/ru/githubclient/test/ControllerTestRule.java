package dev.rodni.ru.githubclient.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import dev.rodni.ru.githubclient.base.TestApplication;
import dev.rodni.ru.githubclient.data.RepoRepository;
import dev.rodni.ru.githubclient.data.TestRepoService;
import dev.rodni.ru.githubclient.ui.TestScreenNavigator;


public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestRepoService repoService;
    public final RepoRepository repoRepository;

    public ControllerTestRule(Class<T> activityClass) {
        super(activityClass, true, false);
        screenNavigator = TestApplication.getComponent().screenNavigator();
        repoService = TestApplication.getComponent().repoService();
        repoRepository = TestApplication.getComponent().repoRepository();
    }

    public void clearState() {
        repoService.clearErrorFlags();
        repoService.clearHoldFlags();
        repoRepository.clearCache();
    }
}
