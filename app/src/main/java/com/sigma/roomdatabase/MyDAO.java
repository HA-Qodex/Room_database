package com.sigma.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDAO {

    @Insert
    void dataInsert(MovieList movieList);

    @Update
    void dataUpdate(MovieList movieList);

    @Delete
    void dataDelete(MovieList movieList);

    @Query("select * from movie_list")
    List<MovieList> getAllData();

}
