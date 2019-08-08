package dev.rodni.ru.githubclient.trending;

import android.annotation.SuppressLint;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.data.RepoRepository;
import dev.rodni.ru.githubclient.di.ScreenScope;
import dev.rodni.ru.githubclient.model.Repo;
import dev.rodni.ru.githubclient.ui.ScreenNavigator;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener {

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private final ScreenNavigator screenNavigator;

    @Inject
    TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        loadRepos();
    }

    @SuppressLint("CheckResult")
    private void loadRepos() {
        repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
