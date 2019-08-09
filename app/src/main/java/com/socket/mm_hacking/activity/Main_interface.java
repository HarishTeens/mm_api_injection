package com.socket.mm_hacking.activity;

public interface Main_interface {

    interface model_presenter{

        String username(Integer user);
        String last_id(String user_id);
        String success(Integer success);
        String failure(Integer failure);
    }

    interface view_presenter{

        void onstart();
        void onpause();
        void ondestroy();
        void oncreate();
        void onresume();
    }

    interface presenter_view{

        void update_user(String user);
        void update_userid(String user_id);
        void success(String success);
        void failure(String failure);
    }
}
