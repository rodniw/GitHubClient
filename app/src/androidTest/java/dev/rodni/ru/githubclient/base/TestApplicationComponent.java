package dev.rodni.ru.githubclient.base;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {
}
