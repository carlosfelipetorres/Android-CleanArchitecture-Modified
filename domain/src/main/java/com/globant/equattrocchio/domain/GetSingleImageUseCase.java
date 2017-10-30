package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

public class GetSingleImageUseCase extends UseCase<String,Integer> {

    private ImagesServices imagesServices;

    public GetSingleImageUseCase(ImagesServices imagesServices) {
        super();
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<String> observer, Integer imageId) {
        imagesServices.getSingleImage(observer, imageId);
    }
}
