package com.example.dell.civicaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    DBHelper helper= new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
    public void next(View v) {

        if(v.getId() == R.id.signup) {

            EditText name=(EditText)findViewById(R.id.uname);
            EditText num=(EditText)findViewById(R.id.TFcontact);
            EditText email=(EditText)findViewById(R.id.email);
            EditText pass=(EditText)findViewById(R.id.pass1);
            EditText pass2=(EditText)findViewById(R.id.pass2);

            String namestr = name.getText().toString();
            String numstr = num.getText().toString();
            String emailstr = email.getText().toString();
            String passstr = pass.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!passstr.equals(pass2str)){

                Toast Tpass = Toast.makeText(signup.this , "Passwords do not match", Toast.LENGTH_SHORT);
                Tpass.show();
            }
            else{
                Contact c=new Contact();
                c.setName(namestr);
                c.setNumber(numstr);
                c.setEmail(emailstr);
                c.setPassword(passstr);

                helper.insertContact(c);
                Toast temp = Toast.makeText(signup.this, "Your account has been created successfully !!", Toast.LENGTH_SHORT);
                temp.show();
                Intent i = new Intent(signup.this, Main2Activity.class);
                startActivity(i);
                Toast.makeText(signup.this," Welcome!!! "+namestr,Toast.LENGTH_LONG).show();
            }

        }

    }


}
