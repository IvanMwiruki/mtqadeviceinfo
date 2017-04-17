package com.ivanmwiruki.mtqaticketinfo;

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


        //set date and time (using network)

        //set Seller App build value
        //buildValue.setText();
        //set device model value
        modelValue.setText(android.os.Build.MODEL);
        //set device ID value
        //deviceIDValue.setText();
        //set Android version value
        //androidVersionValue.setText(System.getProperty("os.version"));
        //set API level value
        //apiValue.setText(android.os.Build.VERSION.SDK_INT);
    }
}
