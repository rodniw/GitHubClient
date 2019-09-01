package dev.rodni.ru.githubclient.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

//interface which will be implemented by the single activity to clean up methods which deals with conductors
public interface RouterProvider {

    Router getRouter();

    Controller initialScreen();
}
