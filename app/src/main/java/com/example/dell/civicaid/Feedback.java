package com.example.dell.civicaid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends ActionBarActivity {
    Button b;
    EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        b = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]
                        {"jazzminedhanju14@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from CivicAid");
                i.putExtra(Intent.EXTRA_TEXT, "Name : " + e1.getText() + "\nMessage : " + e2.getText());
                try {
                    startActivity(Intent.createChooser(i, "Choose app to send your feedback"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

