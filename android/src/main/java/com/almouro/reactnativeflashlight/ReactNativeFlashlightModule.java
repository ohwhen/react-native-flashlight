package com.almouro.reactnativeflashlight;

import android.content.Context;
import android.util.AndroidException;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

class ReactNativeFlashlightModule extends ReactContextBaseJavaModule {
    private FlashlightControl flashlight;

    public ReactNativeFlashlightModule(ReactApplicationContext reactContext) {
        super(reactContext);
        flashlight = new FlashlightControl();
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from javascript.
     */
    @Override
    public String getName() {
        return "ReactNativeFlashlight";
    }

    @ReactMethod
    public void turnFlashlight(Boolean on, Callback onSuccess, Callback onFailure) {
        try {
            if (on) {
              flashlight.turnOn();
            } else {
              flashlight.turnOff();
            }
        } catch(AndroidException e) {
            onFailure.invoke(e.getMessage());
            return;
        }
        onSuccess.invoke();
    }
}
