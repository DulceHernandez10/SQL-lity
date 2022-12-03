package com.example.ejemplo1_bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE alumno(numcontrol INTEGER PRIMARY KEY, nombre TEXT, semestre INTEGER, carrera TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists alumno");
        sqLiteDatabase.execSQL("CREATE TABLE alumno(numcontrol INTEGER PRIMARY KEY, nombre TEXT, semestre INTEGER, carrera TEXT)");


    }
}
