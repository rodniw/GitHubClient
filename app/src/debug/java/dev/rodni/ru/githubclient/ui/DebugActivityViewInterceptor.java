package dev.rodni.ru.githubclient.ui;

import android.app.Activity;

import javax.inject.Inject;

public class DebugActivityViewInterceptor implements ActivityViewInterceptor {

    @Inject
    DebugActivityViewInterceptor() {

    }

    @Override
    public void setContentView(Activity activity, int layoutRes) {

    }

    @Override
    public void clear() {

    }
}
