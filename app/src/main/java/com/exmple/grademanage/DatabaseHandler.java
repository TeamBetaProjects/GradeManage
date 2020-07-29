package com.exmple.grademanage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GradeDatabase.db";
    public static final String GRADE_TABLE_NAME = "grade";
    public static final String GRADE_COLUMN_ID = "id";
    public static final String GRADE_COLUMN_FIRSTNAME = "firstname";
    public static final String GRADE_COLUMN_LASTNAME = "lastname";
    public static final String GRADE_COLUMN_COURSE = "course";
    public static final String GRADE_COLUMN_CREDITS = "credits";
    public static final String GRADE_COLUMN_MARKS = "marks";

    public DatabaseHandler(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table grade ( id INTEGER PRIMARY KEY AUTOINCREMENT, firstname text not null, lastname text not null ,course text not null ,credits INTEGER not null , marks INTEGER not null );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + GRADE_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData (String fName,String lName,String course,Integer credits,Integer marks) {
        boolean isSuccess = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(GRADE_COLUMN_FIRSTNAME,fName);
        content.put(GRADE_COLUMN_LASTNAME,lName);
        content.put(GRADE_COLUMN_COURSE,course);
        content.put(GRADE_COLUMN_CREDITS,credits);
        content.put(GRADE_COLUMN_MARKS,marks);
        isSuccess = db.insert(GRADE_TABLE_NAME,null,content) > 0;
        db.close();
        return isSuccess;
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from grade",null);
    }

    public Cursor getDataByProgrammeId(String s) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("select * from grade where " + GRADE_COLUMN_COURSE + " = '" + s + "'",null);
    }

    public Cursor getDataById(String s) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String str = "select * from grade where id = " + s;
        return sqLiteDatabase.rawQuery(str ,null);
    }

    public void updateData(int id, String firstName, String lastName, String course, int credits, int marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update grade set firstname = '" + firstName + "', lastname = '" + lastName + "', course = '" + course + "', credits = " + credits + ", marks = " + marks + " where id = " + id);
    }

    public void deleteData(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from grade where id = " + s);
    }
}
