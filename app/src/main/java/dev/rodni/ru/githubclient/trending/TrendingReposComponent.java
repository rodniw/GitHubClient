package dev.rodni.ru.githubclient.trending;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dev.rodni.ru.githubclient.base.ScreenModule;
import dev.rodni.ru.githubclient.di.ScreenScope;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
})
public interface TrendingReposComponent extends AndroidInjector<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {

    }
}
