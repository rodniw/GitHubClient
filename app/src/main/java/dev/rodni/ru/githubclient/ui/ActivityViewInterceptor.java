package dev.rodni.ru.githubclient.ui;

import android.app.Activity;

import androidx.annotation.LayoutRes;

public interface ActivityViewInterceptor {

    void setContentView(Activity activity, @LayoutRes int layoutRes);

    //to clear all the listeners or bindings i have
    void clear();

    //the default implementation of the interface
    //implementation that will be used in release build
    ActivityViewInterceptor DEFAULT = new ActivityViewInterceptor() {
        @Override
        public void setContentView(Activity activity, int layoutRes) {
            //normal activity setContentView call
            activity.setContentView(layoutRes);
        }

        @Override
        public void clear() {

        }
    };
}
