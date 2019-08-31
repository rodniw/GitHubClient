package dev.rodni.ru.githubclient.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.jakewharton.rxbinding3.widget.RxCompoundButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.settings.DebugPreferences;
import io.reactivex.disposables.CompositeDisposable;

public class DebugActivityViewInterceptor implements ActivityViewInterceptor {

    @BindView(R.id.sw_mock_responses)
    Switch mockResponseSwitch;

    private final DebugPreferences debugPreferences;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    //to clear butter knife's references to the view
    private Unbinder unbinder;

    //inject my debug preferences here
    @Inject
    DebugActivityViewInterceptor(DebugPreferences debugPreferences) {
        this.debugPreferences = debugPreferences;
    }

    //this method will inflate my debug layout
    @Override
    public void setContentView(Activity activity, int layoutRes) {
        View debugLayout = LayoutInflater.from(activity).inflate(R.layout.debug_drawer, null);
        //bind layout with the butterknife
        unbinder = ButterKnife.bind(this, debugLayout);

        initializePrefs();

        //attaching activity layout to debug layout
        View activityLayout = LayoutInflater.from(activity).inflate(layoutRes, null);
        debugLayout.<ViewGroup>findViewById(R.id.activity_layout_container).addView(activityLayout);

        //adding the layout to a given activity
        activity.setContentView(debugLayout);
    }

    //in this method clean streams and binded views
    @Override
    public void clear() {
        compositeDisposable.dispose();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    //setting switch view to its state from preferences and start listening to switch button changes with rx binding
    private void initializePrefs() {
        mockResponseSwitch.setChecked(debugPreferences.useMockResponsesEnabled());

        //subscribe to listening for some changes
        //if they happens then send a result of those changes to prefs by using setUseMockResponse method
        compositeDisposable.addAll(
                RxCompoundButton.checkedChanges(mockResponseSwitch)
                .subscribe(debugPreferences::setUseMockResponse)
        );
    }
}
