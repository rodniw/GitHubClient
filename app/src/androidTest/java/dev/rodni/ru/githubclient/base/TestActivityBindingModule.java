package dev.rodni.ru.githubclient.base;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import dev.rodni.ru.githubclient.home.MainActivity;
import dev.rodni.ru.githubclient.home.TestMainActivityComponent;

@Module(subcomponents = {
        TestMainActivityComponent.class,
})
public abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjector(TestMainActivityComponent.Builder builder);

}
