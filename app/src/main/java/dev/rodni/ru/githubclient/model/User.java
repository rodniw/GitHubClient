package dev.rodni.ru.githubclient.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

//i suppose that its much better to use Kotlin data class here
//but not today
//today i write in java
//By convention, AutoValue classes allow new instances by publishing a static method create.
//This method instantiates a new object and returns it in a transparent way.
//public static PersonData create(long id, String name, int status, String eMail,String profileUrl, String pictureImageUrl) {
//        return new AutoValue_PersonData(id, name, status, eMail, profileUrl, pictureImageUrl);
//    }
@AutoValue
public abstract class User {

    //remember that we can use Long instead of long to check for null
    //and in case of primitive type it can be simply 0
    public abstract Long id();

    public abstract String login();

    //its gonna be first $AutoValue_User class
    //this autogenerates things like json stuffs, hashcodes e t c(i mean toString equals hashcode methods)
    //then its gonna be AutoValue_User class
    //here we have methods like toJson and fromJson
    public static JsonAdapter<User> jsonAdapter(Moshi moshi) {
        return new AutoValue_User.MoshiJsonAdapter(moshi);
    }

}

//Remember that AutoValue classes are generated in build time,
//so you must rebuild your project every time we change something in the definitions.
