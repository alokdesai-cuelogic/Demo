package com.alok.pp.network.apicommunicator;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;


public class HTTPClient {

    private AsyncHttpClient asyncHttpClient = null;

    public HTTPClient(AsyncHttpClient httpClient) {
        asyncHttpClient = httpClient;
    }

    public void cancelRequest(Context context) {
        if (asyncHttpClient != null) {
            //LogUtils.printLogs("Cancel Request");
            asyncHttpClient.cancelRequests(context, true);
        }
    }
}
