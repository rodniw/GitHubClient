package dev.rodni.ru.githubclient.base;

import javax.inject.Singleton;

import dagger.Component;

//singleton means to the end of the app lifecycle
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

}
