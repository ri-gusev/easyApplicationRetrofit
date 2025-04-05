package com.example.dogsinternet;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("random") //random - это endpoint
                    // ссылки на запрос
    Single<DogImage> loadDogImage();
}
