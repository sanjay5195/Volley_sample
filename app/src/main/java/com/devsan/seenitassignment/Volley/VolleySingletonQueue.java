package com.devsan.seenitassignment.Volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingletonQueue {

    private static final String TAG = "BookApp-Request";

    private static VolleySingletonQueue mVolleySingleton;
    private final RequestQueue requestQueue;

    private VolleySingletonQueue() {

        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingletonQueue getInstance() {
        if (mVolleySingleton == null) {
            mVolleySingleton = new VolleySingletonQueue();
        }
        return mVolleySingleton;
    }

    private RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {

        if(req == null){
            return;
        }

        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
