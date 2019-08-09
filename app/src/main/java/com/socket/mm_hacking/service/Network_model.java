package com.socket.mm_hacking.service;

import org.json.JSONException;
import org.json.JSONObject;

public class Network_model implements Network_interface.model_to_service {

    private Network_interface.service_to_model service_to_model;

    public Network_model(Network_interface.service_to_model service_to_model){
        this.service_to_model = service_to_model;

    }

    @Override
    public Integer getusername(Integer previous_user) {
        return previous_user + 1;
    }

    @Override
    public Integer setsuccess(Integer pre_success) {
        return pre_success + 1;
    }

    @Override
    public Integer setFailure(Integer pre_failure) {
        return pre_failure + 1;
    }

    @Override
    public Integer set_pre_user(Integer user) {
        return user;
    }

    @Override
    public boolean send_login_status(String json) {
        Boolean status = false;
        try {
            JSONObject jsonObject = new JSONObject(json);
            status = jsonObject.getBoolean("success");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public String send_userid(String json) {
        String userid = "0000";
        try {
            JSONObject jsonObject = new JSONObject(json);
            userid = jsonObject.getString("userID");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userid;
    }
}
