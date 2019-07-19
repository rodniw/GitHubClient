package dev.rodni.ru.githubclient.data;

import java.util.List;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.model.Contributor;
import dev.rodni.ru.githubclient.model.Repo;
import io.reactivex.Single;

public class RepoRequester {

    private final RepoService service;

    @Inject
    RepoRequester(RepoService service) {
        this.service = service;
    }

    Single<List<Repo>> getTrendingRepos() {
        return service.getTrendingRepos()
                .map(TrendingReposResponse::repos);
        //now its all about repository work
                //.subscribeOn(Schedulers.io());
    }

    Single<Repo> getRepo(String repoOwner, String repoName) {
        return service.getRepo(repoOwner, repoName);
        //now its all about repository work
                //.subscribeOn(Schedulers.io());
    }

    Single<List<Contributor>> getContributors(String url) {
        return service.getContributors(url);
    }
}
