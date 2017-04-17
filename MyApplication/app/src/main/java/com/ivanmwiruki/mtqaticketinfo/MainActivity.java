/*
 * Created by Ivan Mwiruki
 */

package com.ivanmwiruki.mtqaticketinfo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //define variables for the textview widgets that will be populated with data
    private TextView dateAndTime;
    private TextView buildValue;
    private TextView modelValue;
    private TextView deviceIDValue;
    private TextView androidVersionValue;
    private TextView apiValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mtqaticketinfo_main);

        //get references to the widgets
        dateAndTime = (TextView) findViewById(R.id.dateAndTime);
        buildValue = (TextView) findViewById(R.id.buildValue);
        modelValue = (TextView) findViewById(R.id.modelValue);
        deviceIDValue = (TextView) findViewById(R.id.deviceIDValue);
        androidVersionValue = (TextView) findViewById(R.id.androidVersionValue);
        apiValue = (TextView) findViewById(R.id.apiValue);


        //get/set date and time

        //get/set Seller App build value
        buildValue.setText(getSellerAppBuild());
        //get/set device model value
        modelValue.setText(DeviceName.getDeviceName());
        //get/set device ID value
        deviceIDValue.setText(Settings.Secure.getString(getContentResolver(), "bluetooth_name"));
        //get/set Android version value
        androidVersionValue.setText(android.os.Build.VERSION.RELEASE);
        //get/set API level value
        apiValue.setText(android.os.Build.VERSION.SDK);
    }

    private String getSellerAppBuild() {
        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo("com.amazon.sellermobile.android", 0);
        } catch (PackageManager.NameNotFoundException e) {}
        return pinfo.versionName;
    }
}
