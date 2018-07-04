package com.android.haule.mvpparttern.API;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ApiManager {
    private String mUrlRequest = "https://jsonplaceholder.typicode.com/";
    private static final  String URL_GET = "posts/1";
    //Instance singleton
    public static ApiManager instance;
    //Instance variable
    private RequestQueue mRequestQueue;
    private ApiManager() { }
    public static ApiManager getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new ApiManager();
            instance.mRequestQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    private void methodGET(final ApiCallback apiCallback, String url){
        if (instance.mUrlRequest.equals("")) {
            return;
        }

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        apiCallback.ApiResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            apiCallback.ApiResponse(String.valueOf(error.networkResponse.statusCode));
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() {
                // Set Header for call api
                Map<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json; charset=UTF-8");
                return header;
            }
        };

        //set request time out
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);
    }

    public void getData(ApiCallback apiCallback){
        this.methodGET(apiCallback, mUrlRequest + URL_GET);
    }
}
