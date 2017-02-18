package com.codepath.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Saranu on 2/15/17.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}