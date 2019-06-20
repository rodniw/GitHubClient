package dev.rodni.ru.githubclient.data;

import java.util.List;

import dev.rodni.ru.githubclient.model.Contributor;
import dev.rodni.ru.githubclient.model.Repo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

//just classical Retrofit service
public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repos/{owner}/{name}")
    Single<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);

    @GET
    Single<List<Contributor>> getContributors(@Url String url);

}
