package com.khafonline.phoenix4.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.khafonline.phoenix4.core.Constants;


/**
 * Created by Hossein on 06/02/2018.
 */



public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command = "";
        command = "CREATE TABLE " + CategoryRepository.table_name + " ( " +
                //ClassRepository.ClassID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                CategoryRepository.category_id + " INTEGER ," +
                CategoryRepository.title + " TEXT ," +
                CategoryRepository.parent_id + " INTEGER ," +
                CategoryRepository.thumbnail + " TEXT ," +
                CategoryRepository.priority + " INTEGER )";
        db.execSQL(command);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String command = "";
//            command = "CREATE TABLE " + ProductRepository.table_name + " ( " +
//                    //ClassRepository.ClassID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
//                    ProductRepository.product_id + " INTEGER ," +
//                    ProductRepository.title + " TEXT ," +
//                    ProductRepository.parent_id + " INTEGER ," +
//                    ProductRepository.thumbnail + " TEXT ," +
//                    ProductRepository.priority + " INTEGER )";
//            db.execSQL(command);
        }
    }
}
