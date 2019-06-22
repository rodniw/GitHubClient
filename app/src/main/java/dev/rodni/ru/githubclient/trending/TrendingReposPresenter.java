package dev.rodni.ru.githubclient.trending;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.data.RepoRequester;
import dev.rodni.ru.githubclient.di.ScreenScope;

@ScreenScope
public class TrendingReposPresenter {

    private final TrendingReposViewModel mViewModel;
    private final RepoRequester mRepoRequester;

    @Inject
    public TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
        mViewModel = viewModel;
        mRepoRequester = repoRequester;
        loadRepos();
    }

    @SuppressLint("CheckResult")
    private void loadRepos() {
        mRepoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> mViewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> mViewModel.loadingUpdated().accept(false))
                .subscribe(mViewModel.reposUpdated(), mViewModel.onError());
    }
}
