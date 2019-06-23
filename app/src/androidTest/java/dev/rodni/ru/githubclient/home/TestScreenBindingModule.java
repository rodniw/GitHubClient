package dev.rodni.ru.githubclient.home;

import com.bluelinelabs.conductor.Controller;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import dev.rodni.ru.githubclient.di.ControllerKey;
import dev.rodni.ru.githubclient.trending.TrendingReposComponent;
import dev.rodni.ru.githubclient.trending.TrendingReposController;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder builder);

}
