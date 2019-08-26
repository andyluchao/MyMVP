package com.yundotech.mymvp.network;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpClient implements IHttpClient {
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private String mServer;
    private String mTokenValue;
    private String mTokenName;
    private OkHttpClient mOkClient;

    enum HttpMethod {
        GET,
        POST,
        PUT,
        DELETE
    }
    public HttpClient(String mServer, String mTokenName) {
        this.mServer = mServer;
        this.mTokenValue = null;
        this.mTokenName = mTokenName;
        this.mOkClient = new OkHttpClient();
        mOkClient.newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS);
    }

    public void setToken(String token) {
        this.mTokenValue = token;
    }

    @Override
    public boolean asyncGet(String url, Map<String, String> params, final IHttpRequestCallback callback) {
        Request request = generateGetRequest(url, params);
        Call call = mOkClient.newCall(request);
        call.enqueue(new OkHttpCallback(callback));
        return true;
    }

    @Override
    public boolean asyncPost(String url, String jsonBody, IHttpRequestCallback callback) {
        Request request = generateRequestWithBody(url, jsonBody, HttpMethod.POST);
        if (request == null) {
            return false;
        }
        Call call = mOkClient.newCall(request);
        call.enqueue(new OkHttpCallback(callback));
        return true;
    }

    @Override
    public boolean asyncPut(String url, String jsonBody, IHttpRequestCallback callback) {
        Request request = generateRequestWithBody(url, jsonBody, HttpMethod.PUT);
        if (request == null) {
            return false;
        }
        Call call = mOkClient.newCall(request);
        call.enqueue(new OkHttpCallback(callback));
        return true;
    }

    @Override
    public boolean asyncDelete(String url, String jsonBody, IHttpRequestCallback callback) {
        Request request = generateRequestWithBody(url, jsonBody, HttpMethod.DELETE);
        if (request == null) {
            return false;
        }
        Call call = mOkClient.newCall(request);
        call.enqueue(new OkHttpCallback(callback));
        return true;
    }

    private String paramToUrl(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                // TODO: need to check with back-end server API design
                if (entry.getValue() != null) {
                    builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        }
        if (builder.length() > 0) {
            builder.replace(0, 1, "?");
        }
        return builder.toString();
    }

    private Request generateGetRequest(String url, Map<String, String> params) {
        String reqUrl = this.mServer + url;
        Request.Builder builder = new Request.Builder();
        if (params != null) {
            reqUrl += paramToUrl(params);
        }
        builder.url(reqUrl);
        if (this.mTokenValue != null) {
            builder.addHeader(this.mTokenName, this.mTokenValue);
        }
        builder.addHeader("Accept", "application/json");
        return builder.build();
    }

    private Request generateRequestWithBody(String url, String jsonBody, HttpMethod method) {
        String reqUrl = this.mServer + url;
        Request.Builder builder = new Request.Builder();
        builder.url(reqUrl);
        if (this.mTokenValue != null) {
            builder.addHeader(this.mTokenName, this.mTokenValue);
        }
        builder.addHeader("Content-Type", "application/json");
        builder.addHeader("Accept", "application/json");
        switch (method) {
            case POST:
                builder.post(RequestBody.create(jsonBody,JSON));
                break;
            case PUT:
                builder.put(RequestBody.create(jsonBody,JSON));
                break;
            case DELETE:
                builder.delete(RequestBody.create(jsonBody,JSON));
                break;
            default:
                return null;
        }
        return builder.build();
    }
}
