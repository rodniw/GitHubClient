package dev.rodni.ru.githubclient.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.details.RepoDetailsController;
import dev.rodni.ru.githubclient.di.ActivityScope;

@ActivityScope
public class DefaultScreenNavigator implements ScreenNavigator {

    private Router router;

    @Inject
    public DefaultScreenNavigator() {

    }

    //SOLID
    //if its the first time we need it to give a root screen
    //in rotation that condition will be false so we will not execute transaction again
    @Override
    public void initWithRouter(Router router, Controller rootScreen) {
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
    public void clear() {
        router = null;
    }
}
