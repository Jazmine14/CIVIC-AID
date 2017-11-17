package com.example.dell.civicaid;

        import android.content.Intent;
        import android.database.Cursor;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

public class Mygroup extends AppCompatActivity {


    DBHelper3 help3;

    private static final int RESULT_PICK_CONTACT = 1;
    private static final int RESULT_PICK_CONTACT2 = 2;
    private static final int RESULT_PICK_CONTACT3 = 3;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;

    EditText name1,name2,name3,number1,number2,number3;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygroup);
        help3=new DBHelper3(this);
        textView1 = (TextView) findViewById(R.id.TFEname1);
        textView2 = (TextView) findViewById(R.id.TFEnumber1);
        textView3 = (TextView) findViewById(R.id.TFEname2);
        textView4 = (TextView) findViewById(R.id.TFEnumber2);
        textView5 = (TextView) findViewById(R.id.TFEname3);
        textView6 = (TextView) findViewById(R.id.TFEnumber3);


        name1=(EditText)findViewById(R.id.TFEname1);
        name2=(EditText)findViewById(R.id.TFEname2);
        name3=(EditText)findViewById(R.id.TFEname3);
        number1=(EditText)findViewById(R.id.TFEnumber1);
        number2=(EditText)findViewById(R.id.TFEnumber2);
        number3=(EditText)findViewById(R.id.TFEnumber3);
        Save=(Button)findViewById(R.id.BSave);
        save();
    }


    public void pickContact(View v)
    {
        if (v.getId()==R.id.BContact1) {
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
        }

        if (v.getId()==R.id.BContact2){
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT2);
        }
        if (v.getId()==R.id.BContact3){
            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT3);
        }

    }

    public void save(){
        Save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        help3.insertData(name1.getText().toString(),
                                number1.getText().toString());
                        help3.insertData(name2.getText().toString(),
                                number2.getText().toString());
                        help3.insertData(name3.getText().toString(),
                                number3.getText().toString());
                    }
                }
        );



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
                case RESULT_PICK_CONTACT2:
                    contactPicked1(data);
                    break;
                case RESULT_PICK_CONTACT3:
                    contactPicked2(data);
                    break;
            }

        } else {
            Log.e("Mygroup", "Failed to pick contact");
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            textView1.setText(name);
            textView2.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void contactPicked1(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            textView3.setText(name);
            textView4.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void contactPicked2(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null ;
            String name = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();

            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            textView5.setText(name);
            textView6.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
