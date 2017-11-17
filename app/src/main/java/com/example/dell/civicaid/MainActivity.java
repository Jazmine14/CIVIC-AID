package com.example.dell.civicaid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper helper=new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void call(View v) {

        if (v.getId() == R.id.B1) {

            EditText a = (EditText) findViewById(R.id.tf1);
            String str = a.getText().toString();

            EditText a1 = (EditText) findViewById(R.id.TFpassword);
            String str2 = a1.getText().toString();

            String Password = helper.searchPassword(str);
            if (str2.equals(Password)) {

                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this," Welcome!!! "+str,Toast.LENGTH_LONG).show();
            } else {
                Toast temp = Toast.makeText(MainActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        if (v.getId() == R.id.B2) {

            Intent i = new Intent(MainActivity.this, signup.class);
            startActivity(i);
        }
    }


}