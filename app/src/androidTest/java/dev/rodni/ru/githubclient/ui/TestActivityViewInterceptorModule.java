package dev.rodni.ru.githubclient.ui;

import dagger.Module;
import dagger.Provides;

//module to set activity interceptor dependency
@Module
public abstract class TestActivityViewInterceptorModule {

    //provides the default implementation
    @Provides
    static ActivityViewInterceptor provideActivityInterceptor() {
        return ActivityViewInterceptor.DEFAULT;
    }
}
