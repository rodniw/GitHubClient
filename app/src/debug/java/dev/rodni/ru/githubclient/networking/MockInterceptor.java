package dev.rodni.ru.githubclient.networking;

import java.io.IOException;

import javax.inject.Inject;

import dev.rodni.ru.githubclient.settings.DebugPreferences;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

//this class implements interceptor from okhttp
//this class let me change everything before being proceed(some headers for example)
public class MockInterceptor implements Interceptor {

    private final MockResponseFactory mockResponseFactory;
    private final DebugPreferences debugPreferences;

    @Inject
    MockInterceptor(MockResponseFactory mockResponseFactory, DebugPreferences debugPreferences) {
        this.mockResponseFactory = mockResponseFactory;
        this.debugPreferences = debugPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //if mock responses are enabled
        if (debugPreferences.useMockResponsesEnabled()) {
            //then get mock response from the factory
            String mockResponse = mockResponseFactory.getMockResponse(chain.request());
            //it can be null for some specific request
            if (mockResponse != null) {
                //building response header
                return new Response.Builder()
                        .message("")
                        .protocol(Protocol.HTTP_1_1)
                        .request(chain.request())
                        .code(200)
                        .body(ResponseBody.create(MediaType.parse("text/json"), mockResponse))
                        .build();
            }
        }
        //fall back to the api
        return chain.proceed(chain.request());
    }
}
