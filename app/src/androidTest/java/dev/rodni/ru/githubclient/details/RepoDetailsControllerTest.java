package dev.rodni.ru.githubclient.details;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.bluelinelabs.conductor.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.data.TestRepoService;
import dev.rodni.ru.githubclient.test.ControllerTest;

//TODO: refactor test. They are failed because of those settings.
//test runner class
@RunWith(AndroidJUnit4.class)
public class RepoDetailsControllerTest extends ControllerTest {

    //we work with repo from test/mock/repos/get_repo.json

    //we have access to the testRule from our ControllerTest class
    @Before
    public void setUp() {
        testRule.clearState();
    }

    //test repo success case
    @Test
    public void RepoDetailsSuccess() {
        //launch the activity and out intent should be null
        launch();
        //getting new instance of the RepoDetailsRobot and start all our verifications
        RepoDetailsRobot.init()
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyName("RxJava")
                .verifyDescription("RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.")
                .verifyCreationDate("Jan 08, 2013")
                .verifyUpdatedDate("Oct 06, 2017");
    }

    //test repo error case
    @Test
    public void repoDetailsError() {
        repoService.setErrorFlags(TestRepoService.FLAG_GET_REPO);
        launch();
        RepoDetailsRobot.init()
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContentVisibility(ViewMatchers.Visibility.GONE)
                .verifyErrorText(R.string.api_error_single_repo);
    }

    //test contributors success
    @Test
    public void contributorsSuccess() {
        launch();
        RepoDetailsRobot.init()
                .verifyContributorsLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsErrorVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorShown("benjchristensen");
    }

    //test contributors error
    @Test
    public void contributorsError() {
        repoService.setErrorFlags(TestRepoService.FLAG_GET_CONTRIBUTORS);
        launch();
        RepoDetailsRobot.init()
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsErrorText(R.string.api_error_contributors);
    }

    //test case when repo success but contributors has an error
    @Test
    public void repoSuccessContributorsError() {
        repoService.setErrorFlags(TestRepoService.FLAG_GET_CONTRIBUTORS);
        launch();
        RepoDetailsRobot.init()
                .verifyLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsLoadingVisibility(ViewMatchers.Visibility.GONE)
                .verifyContributorsErrorText(R.string.api_error_contributors)
                .verifyErrorVisibility(ViewMatchers.Visibility.GONE);
    }

    //test ui when loading repo
    @Test
    public void loadingRepo() {
        repoService.setHoldFlags(TestRepoService.FLAG_GET_REPO);
        launch();
        RepoDetailsRobot.init()
                .verifyLoadingVisibility(ViewMatchers.Visibility.VISIBLE);
    }

    //test ui when loading contributors
    @Test
    public void loadingContributors() {
        repoService.setHoldFlags(TestRepoService.FLAG_GET_CONTRIBUTORS);
        launch();
        RepoDetailsRobot.init().
                verifyContributorsLoadingVisibility(ViewMatchers.Visibility.VISIBLE);
    }

    //creation of the new instance of the details controller
    @Override
    public Controller controllerToLaunch() {
        return RepoDetailsController.newInstance("ReactiveX", "RxJava");
    }
}
