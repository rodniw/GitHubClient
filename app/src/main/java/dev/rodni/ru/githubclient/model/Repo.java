package dev.rodni.ru.githubclient.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

//special api for android versions less than 26
import org.threeten.bp.ZonedDateTime;

@AutoValue
public abstract class Repo {

    public abstract long id();

    public abstract String name();

    public abstract String description();

    public abstract User owner();

    //of course it can be much more right here
    //but i suppose thats enough for right now

    //how many people starred it
    @Json(name = "stargazers_count")
    public abstract long stargazersCount();

    //how many people forked it
    @Json(name = "forks_count")
    public abstract long forksCount();

    //all the contributors for the specific repo
    @Json(name = "contributors_url")
    public abstract String contributorsUrl();

    //created date
    @Json(name = "created_at")
    public abstract ZonedDateTime createdDate();

    //updated date
    @Json(name = "updated_at")
    public abstract ZonedDateTime updatedDate();

    /*@Override
    public String renderKey() {
        return "Repo";
    }

    @Override
    public long getId() {
        return id();
    }*/

    public static JsonAdapter<Repo> jsonAdapter(Moshi moshi) {
        return new AutoValue_Repo.MoshiJsonAdapter(moshi);
    }
}
