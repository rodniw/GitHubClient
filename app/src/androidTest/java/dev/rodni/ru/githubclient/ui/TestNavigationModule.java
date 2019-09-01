package dev.rodni.ru.githubclient.ui;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import dev.rodni.ru.githubclient.lifecycle.ActivityLifecycleTask;

@Module
public abstract class TestNavigationModule {
    @Binds
    abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(TestScreenNavigator screenNavigator);
}

