package dev.rodni.ru.githubclient.details;

import android.annotation.SuppressLint;

import javax.inject.Inject;
import javax.inject.Named;

import dev.rodni.ru.githubclient.data.RepoRepository;
import dev.rodni.ru.githubclient.di.ScreenScope;

@ScreenScope
class RepoDetailsPresenter {

    @SuppressLint("CheckResult" )
    @Inject
    public RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel
    ) {
        repoRepository.getRepo(repoOwner, repoName)
                .doOnSuccess(viewModel.processRepo())
                .doOnError(viewModel.detailsError())
                .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                        .doOnError(viewModel.contributorsError()))
                .subscribe(viewModel.processContributors(), throwable -> {
                    // We handle logging in the view model
                });
    }
}
