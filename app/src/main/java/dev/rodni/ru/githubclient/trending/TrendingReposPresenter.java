package dev.rodni.ru.githubclient.trending;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.data.RepoRequester;
import dev.rodni.ru.githubclient.di.ScreenScope;
import dev.rodni.ru.githubclient.model.Repo;

@ScreenScope
public class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

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

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
