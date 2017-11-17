package com.example.dell.civicaid;

/**
 * Created by Dell on 11/16/2017.
 */
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

 public class DBHelper3 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ercontacts.db";
    public static final String TABLE_NAME = "ercontact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "NOM";

    public DBHelper3(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,NOM INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String number){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,number);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return res;
    }
}



