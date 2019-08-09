package com.socket.mm_hacking.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.socket.mm_hacking.R;
import com.socket.mm_hacking.service.Network_conn;
import com.socket.mm_hacking.utils.Shared_prefs;
import com.socket.mm_hacking.utils.Utils;

public class MainActivity extends AppCompatActivity implements Main_interface.presenter_view{

    private TextView success, failure, username, userid;
    private Main_presenter main_presenter;
    private Main_interface.view_presenter view_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        success = findViewById(R.id.success);
        failure = findViewById(R.id.failure);
        username = findViewById(R.id.username);
        userid = findViewById(R.id.userid);
        main_presenter = new Main_presenter(this, this);
        view_presenter = main_presenter;
        view_presenter.oncreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        view_presenter.onstart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        view_presenter.onpause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view_presenter.onresume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view_presenter.ondestroy();
    }

    @Override
    public void update_user(String user) {
        username.setText(user);
    }

    @Override
    public void update_userid(String user_id) {
        userid.setText(user_id);
    }

    @Override
    public void success(String success) {
        this.success.setText(success);
    }

    @Override
    public void failure(String failure) {
        this.failure.setText(failure);
    }
}
