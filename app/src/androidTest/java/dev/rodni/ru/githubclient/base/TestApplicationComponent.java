package dev.rodni.ru.githubclient.base;

import javax.inject.Singleton;

import dagger.Component;
import dev.rodni.ru.githubclient.data.RepoRepository;
import dev.rodni.ru.githubclient.data.TestRepoService;
import dev.rodni.ru.githubclient.data.TestRepoServiceModule;
import dev.rodni.ru.githubclient.networking.ServiceModule;
import dev.rodni.ru.githubclient.trending.TrendingReposControllerTest;
import dev.rodni.ru.githubclient.ui.NavigationModule;
import dev.rodni.ru.githubclient.ui.TestActivityViewInterceptorModule;
import dev.rodni.ru.githubclient.ui.TestNavigationModule;
import dev.rodni.ru.githubclient.ui.TestScreenNavigator;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        TestNavigationModule.class,
        TestActivityViewInterceptorModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(TrendingReposControllerTest trendingReposControllerTest);

    TestScreenNavigator screenNavigator();

    TestRepoService repoService();

    RepoRepository repoRepository();
}
