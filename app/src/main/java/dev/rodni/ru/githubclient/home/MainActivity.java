package dev.rodni.ru.githubclient.home;

import com.bluelinelabs.conductor.Controller;

import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.base.BaseActivity;
import dev.rodni.ru.githubclient.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}
