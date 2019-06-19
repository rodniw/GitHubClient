package dev.rodni.ru.githubclient.trending;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import dev.rodni.ru.githubclient.base.BaseController;

public class TrendingReposController extends BaseController {

    //we do not need this anymore after including seedInstance into MainActivityComponent
    //@Inject MainActivity mainActivity;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return null;
    }
}
