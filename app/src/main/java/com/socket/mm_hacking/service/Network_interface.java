package com.socket.mm_hacking.service;

public interface Network_interface {

    interface service_to_model{

        void returnuser();
    }

    interface model_to_service{

        Integer getusername(Integer previous_user);
        Integer setsuccess(Integer pre_success);
        Integer setFailure(Integer pre_failure);
        Integer set_pre_user(Integer user);
        boolean send_login_status(String json);
        String send_userid(String json);
    }
}
