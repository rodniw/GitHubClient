package dev.rodni.ru.githubclient.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dev.rodni.ru.githubclient.di.ActivityScope;

@ActivityScope
@Subcomponent
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}
