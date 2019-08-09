package com.socket.mm_hacking.activity;

public class Main_model implements Main_interface.model_presenter {

    @Override
    public String username(Integer user) {
        return user+"";
    }

    @Override
    public String last_id(String user_id) {
        return user_id+"";
    }

    @Override
    public String success(Integer success) {
        return success+"";
    }

    @Override
    public String failure(Integer failure) {
        return failure+"";
    }
}
