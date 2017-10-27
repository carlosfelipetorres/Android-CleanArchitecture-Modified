package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL = "http://splashbase.co/";

    @Override
    public void getLatestImages(final Observer<Map<Integer, String>> observer) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api = retrofit.create(SplashbaseApi.class);

        Call<Result> call = api.getImages();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (observer != null) {
                    Map<Integer, String> images = new HashMap<>();
                    for (Image image : response.body().getImages()) {
                        images.put(image.getId(), image.getUrl());
                    }
                    observer.onNext(images);
                    observer.onComplete();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                //todo: update the UI with a connection error message
            }
        });


    }

    @Override
    public void getSingleImage(final Observer<String> observer, Integer imageId) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api = retrofit.create(SplashbaseApi.class);

        Call<Image> call = api.getImageById(imageId);

        call.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                if (observer != null) {
                    observer.onNext(response.body().getUrl());
                    observer.onComplete();
                }
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                observer.onError(t);
            }
        });


    }
}
