package dev.rodni.ru.githubclient.details;

import android.support.test.espresso.matcher.ViewMatchers;

import dev.rodni.ru.githubclient.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

//i need it to be package private
class RepoDetailsRobot {

    //static method that provides an instance of the RepoDetailsRobot
    static RepoDetailsRobot init() {
        return new RepoDetailsRobot();
    }

    private RepoDetailsRobot() {

    }

    //method that checking(with Espresso) is the name text view matches the string that ve been passed through
    RepoDetailsRobot verifyName(String name) {
        onView(withId(R.id.tv_repo_name)).check(matches(withText(name)));
        return this;
    }

    //method that checking is the desctiption text view matches the string that ve been passed through
    RepoDetailsRobot verifyDescription(String description) {
        onView(withId(R.id.tv_repo_description)).check(matches(withText(description)));
        return this;
    }

    //method that will verify creation date text view
    RepoDetailsRobot verifyCreationDate(String creationDate) {
        onView(withId(R.id.tv_creation_date)).check(matches(withText(creationDate)));
        return this;
    }

    //verify updated date
    RepoDetailsRobot verifyUpdatedDate(String updatedDate) {
        onView(withId(R.id.tv_updated_date)).check(matches(withText(updatedDate)));
        return this;
    }

    //verify error text
    //first we verify if we have some error's text
    RepoDetailsRobot verifyErrorText(Integer textRes) {
        if (textRes == null) {
            onView(withId(R.id.tv_error)).check(matches(withText("")));
        } else {
            onView(withId(R.id.tv_error)).check(matches(withText(textRes)));
        }
        return this;
    }

    //verify error visibility
    RepoDetailsRobot verifyErrorVisibility(ViewMatchers.Visibility visibility) {
        onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(visibility)));
        return this;
    }

    //verify contributors error text
    //first we verify if we have some error's text
    RepoDetailsRobot verifyContributorsErrorText(Integer textRes) {
        if (textRes == null) {
            onView(withText(R.id.tv_contributors_error)).check(matches(withText("")));
        } else {
            onView(withId(R.id.tv_contributors_error)).check(matches(withText(textRes)));
        }
        return this;
    }

    //verify contributors error visibility
    RepoDetailsRobot verifyContributorsErrorVisibility(ViewMatchers.Visibility visibility) {
        onView(withId(R.id.tv_contributors_error)).check(matches(withEffectiveVisibility(visibility)));
        return this;
    }

    //verify loading view visibility
    RepoDetailsRobot verifyLoadingVisibility(ViewMatchers.Visibility visibility) {
        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(visibility)));
        return this;
    }

    //verify contributors loading view visibility
    RepoDetailsRobot verifyContributorsLoadingVisibility(ViewMatchers.Visibility visibility) {
        onView(withId(R.id.contributor_loading_indicator)).check(matches(withEffectiveVisibility(visibility)));
        return this;
    }

    //verify visibility of the text field
    RepoDetailsRobot verifyContributorShown(String login) {
        onView(allOf(withId(R.id.tv_user_name), withText(login))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        return this;
    }

    //vefity visibility of the whole linear layout which is the main view group of that layout
    RepoDetailsRobot verifyContentVisibility(ViewMatchers.Visibility visibility) {
        onView(withId(R.id.content)).check(matches(withEffectiveVisibility(visibility)));
        return this;
    }
}

