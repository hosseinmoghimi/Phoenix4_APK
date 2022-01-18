package com.khafonline.phoenix4.core;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.khafonline.phoenix4.model.Profile;
import com.khafonline.phoenix4.model.Setting;
import com.khafonline.phoenix4.volley.MyApplication;

import java.util.ArrayList;
import java.util.List;

//
//import com.khafonline.genesis.auth.AuthCheck;

/**
 * Created by Hossein on 15/03/2019.
 */


public class SharedPref {
    private static final String RememberedUsername = "RememberedUsername";
    private static final String RememberedPassword = "RememberedPassword";


    //region Set & Get


    private static boolean getBoolean(String keyName, boolean defaultValue) {
        SharedPreferences sharedPreferences = MyApplication.getAppContext().getSharedPreferences(Constants.SHAREDPREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(keyName, defaultValue);
    }

    private static void setBoolean(String keyName, boolean keyValue) {

        SharedPreferences sharedPref = MyApplication.getAppContext().getSharedPreferences(Constants.SHAREDPREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(keyName, keyValue);
        editor.apply();
    }

    private static String getString(String keyName) {
        SharedPreferences sharedPreferences = MyApplication.getAppContext().getSharedPreferences(Constants.SHAREDPREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(keyName, "");
    }

    private static void setString(String keyName, String keyValue) {

        SharedPreferences sharedPref = MyApplication.getAppContext().getSharedPreferences(Constants.SHAREDPREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(keyName, keyValue);
        editor.apply();
    }

    private static int getInt(String keyName) {
        SharedPreferences sharedPreferences = MyApplication.getAppContext().getSharedPreferences(Constants.SHAREDPREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(keyName, 0);
    }

    private static void setInt(String keyName, int keyValue) {

        SharedPreferences sharedPref = MyApplication.getAppContext().getSharedPreferences(Constants.SHAREDPREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(keyName, keyValue);
        editor.apply();
    }

    //endregion


    public static boolean getFirstNameFirst() {
        return getBoolean(Constants.SHAREDPREF_FIRST_NAME_FIRST, true);
    }

    public static void setFirstNameFirst(boolean firstNameFirst) {
        setBoolean(Constants.SHAREDPREF_FIRST_NAME_FIRST, firstNameFirst);
    }


    public static boolean isFirstRun() {
        return getBoolean(Constants.SHAREDPREF_FIRST_RUN, true);
    }

    public static void setFirstRun(boolean firstNameFirst) {
        setBoolean(Constants.SHAREDPREF_FIRST_RUN, firstNameFirst);
    }


    public static String getServerAddress() {
        return getString(Constants.SHAREDPREF_SERVER_ADDRESS);
    }

    public static void setServerAddress(String serverAddress) {
        setString(Constants.SHAREDPREF_SERVER_ADDRESS, serverAddress);
    }


    public static void setProfile(Profile profile) {
        editUser(profile);
        //Core.startPusher();
        //AuthCheck.StartBeam();

    }

    public static void saveSettings(List<Setting> settings) {
        for (int i = 0; i < settings.size(); i++) {
            String v = settings.get(i).getParamteterValue();
            String n = settings.get(i).getParamteterName();

            setString("SETTING_" + n, v);
        }
    }

    public static List<Setting> loadSettings() {
        List<Setting> settings = new ArrayList<>();
        for (int i = 0; i < Setting.PARAMETER_NAMES.length; i++) {
            String n = Setting.PARAMETER_NAMES[i];

            String v = getString("SETTING_" + n);
            settings.add(new Setting(n, v));
        }
        return settings;
    }

    public static void setProfileImage(String image) {
        setString(Constants.SHAREDPREF_PROFILE_IMAGE, image);
    }


    public static void editUser(Profile profile) {
        if (profile == null) {
            setString(Constants.SHAREDPREF_PROFILE_EMAIL, "");
            setString(Constants.SHAREDPREF_PROFILE_FIRST_NAME, "");
            setString(Constants.SHAREDPREF_PROFILE_LAST_NAME, "");
            setString(Constants.SHAREDPREF_PROFILE_TOKEN, "");
            setString(Constants.SHAREDPREF_PROFILE_IMAGE, "");
            setString(Constants.SHAREDPREF_PROFILE_MOBILE, "");
            setString(Constants.SHAREDPREF_PROFILE_USERNAME, "");
            setInt(Constants.SHAREDPREF_SHIPPER_ID, 0);
            setInt(Constants.SHAREDPREF_SUPPLIER_ID, 0);
            setInt(Constants.SHAREDPREF_PROFILE_INVITED, 0);
            setInt(Constants.SHAREDPREF_PROFILE_ACCEPTED, 0);
            setString(Constants.SHAREDPREF_PROFILE_REGION, "");
            setString(Constants.SHAREDPREF_PROFILE_BIO, "");
        } else {
//            setString(Constants.SHAREDPREF_PROFILE_EMAIL, profile.getEmail());
            setString(Constants.SHAREDPREF_PROFILE_FIRST_NAME, profile.getFirst_name());
            setString(Constants.SHAREDPREF_PROFILE_LAST_NAME, profile.getLast_name());
            setString(Constants.SHAREDPREF_PROFILE_TOKEN, profile.getToken());
            setString(Constants.SHAREDPREF_PROFILE_IMAGE, profile.getImage());
            setString(Constants.SHAREDPREF_PROFILE_MOBILE, profile.getMobile());
//            setString(Constants.SHAREDPREF_PROFILE_USERNAME, profile.getUsername());
//            setInt(Constants.SHAREDPREF_SHIPPER_ID, profile.getShipper_id());
//            setInt(Constants.SHAREDPREF_SUPPLIER_ID, profile.getSupplier_id());
//            setString(Constants.SHAREDPREF_PROFILE_INVITED,profile.getInviter()==null?"": profile.getInviter().getFullName());
            setString(Constants.SHAREDPREF_PROFILE_BIO, profile.getBio());
//            setString(Constants.SHAREDPREF_PROFILE_REGION, profile.getCity());
        }
    }

    @Nullable
    public static Profile getProfile() {
        Profile profile = new Profile();

//        profile.setShipper_id(getInt(Constants.SHAREDPREF_SHIPPER_ID));
//        profile.setSupplier_id(getInt(Constants.SHAREDPREF_SUPPLIER_ID));
//        profile.setEmail(getString(Constants.SHAREDPREF_PROFILE_EMAIL));
        profile.setFirst_name(getString(Constants.SHAREDPREF_PROFILE_FIRST_NAME));
        profile.setLast_name(getString(Constants.SHAREDPREF_PROFILE_LAST_NAME));
        profile.setToken(getString(Constants.SHAREDPREF_PROFILE_TOKEN));
        profile.setMobile(getString(Constants.SHAREDPREF_PROFILE_MOBILE));
        profile.setImage(getString(Constants.SHAREDPREF_PROFILE_IMAGE));
//        profile.setUsername(getString(Constants.SHAREDPREF_PROFILE_USERNAME));
//        profile.setCity(getString(Constants.SHAREDPREF_PROFILE_REGION));
        profile.setBio(getString(Constants.SHAREDPREF_PROFILE_BIO));

//        if (profile.getToken().equals(""))
//            return null;
        return profile;


    }

    public static String getToken() {
        return getString(Constants.SHAREDPREF_PROFILE_TOKEN);
    }

    public static void setRememberedUsername(String username) {
        setString(RememberedUsername, username);
    }

    public static String getRememberedUsername() {
        return getString(RememberedUsername);
    }


    public static void setRememberedPassword(String password) {
        setString(RememberedPassword, password);
    }

    public static String getRememberedPassword() {
        return getString(RememberedPassword);
    }

}

