package com.mosalingua.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.batch.android.*;

public class BatchIntegration extends CordovaPlugin {
public static final String ACTION_CHANGE_LANGUAGE = "changeLanguage";

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
