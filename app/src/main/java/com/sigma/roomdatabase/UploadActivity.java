package com.sigma.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UploadActivity extends BaseActivity {

    private EditText title, image, date, rating;
    private Button button;
    private String movieTitle, poster, releaseDate, movieRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        title = findViewById(R.id.movieName_Id);
        image = findViewById(R.id.movieUrl_Id);
        date = findViewById(R.id.releaseDate_Id);
        rating = findViewById(R.id.movieRating_Id);
        button = findViewById(R.id.button_Id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movieTitle = title.getText().toString().trim();
                poster = image.getText().toString();
                releaseDate = date.getText().toString();
                movieRating = rating.getText().toString();

                MovieList movieList = new MovieList(movieTitle, releaseDate, poster, movieRating);

                myDB.myDAO().dataInsert(movieList);

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(UploadActivity.this, "Done", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}