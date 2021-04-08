package com.sigma.roomdatabase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class BaseActivity extends AppCompatActivity {
    public static MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myDB = Room.databaseBuilder(getApplicationContext(),
                MyDB.class,
                "movieList_db").allowMainThreadQueries().build();

    }
}
