package com.example.moon.ECommerse.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleTone {
   public Context mcontext;
    RequestQueue requestQueue;
    public static MySingleTone mySingleTone;

    public MySingleTone(Context mcontext) {
        this.mcontext = mcontext;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleTone getInstance(Context context){
        if(mySingleTone==null){
           mySingleTone =  new MySingleTone(context);
        }
        return mySingleTone;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mcontext);
        }
        return requestQueue;
    }


    public <T>void addrequestToQueue(Request<T> request){
        requestQueue.add(request);
    }
}
