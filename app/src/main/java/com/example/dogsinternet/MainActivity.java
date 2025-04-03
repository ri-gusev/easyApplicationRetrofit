package com.example.dogsinternet;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button buttonLoadImage;
    private ProgressBar progressBar;

    private static final String TAG = "MainActivity";

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.loadDogImage();

        viewModel.getDogImage().observe(this, new Observer<DogImage>() {
            @Override
            public void onChanged(DogImage dogImage) {
                //с помощью библиотки Glide загружаем картинку в ImageView
                Glide.with(MainActivity.this)
                        .load(dogImage.getMessage())
                        .into(imageView);
            }
        });

        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loading) {
                if (loading) {
                    progressBar.setVisibility(VISIBLE);
                } else {
                    progressBar.setVisibility(GONE);
                }
            }
        });

        viewModel.getIsNoInternet().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isNoInternet) {
                if (isNoInternet) {
                    Toast.makeText(
                            MainActivity.this,
                            R.string.please_turn_on_internet,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loadDogImage();
            }
        });
    }

    private void initViews(){
        imageView = findViewById(R.id.ImageViewDog);
        buttonLoadImage = findViewById(R.id.ButtonLoadNextImage);
        progressBar = findViewById(R.id.ProgressBar);
    }
}