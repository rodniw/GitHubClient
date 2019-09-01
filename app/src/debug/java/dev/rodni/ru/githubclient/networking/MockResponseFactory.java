package dev.rodni.ru.githubclient.networking;

import android.content.Context;

import androidx.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.Request;

class MockResponseFactory {

    private final Context context;
    private final int startIndex;

    //this class needs the context and a base url to inflate startIndex variable
    @Inject
    MockResponseFactory(Context context, @Named("base_url") String baseUrl) {
        this.context = context;
        startIndex = baseUrl.length();
    }

    //getting mock response(dividing the request with the "/") from the mock loader
    @Nullable
    String getMockResponse(Request request) {
        String[] endpointParts = getEndpoint(request).split("/");
        return MockResourceLoader.getResponseString(context, request.method(), endpointParts);
    }

    //this gives an understanding does an url has some query parameter
    private String getEndpoint(Request request) {
        String url = request.url().toString();
        int queryParamStart = url.indexOf("?");
        return queryParamStart == -1 ? url.substring(startIndex) : url.substring(startIndex, queryParamStart);
    }
}
