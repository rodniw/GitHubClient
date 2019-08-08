package dev.rodni.ru.githubclient.ui;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestNavigationModule {
    @Binds
    abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator screenNavigator);
}
