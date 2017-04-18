/*
 * @author Ivan Mwiruki
 */

package com.ivanmwiruki.mtqaticketinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;
import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {

    //define variables for the textview widgets that will be populated with data
    private TextView dateAndTime;
    private TextView buildValue;
    private TextView modelValue;
    private TextView deviceIDValue;
    private TextView androidVersionValue;
    private TextView apiValue;
    private TextView wifiNetworkValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mtqaticketinfo_main);

        //get references to the textview widgets
        dateAndTime = (TextView) findViewById(R.id.dateAndTime);
        buildValue = (TextView) findViewById(R.id.buildValue);
        modelValue = (TextView) findViewById(R.id.modelValue);
        deviceIDValue = (TextView) findViewById(R.id.deviceIDValue);
        androidVersionValue = (TextView) findViewById(R.id.androidVersionValue);
        apiValue = (TextView) findViewById(R.id.apiValue);
        wifiNetworkValue = (TextView) findViewById(R.id.wifiNetworkValue);


        //get/set textview widget values
        dateAndTime.setText(DateFormat.getDateTimeInstance().format(new Date()));
        buildValue.setText(getSellerAppBuild());
        modelValue.setText(getModelValue());
        deviceIDValue.setText(Settings.Secure.getString(getContentResolver(), "bluetooth_name"));
        androidVersionValue.setText(android.os.Build.VERSION.RELEASE);
        apiValue.setText(android.os.Build.VERSION.SDK);
        wifiNetworkValue.setText(getWifiName(this));
    }

    private String getSellerAppBuild() {
        PackageInfo pinfo = null;
        try {
            pinfo = getPackageManager().getPackageInfo("com.amazon.sellermobile.android", PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {}

        return pinfo.versionName;
    }

    private String getModelValue() {
        String manufacturer = android.os.Build.MANUFACTURER;
        String capitalManufacturer = manufacturer.substring(0, 1).toUpperCase() + manufacturer.substring(1);
        return capitalManufacturer + " " + DeviceName.getDeviceName();
    }

    private String getWifiName(Context context) {
        WifiManager manager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (manager.isWifiEnabled()) {
            WifiInfo wifiInfo = manager.getConnectionInfo();
            if (wifiInfo != null) {
                NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                    return wifiInfo.getSSID();
                }
            }
        }
        return "Not connected to wifi";
    }
}
