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
        component = initComponent();
        component.inject(this);

    }

    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }

}
