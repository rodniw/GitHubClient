package dev.rodni.ru.githubclient.base;

import javax.inject.Singleton;

import dagger.Component;
import dev.rodni.ru.githubclient.data.RepoServiceModule;
import dev.rodni.ru.githubclient.networking.ServiceModule;

//singleton means to the end of the app lifecycle
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        RepoServiceModule.class,
})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);

}
