package dev.rodni.ru.githubclient.ui;

import dagger.Binds;
import dagger.Module;

//the module to provide debug activity interceptor
@Module
public abstract class ActivityViewInterceptorModule {

    //when activityViewInterceptor injected then debugActivityViewInterceptor provided
    @Binds
    abstract ActivityViewInterceptor bindDebugActivityViewInterceptor(DebugActivityViewInterceptor activityViewInterceptor);
}
