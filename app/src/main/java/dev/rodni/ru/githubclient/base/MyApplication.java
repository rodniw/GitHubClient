package dev.rodni.ru.githubclient.base;

import android.app.Application;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.di.ActivityInjector;

public class MyApplication extends Application {

    @Inject
    ActivityInjector activityInjector;

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        //here we have dependencies for our application
        //dagger start building dependencies graph
        component = initComponent();
        //and by this methos we can get our provider for activity injector if i understand that right
        //from dagger build classes
        component.inject(this);

    }

    protected ApplicationComponent initComponent() {

        //so we have all our activities and screens put into a map inside DaggerApplicationComponent
        //and when we need some of them we simply ask for them and not create them at the exact time
        //our DaggerApplicationComponent knows about all them at the time of the app creation
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        //also i need to say that we do not keep Builder inside DaggerApplicationComponent
        //we need it only at the building time and then we can throw it away
        //first of all because we want to initialize our dependencies only once(not for a few time)
        //second we want to prevent memory leaks

        //do not forget to injectr our Activity into our Controller
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }

}