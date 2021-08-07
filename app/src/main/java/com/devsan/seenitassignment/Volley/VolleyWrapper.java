package com.devsan.seenitassignment.Volley;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.devsan.seenitassignment.util.DataResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyWrapper {

    public static final String TAG = "devdroid";
    private final int defaultMaxNumberOfRetry = 2;
    private final int defaultMaxTimeoutInMill = 120 * 1000;

    public void getRequest(String url, DataResponseListener<Object> listener) {

        Log.d(TAG, "Main url : " + url);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url,
                t -> {
                    Log.d(TAG, "Response : " + t.toString());
                    listener.onSuccess(t);
                },
                error -> {
                    Log.d(TAG, "Eroor : " + error.getLocalizedMessage());

                    final byte[] responseData = error.networkResponse.data;
                    if (null == responseData || responseData.length <= 0) {
                        Log.e(TAG, "response Data response is null");
                        listener.onFailure("Response Data response is null");
                        return;
                    }

                    String responseString = new String(responseData);
                    JSONObject responseJSON;
                    try {
                        responseJSON = new JSONObject(responseString);
                        String message = responseJSON.optString("message");
                        listener.onFailure(message);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });

        DefaultRetryPolicy defaultRetryPolicy = new DefaultRetryPolicy() {
            int mCurrentRetryCount = 0;

            @Override
            public int getCurrentTimeout() {
                return defaultMaxTimeoutInMill;
            }

            @Override
            public int getCurrentRetryCount() {
                return mCurrentRetryCount;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

                if (mCurrentRetryCount >= defaultMaxNumberOfRetry) {
                    throw error;
                }
                Log.e("TAG","retrying" + error.getMessage());
                mCurrentRetryCount++;
                error.printStackTrace();
            }
        };

        stringRequest.setRetryPolicy(defaultRetryPolicy);
        stringRequest.setShouldCache(false);
        VolleySingletonQueue.getInstance().addToRequestQueue(stringRequest);
    }
}
