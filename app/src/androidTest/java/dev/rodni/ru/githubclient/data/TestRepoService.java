package dev.rodni.ru.githubclient.data;

import java.util.List;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.model.Contributor;
import dev.rodni.ru.githubclient.model.Repo;
import io.reactivex.Single;

public class TestRepoService implements RepoService {

    @Inject
    public TestRepoService() {

    }

    @Override
    public Single<TrendingReposResponse> getTrendingRepos() {
        return null;
    }

    @Override
    public Single<Repo> getRepo(String repoOwner, String repoName) {
        return null;
    }

    @Override
    public Single<List<Contributor>> getContributors(String url) {
        return null;
    }
}
