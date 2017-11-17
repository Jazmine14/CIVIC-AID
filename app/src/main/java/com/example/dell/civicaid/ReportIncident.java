package com.example.dell.civicaid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class ReportIncident extends AppCompatActivity {

    public static final int CAMERA_REQUEST=10;
    private int REQUEST_CODE=30;


    Button submitReport1;
    EditText name1,contact1,information1;
    ImageView camera1,gallery1,imgPicture;
    ArrayAdapter<CharSequence> myAdapter;
    Spinner mySpinner;
    String a;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incident);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        submitReport1=(Button)findViewById(R.id.submitReport);
        name1=(EditText)findViewById(R.id.name);
        contact1=(EditText)findViewById(R.id.contact);
        information1=(EditText)findViewById(R.id.information);
        camera1=(ImageView)findViewById(R.id.btnCaptureImage);
        gallery1=(ImageView)findViewById(R.id.galleryButton);
        imgPicture=(ImageView)findViewById(R.id.imgPicture);

        mySpinner=(Spinner)findViewById(R.id.spinner1);

        myAdapter = ArrayAdapter.createFromResource(this,R.array.category,android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                a = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                a = null;

            }
        });

        submitReport1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]
                        {"jazzminedhanju14@gmail.com"});
                i.putExtra(Intent.EXTRA_STREAM,uri);

                i.putExtra(Intent.EXTRA_SUBJECT, "Reporting Incident from CivicAid");
                i.putExtra(Intent.EXTRA_TEXT, "Name : " + name1.getText() + "\nInformation : " + information1.getText()+"\nContact : "+contact1.getText()+"\nCategory : "+a);
                try {
                    startActivity(Intent.createChooser(i, "Choose app to Report an incident (prefer Gmail) "));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no clients installed for sending the Report", Toast.LENGTH_SHORT).show();
                }

            }
        });


        gallery1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);
            }
        });


    }

    public void btnTakePhotoClicked(View v){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

             uri = data.getData();

            try{

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imgPicture.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(),"Picture has been selected",Toast.LENGTH_LONG).show();
            }catch (IOException e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Something went wrong while choosing photos",Toast.LENGTH_LONG).show();
            }
        }

         else if (requestCode == CAMERA_REQUEST) {
            //we are hearing back from the camera
            Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
            //at this point, we have the image from the camera
            imgPicture.setImageBitmap(cameraImage);
        }


    }
}
