package dev.rodni.ru.githubclient.lifecycle;

import android.view.View;

import dev.rodni.ru.githubclient.di.ActivityScope;

public abstract class ScreenLifecycleTask {

    /**
     * Callback received when a Screen becomes the visible screen.
     */
    public void onEnterScope(View view) {

    }

    /**
     * Callback received when a Screen is either popped of moved to the back stack.
     */
    public void onExitScope() {

    }

    /**
     * Callback received when a Screen is destroyed and will not be coming back (except as a new instance). This should
     * be used to clear any {@link ActivityScope} connections (Disposables, etc).
     */
    public void onDestroy() {

    }
}
