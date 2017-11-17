package com.example.dell.civicaid;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class travelsafe extends AppCompatActivity {

    EditText b,h,vNo,iNo,iNo2;

    int MY_PERMISSIONS_REQUEST_SEND_SMS=1;

    String SENT="SMS_SENT";
    String DELIVERED="SMS_DELIVERED";
    PendingIntent sentPI,deliveredPI;
    BroadcastReceiver smsSentReceiver,smsDeliveredReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelsafe);

        b=(EditText)findViewById(R.id.editText6);
        h=(EditText)findViewById(R.id.editText7);
        vNo=(EditText)findViewById(R.id.editText8);
        iNo=(EditText)findViewById(R.id.INumber);
        iNo2=(EditText)findViewById(R.id.INumber2);

        sentPI=PendingIntent.getBroadcast(this,0,new Intent(SENT),0);
        deliveredPI=PendingIntent.getBroadcast(this,0,new Intent(DELIVERED),0);
    }
    @Override
    protected void onResume(){
        super.onResume();
        smsSentReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(travelsafe.this,"SMS sent!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(travelsafe.this,"Generic failure!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(travelsafe.this,"No service!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(travelsafe.this,"Null PDU!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(travelsafe.this,"Radio off!",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        smsDeliveredReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(travelsafe.this,"SMS delivered!",Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(travelsafe.this,"SMS not delivered!",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        registerReceiver(smsSentReceiver,new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver,new IntentFilter(DELIVERED));
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(smsDeliveredReceiver);
        unregisterReceiver(smsSentReceiver);
    }
    public void button(View v){

        String message1=b.getText().toString();
        String telNr=iNo.getText().toString();
        String telNr2=iNo2.getText().toString();
        String message2=h.getText().toString();
        String message3=vNo.getText().toString();
        String message=message1+","+message2+","+message3;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]
                            {Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else
        {
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(telNr,null,message,sentPI,deliveredPI);
            SmsManager sms2=SmsManager.getDefault();
            sms2.sendTextMessage(telNr2,null,message,sentPI,deliveredPI);
        }
    }

}






