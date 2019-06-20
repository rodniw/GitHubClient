package dev.rodni.ru.githubclient.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;

import java.util.UUID;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.di.Injector;
import dev.rodni.ru.githubclient.di.ScreenInjector;
import dev.rodni.ru.githubclient.ui.ScreenNavigator;

public abstract class BaseActivity extends AppCompatActivity {

    private static String INSTANCE_ID_KEY = "instance_id";

    @Inject
    ScreenInjector screenInjector;
    @Inject
    ScreenNavigator screenNavigator;

    private String instanceId;
    //Router for Conductors is the same as FragmentManager for Fragments
    private Router router;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }

        Injector.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());

        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if (screenContainer == null) {
            throw new NullPointerException("Activity must have a view with id: screen_container");
        }

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        screenNavigator.initWithRouter(router, initialScreen());
        //by this method we can get info about backstack changes
        monitorBackStack();
    }

    @LayoutRes
    protected abstract int layoutRes();

    //because in the future we need to give to all activities
    //an opportunity to get their controllers
    protected abstract Controller initialScreen();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    @Override
    public void onBackPressed() {
        if (!screenNavigator.pop()) {
            super.onBackPressed();
        }
    }

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        screenNavigator.clear();
        if (isFinishing()) {
            Injector.clearComponent(this);
        }
    }

    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }

    private void monitorBackStack() {
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {
                //if controller not going to use anymore we can clear its component
                if (!isPush && from != null) {
                    Injector.clearComponent(from);
                }
            }
        });
    }
}
