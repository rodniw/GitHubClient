package dev.rodni.ru.githubclient.networking;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import timber.log.Timber;

class MockResourceLoader {

    //Singleton
    private MockResourceLoader() {

    }

    //the second parameter is to understans a method type(get, post, e t c..)
    @Nullable
    static String getResponseString(Context context, String method, String[] endPointParts) {
        try {
            String currentPath = "mock";
            Set<String> mockList = new HashSet<>(Arrays.asList(context.getAssets().list(currentPath)));
            for (String endPointPart : endPointParts) {
                if (mockList.contains(endPointPart)) {
                    currentPath = currentPath + "/" + endPointPart;
                    mockList = new HashSet<>(Arrays.asList(context.getAssets().list(currentPath)));
                }
            }

            //At this stage my mock list will be the list of file in the matching directory for the endpoint parts.
            String finalPath = null;
            for (String path: mockList) {
                if (path.contains(method.toLowerCase())) {
                    finalPath = currentPath + "/" + path;
                    break;
                }
            }

            if (finalPath != null) {
                return responseFromPath(context, finalPath);
            }
            return null;
        } catch (IOException e) {
            Timber.e(e, "error loading mock response from assets");
            return null;
        }
    }

    private static String responseFromPath(Context context, String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream assetStream = context.getAssets().open(path);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            Timber.e(e, "Error reading mock response");
        }
        return stringBuilder.toString();
    }
}
