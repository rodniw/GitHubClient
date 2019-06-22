package dev.rodni.ru.githubclient.trending;

import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.base.BaseController;

public class TrendingReposController extends BaseController {
    //we do not need this anymore after including seedInstance into MainActivityComponent
    //@Inject MainActivity mainActivity;

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }
}
