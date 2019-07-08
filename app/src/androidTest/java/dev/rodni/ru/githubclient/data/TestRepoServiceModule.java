package dev.rodni.ru.githubclient.data;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestRepoServiceModule {

    @Binds
    abstract RepoService provideRepoService(TestRepoService testRepoService);
}
