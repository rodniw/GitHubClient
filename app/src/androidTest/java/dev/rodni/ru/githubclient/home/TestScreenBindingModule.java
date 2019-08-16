package dev.rodni.ru.githubclient.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import dev.rodni.ru.githubclient.details.RepoDetailsComponent;
import dev.rodni.ru.githubclient.details.RepoDetailsController;
import dev.rodni.ru.githubclient.di.ControllerKey;
import dev.rodni.ru.githubclient.trending.TrendingReposComponent;
import dev.rodni.ru.githubclient.trending.TrendingReposController;

@Module(subcomponents = {
        TrendingReposComponent.class,
        RepoDetailsComponent.class,
})
public abstract class TestScreenBindingModule {

    //binding all the repos screen into our activity
    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);

    //binding repo details screen
    @Binds
    @IntoMap
    @ControllerKey(RepoDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(RepoDetailsComponent.Builder builder);

}
