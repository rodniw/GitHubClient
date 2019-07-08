package dev.rodni.ru.githubclient.base;

public class TestApplication extends MyApplication {

    @Override
    protected ApplicationComponent initComponent() {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
