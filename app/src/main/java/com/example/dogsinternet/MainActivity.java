package com.example.dogsinternet;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        loadDogImage();
    }

    private void loadDogImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BASE_URL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream(); //побайтово
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream); //посимвольно
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //построчно
                    String result = bufferedReader.readLine();
                    Log.d("MainActivity", result);
                } catch (Exception e) {
                    Log.d("MainActivity", "incorrect url");
                }
            }
        }).start();
    }
}