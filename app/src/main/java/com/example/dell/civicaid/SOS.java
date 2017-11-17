package com.example.dell.civicaid;

        import android.database.Cursor;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.TextView;

public class SOS extends AppCompatActivity {

    DBHelper3 help4;
    TextView t1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        help4=new DBHelper3(this);
        t1=(TextView)findViewById(R.id.textView10);
    }
    public void sos(View v){
        Cursor res=help4.getAllData();
        String s[]=new String[10];
        int i=0;
        while(res.moveToNext()){
            s[i]=res.getString(2);
            i++;
        }
        t1.setText(s[1]);

    }
}