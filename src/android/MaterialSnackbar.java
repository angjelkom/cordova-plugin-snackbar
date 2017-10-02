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
import android.view.View;

public class MaterialSnackbar extends CordovaPlugin {

    private FrameLayout layout;
    private Snackbar snackbar;

  @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        layout = (FrameLayout) webView.getView().getParent();
    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            if ("materialSnackbar".equals(action)) {

                if(snackbar != null){
                    snackbar.dismiss();
                }

                JSONObject arg_object = args.getJSONObject(0);

                final String text = arg_object.getString("text");

                final String duration = arg_object.getString("duration");

                final String button = arg_object.getString("button");

                cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {

                        snackbar = Snackbar
                        .make(layout, text, Snackbar.LENGTH_INDEFINITE);

                        if(duration.equals("SHORT")){
                          snackbar.setDuration(Snackbar.LENGTH_SHORT);
                        } else if(duration.equals("LONG")){
                          snackbar.setDuration(Snackbar.LENGTH_LONG);
                        } else if(duration.equals("INDEFINITE")){
                          snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);
                        }

                        if(button != null && !button.isEmpty()){
                          snackbar.setAction(button, new View.OnClickListener() {
                              @Override
                              public void onClick(View view) {
                                  snackbar.dismiss();
                                  callbackContext.success();
                              }
                          });
                        }
                        snackbar.show();
                    }
                });
                return true;
            } else if ("closeMaterialSnackbar".equals(action)) {
                snackbar.dismiss();
                callbackContext.success();
            }
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}
