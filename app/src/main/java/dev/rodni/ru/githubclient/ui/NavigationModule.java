package dev.rodni.ru.githubclient.ui;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import dev.rodni.ru.githubclient.di.ActivityScope;
import dev.rodni.ru.githubclient.lifecycle.ActivityLifecycleTask;
//import dagger.Provides;

@Module
public abstract class NavigationModule {

    //@ActivityScope moved to the class
    @Binds
    abstract ScreenNavigator prodiveScreenNavigator(DefaultScreenNavigator defaultScreenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(DefaultScreenNavigator defaultScreenNavigator);
}
