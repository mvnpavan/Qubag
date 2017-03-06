package net.world.qubag.utils;

import android.content.Context;
import android.content.SharedPreferences;

import net.world.qubag.models.User;

/**
 * Created by mvnpavan on 05/03/17.
 */

public class Preferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final String USER_KEY = "username";
    private final String USER_PASSCODE = "passcode";


    public Preferences(Context context) {

        sharedPreferences = context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void storeUserDetails(User user){
        if (sharedPreferences == null || editor == null) return;
        editor.putString(USER_KEY , user.getUsername());
        editor.putString(USER_PASSCODE , user.getPassword());
        editor.apply();
    }

    public User getUserDetails(){
        User user = new User();

        if (sharedPreferences == null || editor == null) return user;

        user.setUsername(sharedPreferences.getString(USER_KEY , ""));

        user.setPassword(sharedPreferences.getString(USER_PASSCODE , ""));

        return user;
    }
}
