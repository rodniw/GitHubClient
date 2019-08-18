package dev.rodni.ru.githubclient.zzz;

import dagger.Provides;
import dagger.Module;

//the module to provide release activity interceptor
@Module
public abstract class ActivityViewInterceptorModule {

    //provide default impl of the act view inter
    @Provides
    static ActivityViewInterceptor provideActivityViewInterceptor() {
        return ActivityViewInterceptor.DEFAULT;
    }
}
