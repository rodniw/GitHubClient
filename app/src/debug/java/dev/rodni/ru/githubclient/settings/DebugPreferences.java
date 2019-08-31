package dev.rodni.ru.githubclient.settings;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DebugPreferences {

    private final static String MOCK_RELEASES_KEY = "mock_response";

    private final SharedPreferences sharedPreferences;

    @Inject
    DebugPreferences(Context context) {
        //taking specific file from which i can fetch setting's preferences
        sharedPreferences = context.getSharedPreferences("debug_settings", Context.MODE_PRIVATE);
    }

    //gettings current values
    //by default its gonna be false
    public boolean useMockResponsesEnabled() {
        return sharedPreferences.getBoolean(MOCK_RELEASES_KEY, false);
    }

    //setting use mock response
    public void setUseMockResponse(boolean useMockResponse) {
        sharedPreferences.edit().putBoolean(MOCK_RELEASES_KEY, useMockResponse).apply();
    }

}
