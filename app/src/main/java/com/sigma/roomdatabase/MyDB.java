package com.sigma.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieList.class}, version = 1)
public abstract class MyDB extends RoomDatabase {

    public abstract MyDAO myDAO();
}
