package dev.rodni.ru.githubclient.test;

import android.content.Intent;

import com.bluelinelabs.conductor.Controller;

import org.junit.Rule;

import dev.rodni.ru.githubclient.data.RepoRepository;
import dev.rodni.ru.githubclient.data.TestRepoService;
import dev.rodni.ru.githubclient.home.MainActivity;
import dev.rodni.ru.githubclient.ui.TestScreenNavigator;

public abstract class ControllerTest {
    @Rule
    public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

    protected TestScreenNavigator screenNavigator = testRule.screenNavigator;
    protected TestRepoService repoService = testRule.repoService;
    protected RepoRepository repoRepository = testRule.repoRepository;

    public ControllerTest() {
        screenNavigator.overrideInitialController(controllerToLaunch());
    }

    public abstract Controller controllerToLaunch();

    protected void launch() {
        launch(null);
    }

    protected void launch(Intent intent) {
        testRule.launchActivity(intent);
    }
}
