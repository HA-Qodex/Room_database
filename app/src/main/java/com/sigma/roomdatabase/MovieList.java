package com.sigma.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_list")
public class MovieList {

    @PrimaryKey(autoGenerate = true)
    private int serial;

    @ColumnInfo(name = "title")
    private String moveTitle;

    @ColumnInfo(name = "date")
    private String releaseDate;

    @ColumnInfo(name = "picture")
    private String moviePic;

    @ColumnInfo(name = "rating")
    private String rating;

    public MovieList(String moveTitle, String releaseDate, String moviePic, String rating) {
        this.moveTitle = moveTitle;
        this.releaseDate = releaseDate;
        this.moviePic = moviePic;
        this.rating = rating;
    }

    public MovieList() {
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getMoveTitle() {
        return moveTitle;
    }

    public void setMoveTitle(String moveTitle) {
        this.moveTitle = moveTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMoviePic() {
        return moviePic;
    }

    public void setMoviePic(String moviePic) {
        this.moviePic = moviePic;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
