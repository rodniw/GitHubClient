package dev.rodni.ru.githubclient.ui;

import dagger.Binds;
import dagger.Module;
import dev.rodni.ru.githubclient.di.ActivityScope;
//import dagger.Provides;

@Module
public abstract class NavigationModule {

    @Binds
    @ActivityScope
    abstract ScreenNavigator prodiveScreenNavigator(DefaultScreenNavigator defaultScreenNavigator);
}
