package com.globant.equattrocchio.domain.service;

import java.util.Map;

import io.reactivex.Observer;

public interface ImagesServices {

    void getLatestImages(Observer<Map<Integer, String>> observer);
}
