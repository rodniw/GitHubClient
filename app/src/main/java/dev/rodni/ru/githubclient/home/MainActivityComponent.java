package dev.rodni.ru.githubclient.home;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dev.rodni.ru.githubclient.di.ActivityScope;
import dev.rodni.ru.githubclient.ui.ActivityViewInterceptorModule;
import dev.rodni.ru.githubclient.ui.NavigationModule;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
        ActivityViewInterceptorModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
        //this required mainly for the activities to prevent all the memory leaks
        //for the team work it would be better to create base class not to forget to override seedInstance
        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
