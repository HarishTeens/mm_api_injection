package com.socket.mm_hacking.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared_prefs {

    private static volatile Shared_prefs shared_prefs;
    Context context;
    SharedPreferences sharedPreferences, login_attempts;
    SharedPreferences.Editor editor, attempts;

    public Shared_prefs(Context context){
        this.context = context;
        prepare_sharedprefs();
    }

//    public static Shared_prefs getInstance(){
//        if (shared_prefs == null){
//            synchronized (Shared_prefs.class){
//                if (shared_prefs == null){
//                    shared_prefs = new Shared_prefs();
//                }
//            }
//        }
//        return shared_prefs;
//    }

    public void prepare_sharedprefs(){
        sharedPreferences = context.getSharedPreferences(Utils.login_cred_filename, Context.MODE_PRIVATE);
        login_attempts = context.getSharedPreferences(Utils.login_trials, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        attempts = login_attempts.edit();
//        editor.putInt(Utils.username_file, 12345);
//        editor.putInt(Utils.password_file, 12345);
//        editor.putInt(Utils.email_file, 12345);
//        editor.putInt(Utils.displayname_file, 12345);
//        editor.commit();
    }

    public void update_sharedprefs(Integer cred, Integer success, Integer failure, String last_id){

        editor.putInt(Utils.username_file, cred);
        editor.putInt(Utils.password_file, cred);
        editor.putInt(Utils.email_file, cred);
        editor.putInt(Utils.displayname_file, cred);
        editor.commit();
        attempts.putInt(Utils.login_success, success);
        attempts.putInt(Utils.login_failure, failure);
        attempts.putString(Utils.last_id, last_id);
        attempts.commit();
    }

    public Integer send_username(){

        return sharedPreferences.getInt(Utils.username_file, 14000);
    }

    public Integer send_password(){
        return sharedPreferences.getInt(Utils.password_file, 14000);

    }

    public Integer send_email(){
        return sharedPreferences.getInt(Utils.email_file, 14000);

    }

    public Integer send_displayname(){
        return sharedPreferences.getInt(Utils.displayname_file, 14000);

    }

    public Integer login_success(){
        return login_attempts.getInt(Utils.login_success, 1250);

    }

    public Integer login_failure(){
        return login_attempts.getInt(Utils.login_failure, 2);

    }

    public String last_id(){
        return login_attempts.getString(Utils.last_id, "0000");
    }
}
