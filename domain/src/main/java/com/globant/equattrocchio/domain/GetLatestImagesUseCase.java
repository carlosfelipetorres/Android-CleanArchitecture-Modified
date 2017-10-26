package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

public class GetLatestImagesUseCase extends UseCase<Map<Integer, String>,Void> {

    private ImagesServices imagesServices;

    public GetLatestImagesUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<Map<Integer, String>> observer, Void aVoid) {
        imagesServices.getLatestImages(observer);
    }
}
