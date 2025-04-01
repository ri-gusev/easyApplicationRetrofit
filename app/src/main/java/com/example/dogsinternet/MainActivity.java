package com.example.dogsinternet;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

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

                    //тк string - иммутабельный мы используем StringBuilder и считываем json в Stringbuilder
                    StringBuilder stringBuilder = new StringBuilder();
                    String result;
                    do {
                        result = bufferedReader.readLine();
                        if (result != null){
                            stringBuilder.append(result);
                        }
                    } while (result != null);

                    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                    String message = jsonObject.getString("message");
                    String status = jsonObject.getString("status");

                    DogImage dogImage = new DogImage(message, status);

                    Log.d("MainActivity", dogImage.toString());
                } catch (Exception e) {
                    Log.d("MainActivity", "incorrect url");
                }
            }
        }).start();
    }
}