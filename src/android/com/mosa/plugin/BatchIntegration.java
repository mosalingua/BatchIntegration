package com.mosa.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.batch.android.*;

import android.content.SharedPreferences;
import android.content.Context;

import android.preference.PreferenceManager;

public class BatchIntegration extends CordovaPlugin {
public static final String ACTION_CHANGE_LANGUAGE = "changeLanguage";
public static final String ACTION_ENABLE_PUSH_NOTIFICATIONS = "enablePushNotifications";
public static final String ACTION_TOGGLE_PUSH_NOTIFICATIONS = "togglePushNotifications";
public static final String ACTION_GET_PUSH_INSTALLATION_ID = "getPushInstallationId";

@Override
public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
                if (ACTION_CHANGE_LANGUAGE.equals(action)) {
                        JSONObject arg_object = args.getJSONObject(0);
                        //System.out.println("Inside action, language: " + arg_object.getString("language"));
                        String languageAndRegion = arg_object.getString("language");
                        String[] parts = languageAndRegion.split("_");
                        String language = parts[0];
                        String region = parts[1];
                        BatchUserProfile userProfile = Batch.getUserProfile();
                        if( userProfile != null ) {
                                System.out.println("Setting language: " + language + ", region: " + region);
                                // Use the user profile to set custom language and data
                                userProfile.setLanguage(language); // Language must be 2 chars, lowercase, ISO 639 formatted
                                userProfile.setRegion(region); // Region must be 2 chars, uppercase, ISO 3166 formatted
                        }
                        callbackContext.success();
                        return true;
                } else if (ACTION_TOGGLE_PUSH_NOTIFICATIONS.equals(action)) {
                    JSONObject arg_object = args.getJSONObject(0);
                    Boolean isEnabled = arg_object.getBoolean("isEnabled");
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.cordova.getActivity().getApplicationContext());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("isNotificationsEnabled", isEnabled);
                    editor.commit();
                    callbackContext.success();
                    return true;
                } else if(ACTION_ENABLE_PUSH_NOTIFICATIONS.equals(action)) {

                   //TODO code me Jeroen :-)
                   callbackContext.success();
                   return true;

                }
                else if(ACTION_GET_PUSH_INSTALLATION_ID.equals(action)) {

                    String identifier = Batch.User.getInstallationID();
                    if(identifier == null) {
                      identifier = "N/A";
                    }
                    callbackContext.success(identifier);
                    return true;
                }

                callbackContext.error("Invalid action");
                return false;
        } catch(Exception e) {
                System.err.println("Exception: " + e.getMessage());
                callbackContext.error(e.getMessage());
                return false;
        }
}
}
