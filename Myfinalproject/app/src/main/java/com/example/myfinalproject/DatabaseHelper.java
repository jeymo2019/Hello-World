package com.example.myfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_NAME = "students_table";
    public static final String COL1 = "Id";
    public static final String COL2 = "Name";
    public static final String COL3 = "Department";
    public static final String COL4 = "Marks";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table " + TABLE_NAME + "(Id Integer PRIMARY KEY AUTOINCREMENT, Name Text, Department Text, Marks Integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name, String Department, String Marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Name);
        contentValues.put(COL3, Department);
        contentValues.put(COL4, Marks);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }

    public ArrayList<StudentModel> getAllStudents() {
        ArrayList<StudentModel> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);

        if (res.moveToFirst()) {
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setId(res.getString(0));
                studentModel.setName(res.getString(1));
                studentModel.setDepartment(res.getString(2));
                studentModel.setMarks(res.getString(3));
                arrayList.add(studentModel);
            } while (res.moveToNext());
        }
        return arrayList;
    }

    public void delete(String ID){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME,"ID="+ID,null);
        sqLiteDatabase.close();
    }

    public boolean updateData(String Id, String Name, String Department, String Marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, Id);
        contentValues.put(COL2, Name);
        contentValues.put(COL3, Department);
        contentValues.put(COL4, Marks);
        db.update(TABLE_NAME, contentValues, "Id = ?", new String[]{Id});
        return true;


    }
}

