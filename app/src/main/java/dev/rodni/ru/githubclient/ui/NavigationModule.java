package dev.rodni.ru.githubclient.ui;

import dagger.Binds;
import dagger.Module;
//import dagger.Provides;

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator prodiveScreenNavigator(DefaultScreenNavigator defaultScreenNavigator);
}
