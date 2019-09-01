package dev.rodni.ru.githubclient.ui;

public interface ScreenNavigator {

    boolean pop();

    void goToRepoDetails(String repoOwner, String repoName);
}
