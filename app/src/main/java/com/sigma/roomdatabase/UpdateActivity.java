package com.sigma.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends BaseActivity {

    private EditText title, image, date, rating;
    private Button button;
    private String  movieTitle, poster, releaseDate, movieRating;
    private int serialNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.updateMovieName_Id);
        image = findViewById(R.id.updateMovieUrl_Id);
        date = findViewById(R.id.updateReleaseDate_Id);
        rating = findViewById(R.id.updateMovieRating_Id);
        button = findViewById(R.id.updateButton_Id);


        Intent intent = getIntent();

        serialNo = intent.getIntExtra("serial", 1);
        movieTitle = intent.getStringExtra("title");
        poster = intent.getStringExtra("poster");
        releaseDate = intent.getStringExtra("date");
        movieRating = intent.getStringExtra("rating");

        title.setText(movieTitle);
        image.setText(poster);
        date.setText(releaseDate);
        rating.setText(movieRating);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleText = title.getText().toString();
                String imageText = image.getText().toString();
                String dateText = date.getText().toString();
                String ratingText = rating.getText().toString();


                MovieList movieList = new MovieList();
                movieList.setSerial(serialNo);
                movieList.setMoveTitle(titleText);
                movieList.setMoviePic(imageText);
                movieList.setReleaseDate(dateText);
                movieList.setRating(ratingText);
                myDB.myDAO().dataUpdate(movieList);
                Toast.makeText(UpdateActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        });

    }
}