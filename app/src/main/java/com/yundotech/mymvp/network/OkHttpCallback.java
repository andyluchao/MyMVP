package com.yundotech.mymvp.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkHttpCallback implements Callback {
    private String TAG = this.getClass().getName();
    private IHttpClient.IHttpRequestCallback mReqCallback;

    public OkHttpCallback(IHttpClient.IHttpRequestCallback mReqCallback) {
        this.mReqCallback = mReqCallback;
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.d(TAG, "onFailure: "+e.toString());
        this.mReqCallback.onFailure(call.request(), e);
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        int code = response.code();
        String result = Objects.requireNonNull(response.body()).string();
        Log.d(TAG, "onResponse: code ["+code+"] result: "+ result);
        if (code == 200) {
            this.mReqCallback.onSuccess(call.request(), result);
        }
    }
}
