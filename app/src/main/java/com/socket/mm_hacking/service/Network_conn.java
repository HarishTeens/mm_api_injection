package com.socket.mm_hacking.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.socket.mm_hacking.utils.Mysingleton;
import com.socket.mm_hacking.utils.Shared_prefs;
import com.socket.mm_hacking.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Network_conn extends Service implements Network_interface.service_to_model{

    private Network_model network_model;
    private Network_interface.model_to_service model_to_service;
    private Shared_prefs shared_prefs;
    private Integer username, success, failure , previous_user;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        network_model = new Network_model(this);
        model_to_service = network_model;
        shared_prefs = new Shared_prefs(this);
        previous_user = shared_prefs.send_username();
        success = shared_prefs.login_success();
        failure = shared_prefs.login_failure();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        login_start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //shared_prefs.update_sharedprefs(username, success, failure);
        super.onDestroy();
    }

    private void login_start(){
        username = model_to_service.getusername(previous_user);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Utils.api_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String last_id = "0000";

                if (model_to_service.send_login_status(response)){
                    success = model_to_service.setsuccess(success);
                    last_id = model_to_service.send_userid(response);

                } else {
                    failure = model_to_service.setFailure(failure);
                }

                previous_user = model_to_service.set_pre_user(username);
                sendbroadcast(last_id);
                login_start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                failure = model_to_service.setFailure(failure);
                previous_user = model_to_service.set_pre_user(username);
                sendbroadcast("0000");
                login_start();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put(Utils.volley_username, username+"");
                map.put(Utils.volley_password, username+"");
                map.put(Utils.volley_email, username+"");
                map.put(Utils.volley_displayname, username+"");
                return map;
            }
        };
        Mysingleton.getInstance(getApplicationContext()).addtorequestque(stringRequest);
        //Log.d("ABHI", ""+username);
    }

    @Override
    public void returnuser() {

    }

    public void sendbroadcast(String last_user_id){
        Intent broadcast_logins = new Intent(Utils.broadcast_intent);
        broadcast_logins.putExtra(Utils.broadcast_username, username);
        broadcast_logins.putExtra(Utils.broadcast_success, success);
        broadcast_logins.putExtra(Utils.broadcast_failure, failure);
        broadcast_logins.putExtra(Utils.broadcast_id, last_user_id);
        sendBroadcast(broadcast_logins);
        shared_prefs.update_sharedprefs(username, success, failure, last_user_id);

    }
}
