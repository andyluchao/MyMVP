package com.yundotech.mymvp.network;

import java.util.Map;

import okhttp3.Request;

public interface IHttpClient {
    public interface IHttpRequestCallback {
        void onSuccess(Request request, String result);
        void onFailure(Request request, Exception e);
    }

    boolean asyncGet(String url, Map<String, String> params, IHttpRequestCallback callback);
    boolean asyncPost(String url, String jsonBody, IHttpRequestCallback callback);
    boolean asyncPut(String url, String jsonBody, IHttpRequestCallback callback);
    boolean asyncDelete(String url, String jsonBody, IHttpRequestCallback callback);
}
