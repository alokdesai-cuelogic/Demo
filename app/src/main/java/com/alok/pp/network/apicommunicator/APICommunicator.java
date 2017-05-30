package com.alok.pp.network.apicommunicator;

import android.content.Context;

import com.alok.pp.interfaces.ResponseStatusListener;
import com.alok.pp.utilities.LogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class APICommunicator {

    private static final String AUTHORIZATION = "Authorization";
    private static final int REFRESH_TOKEN_EXPIRED_CODE = 401;
    public static final int CONFLICTING_ACTVITY_CODE = 409;
    public static final int NETWORK_ERROR_CODE = 400;
    private final int RESPONSE_TIMEOUT = 60000;

    public HTTPClient get(final Context context, final String absoluteUrl, String authToken, final ResponseStatusListener responseStatusListener) {

        if (!Reachability.isNetworkAvailable(context)) {
            LogUtils.printLogs("API CALL : " + absoluteUrl + "Returning No network available") ;
            responseStatusListener.onRequestFailure(NETWORK_ERROR_CODE, "Returning No network available");
            return null;
        }
        LogUtils.printLogs("API CALL : " + absoluteUrl);
        AsyncHttpClient client = new AsyncHttpClient();
        client.setResponseTimeout(RESPONSE_TIMEOUT);

        client.get(context, absoluteUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //LogUtils.printLogs("On Success : " + new String(responseBody));
                responseStatusListener.onRequestSuccess(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                responseStatusListener.onRequestFailure(statusCode, new String(responseBody));
            }
        });

        return new HTTPClient(client);
    }

    public HTTPClient post(final Context context, final String absoluteUrl, String authToken, final String postDataString, final ResponseStatusListener responseStatusListener) {


        if (!Reachability.isNetworkAvailable(context)) {
            LogUtils.printLogs("API CALL : " + absoluteUrl + "Returning No network available") ;
            responseStatusListener.onRequestFailure(NETWORK_ERROR_CODE, "Returning No network available");
            return null;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.setResponseTimeout(RESPONSE_TIMEOUT);
        StringEntity stringEntity = null;
        if(postDataString != null) {
            stringEntity = new StringEntity(postDataString, "UTF-8");
            LogUtils.printLogs("API CALL : " + absoluteUrl + " \nRequest Params : " + postDataString);
        }
        client.post(context,absoluteUrl,stringEntity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtils.printLogs("On Success : " + response.toString());
                responseStatusListener.onRequestSuccess(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                LogUtils.printLogs("On Success : " + response.toString());
                responseStatusListener.onRequestSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                String errorMessage = "";
                if (errorResponse != null) {
                    try {
                        errorMessage = errorResponse.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }

            //Need to override this method because for post request to get sessionId from Rogue Api getting failure response in this method
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                String errorMessage = "";
                if (responseString != null) {
                    try {
                        JSONObject errorMessageJson = new JSONObject(responseString);
                        errorMessage = errorMessageJson.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }
        });

        return new HTTPClient(client);
    }

    public HTTPClient syncPost(final Context context, final String absoluteUrl, String authToken, final String postDataString, final ResponseStatusListener responseStatusListener) {
        if (!Reachability.isNetworkAvailable(context)) {
            LogUtils.printLogs("API CALL : " + absoluteUrl + "Returning No network available") ;
            responseStatusListener.onRequestFailure(NETWORK_ERROR_CODE, "Returning No network available");
            return null;
        }

        SyncHttpClient client = new SyncHttpClient();
        client.setResponseTimeout(RESPONSE_TIMEOUT);
        StringEntity stringEntity = null;
        if(postDataString != null) {
            stringEntity = new StringEntity(postDataString, "UTF-8");
            LogUtils.printLogs("API CALL : " + absoluteUrl + " \nRequest Params : " + postDataString);
        }
        client.post(context,absoluteUrl,stringEntity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtils.printLogs("On Success : " + response.toString());
                responseStatusListener.onRequestSuccess(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                LogUtils.printLogs("On Success : " + response.toString());
                responseStatusListener.onRequestSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                String errorMessage = "";
                if (errorResponse != null) {
                    try {
                        errorMessage = errorResponse.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                LogUtils.printLogs(">>> On Failure : "+errorMessage);
                responseStatusListener.onRequestFailure(statusCode, errorMessage);

            }

            //Need to override this method because for post request to get sessionId from Rogue Api getting failure response in this method
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                String errorMessage = "";
                if (responseString != null) {
                    try {
                        JSONObject errorMessageJson = new JSONObject(responseString);
                        errorMessage = errorMessageJson.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }
        });

        return new HTTPClient(client);
    }

    public HTTPClient put(final Context context, final String absoluteUrl, String authToken, final String postDataString, final ResponseStatusListener responseStatusListener) {

        if (!Reachability.isNetworkAvailable(context)) {
            LogUtils.printLogs("API CALL : " + absoluteUrl + "Returning No network available") ;
            responseStatusListener.onRequestFailure(NETWORK_ERROR_CODE, "Returning No network available");
            return null;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.setResponseTimeout(RESPONSE_TIMEOUT);
        StringEntity stringEntity = new StringEntity(postDataString,"UTF-8");
        LogUtils.printLogs("API CALL : " + absoluteUrl + " \nRequest Params : " + postDataString);

        client.patch(context,absoluteUrl,stringEntity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtils.printLogs("On Success : " + response.toString());
                responseStatusListener.onRequestSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                String errorMessage = "";
                if (errorResponse != null) {
                    try {
                        errorMessage = errorResponse.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }

            //Need to override this method because for post request to get sessionId from Rogue Api getting failure response in this method
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                LogUtils.printLogs("On Failure : " + responseString);
                String errorMessage = "";
                if(responseString != null) {
                    try {
                        JSONObject errorMessageJson = new JSONObject(responseString);
                        errorMessage = errorMessageJson.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }
        });

        return new HTTPClient(client);
    }

    public HTTPClient syncPut(final Context context, final String absoluteUrl, String authToken, final String postDataString, final ResponseStatusListener responseStatusListener) {

        if (!Reachability.isNetworkAvailable(context)) {
            LogUtils.printLogs("API CALL : " + absoluteUrl + "Returning No network available") ;
            responseStatusListener.onRequestFailure(NETWORK_ERROR_CODE, "Returning No network available");
            return null;
        }

        SyncHttpClient client = new SyncHttpClient();
        client.setResponseTimeout(RESPONSE_TIMEOUT);
        StringEntity stringEntity = new StringEntity(postDataString,"UTF-8");
        LogUtils.printLogs("API CALL : " + absoluteUrl + " \nRequest Params : " + postDataString);

        client.patch(context,absoluteUrl,stringEntity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtils.printLogs("On Success : " + response.toString());
                responseStatusListener.onRequestSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                String errorMessage = "";
                if (errorResponse != null) {
                    try {
                        errorMessage = errorResponse.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);

            }

            //Need to override this method because for post request to get sessionId from Rogue Api getting failure response in this method
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                LogUtils.printLogs("On Failure : " + responseString);
                String errorMessage = "";
                if(responseString != null) {
                    try {
                        JSONObject errorMessageJson = new JSONObject(responseString);
                        errorMessage = errorMessageJson.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }
        });

        return new HTTPClient(client);
    }

    public HTTPClient patch(final Context context, final String absoluteUrl, String authToken, final String postDataString, final ResponseStatusListener responseStatusListener) {

        if (!Reachability.isNetworkAvailable(context)) {
            LogUtils.printLogs("API CALL : " + absoluteUrl + "Returning No network available") ;
            responseStatusListener.onRequestFailure(NETWORK_ERROR_CODE, "Returning No network available");
            return null;
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.setResponseTimeout(RESPONSE_TIMEOUT);
        StringEntity stringEntity = new StringEntity(postDataString,"UTF-8");
        LogUtils.printLogs("API CALL : " + absoluteUrl + " \nRequest Params : " + postDataString);

        client.patch(context,absoluteUrl,stringEntity,"application/json",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                LogUtils.printLogs("On Success : " + response.toString());
                responseStatusListener.onRequestSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                String errorMessage = "";
                if (errorResponse != null) {
                    try {
                        errorMessage = errorResponse.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }

            //Need to override this method because for post request to get sessionId from Rogue Api getting failure response in this method
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                LogUtils.printLogs("On Failure : " + responseString);
                String errorMessage = "";
                if(responseString != null) {
                    try {
                        JSONObject errorMessageJson = new JSONObject(responseString);
                        errorMessage = errorMessageJson.getString("msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                responseStatusListener.onRequestFailure(statusCode, errorMessage);
            }
        });

        return new HTTPClient(client);
    }
}
