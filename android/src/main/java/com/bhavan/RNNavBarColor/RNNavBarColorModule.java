package com.bhavan.RNNavBarColor;

import android.app.Activity;
import android.view.Window;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

public class RNNavBarColorModule extends ReactContextBaseJavaModule {
    public RNNavBarColorModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNNavBarColor";
    }

    @ReactMethod
    public void setColor(final String color, final boolean isLight) {
        final Activity activity = getCurrentActivity();
        final int colorInt = Color.parseColor(color);
        if(activity == null)
            return;
        
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int flags = activity.getWindow().getDecorView().getSystemUiVisibility();
                if(isLight){
                    flags = flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    flags = flags |  View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    activity.getWindow().getDecorView().setSystemUiVisibility(flags); 
                } else {
                    flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                    flags = flags ^ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                    activity.getWindow().getDecorView().setSystemUiVisibility(flags); 
                }
                activity.getWindow().setNavigationBarColor(colorInt);
            }
        });
    }

    @Override
    public Map<String, Object> getConstants() {
        HashMap<String, Object> constants = new HashMap<String, Object>();
        constants.put("apiLevel", Build.VERSION.SDK_INT);
        return constants;
    }

}
