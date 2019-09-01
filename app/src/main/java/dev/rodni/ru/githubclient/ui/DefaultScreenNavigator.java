package dev.rodni.ru.githubclient.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.details.RepoDetailsController;
import dev.rodni.ru.githubclient.di.ActivityScope;
import dev.rodni.ru.githubclient.lifecycle.ActivityLifecycleTask;

//scope moved here from the module to satisfy two deps
@ActivityScope
public class DefaultScreenNavigator extends ActivityLifecycleTask implements ScreenNavigator {

    private Router router;

    @Inject
    public DefaultScreenNavigator() {

    }

    //in the oncreate i check that an activity implements router provider interface
    @Override
    public void onCreate(AppCompatActivity activity) {
        if (!(activity instanceof RouterProvider)) {
            throw new IllegalArgumentException("Activity must implements RouterProvider interface");
        }
        initWithRouter(((RouterProvider) activity).getRouter(), ((RouterProvider) activity).initialScreen());
    }

    //SOLID
    //if its the first time we need it to give a root screen
    //in rotation that condition will be false so we will not execute transaction again
    void initWithRouter(Router router, Controller rootScreen) {
        this.router = router;
        //this condition
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public boolean pop() {
        return router != null && router.handleBack();
    }

    @Override
    public void goToRepoDetails(String repoOwner, String repoName) {
        if (router != null) {
            router.pushController(RouterTransaction.with(RepoDetailsController.newInstance(repoName, repoOwner))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        router = null;
    }
}
