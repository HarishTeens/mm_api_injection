package com.socket.mm_hacking.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.socket.mm_hacking.service.Network_conn;
import com.socket.mm_hacking.service.Network_interface;
import com.socket.mm_hacking.utils.Shared_prefs;
import com.socket.mm_hacking.utils.Utils;

public class Main_presenter implements Main_interface.view_presenter {

    private Context context;
    private Main_interface.presenter_view presenter_view;
    private BroadcastReceiver login_receiver;
    private Shared_prefs shared_prefs;
    private Main_interface.model_presenter model_presenter;

    public Main_presenter(Context context, Main_interface.presenter_view presenter_view){
        this.context = context;
        this.presenter_view = presenter_view;
    }

    @Override
    public void onstart() {
        setshared_pref_data();
        startservice();
        registerreceiver();
    }

    @Override
    public void onpause() {
        unregisterreceiver();
    }

    @Override
    public void ondestroy() {
        stopservice();
    }

    @Override
    public void oncreate() {

        shared_prefs = new Shared_prefs(context);
        model_presenter = new Main_model();

    }

    @Override
    public void onresume() {
        registerreceiver();
    }

    private void startservice(){
        context.startService(new Intent(context, Network_conn.class));
    }

    private void stopservice(){
        context.stopService(new Intent(context, Network_conn.class));
    }

    private void registerreceiver(){

        if (login_receiver == null) {
            login_receiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {

                    if (intent.getExtras().getString(Utils.broadcast_id) != null) {

                        Log.d("ABHI", intent.getExtras().getInt(Utils.broadcast_username, 0)+"");
                        Log.d("ABHI", intent.getExtras().getInt(Utils.broadcast_success, 0)+"");
                        Log.d("ABHI", intent.getExtras().getInt(Utils.broadcast_failure, 0)+"");
                        Log.d("ABHI", intent.getExtras().getString(Utils.broadcast_id, "0000")+"");

                        presenter_view.update_user(model_presenter.username(intent.getExtras().getInt(Utils.broadcast_username, 0)));
                        presenter_view.update_userid(model_presenter.last_id(intent.getExtras().getString(Utils.broadcast_id, "0000")));
                        presenter_view.success(model_presenter.success(intent.getExtras().getInt(Utils.broadcast_success, 0)));
                        presenter_view.failure(model_presenter.failure(intent.getExtras().getInt(Utils.broadcast_failure, 0)));
                    }
                }
            };

        }
        context.registerReceiver(login_receiver, new IntentFilter(Utils.broadcast_intent));
    }

    private void unregisterreceiver(){
        context.unregisterReceiver(login_receiver);
    }

    private void setshared_pref_data(){
        presenter_view.update_user(model_presenter.username(shared_prefs.send_username()));
        presenter_view.update_userid(model_presenter.last_id(shared_prefs.last_id()));
        presenter_view.success(model_presenter.success(shared_prefs.login_success()));
        presenter_view.failure(model_presenter.failure(shared_prefs.login_failure()));
    }
}
