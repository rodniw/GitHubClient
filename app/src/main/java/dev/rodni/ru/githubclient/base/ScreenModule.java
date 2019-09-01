package dev.rodni.ru.githubclient.base;

import java.util.Set;

import dagger.Module;
import dagger.multibindings.Multibinds;
import dev.rodni.ru.githubclient.lifecycle.ScreenLifecycleTask;

@Module
public abstract class ScreenModule {

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
