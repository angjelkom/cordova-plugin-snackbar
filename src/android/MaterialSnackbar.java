package com.materialSnackbar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.design.widget.Snackbar;
import android.widget.FrameLayout;

public class MaterialSnackbar extends CordovaPlugin {

  FrameLayout layout;

  @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {

        layout = (FrameLayout) webView.getView().getParent();

      }


    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

        try {
            if ("materialSnackbar".equals(action)) {

                JSONObject arg_object = args.getJSONObject(0);

                final String text = arg_object.getString("text");

                final String duration = arg_object.getString("duration");

                cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {

                        Snackbar snackbar = Snackbar
                        .make(layout, text, Snackbar.LENGTH_LONG);

                        if(duration.equals("SHORT")){
                          snackbar.setDuration(Snackbar.LENGTH_SHORT);
                        } else if(duration.equals("LONG")){
                          snackbar.setDuration(Snackbar.LENGTH_LONG);
                        } /*else if(duration.equals("INDEFINITE")){
                          snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                        }*/

                        snackbar.show();

                        callbackContext.success();
                    }
                });
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
