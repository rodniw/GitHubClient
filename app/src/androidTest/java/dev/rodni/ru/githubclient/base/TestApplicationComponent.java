package dev.rodni.ru.githubclient.base;

import javax.inject.Singleton;

import dagger.Component;
import dev.rodni.ru.githubclient.data.TestRepoServiceModule;
import dev.rodni.ru.githubclient.networking.ServiceModule;
import dev.rodni.ru.githubclient.ui.NavigationModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {
}
