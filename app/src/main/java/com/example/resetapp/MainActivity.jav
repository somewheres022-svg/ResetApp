package com.example.resetapp;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private DevicePolicyManager dpm;
    private ComponentName admin;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        admin = new ComponentName(this, MyDeviceAdminReceiver.class);

        Button btn = findViewById(R.id.btnReset);
        btn.setOnClickListener(v -> {
            if (dpm.isDeviceOwnerApp(getPackageName())) {
                dpm.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);
            } else {
                Toast.makeText(this, "اپ Device Owner نیست! ابتدا با adb آن را set-device-owner کنید", Toast.LENGTH_LONG).show();
            }
        });
    }
}
