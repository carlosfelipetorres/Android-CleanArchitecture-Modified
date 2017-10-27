package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.GetSingleImageUseCase;

import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class CustomDialog extends DialogFragment {

    int mId;
    ImageView iv;
    static CustomDialog newInstance(int id) {
        CustomDialog f = new CustomDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("id", id);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getInt("id");
        GetSingleImageUseCase getSingleImageUseCase = new GetSingleImageUseCase(new ImagesServicesImpl());
        getSingleImageUseCase.execute(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String imageUrl) {
                Glide.with(getContext()).load(imageUrl).into(iv);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.toString();
            }

            @Override
            public void onComplete() {
            }
        },mId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        View tv = v.findViewById(R.id.tv_id_image);
        ((TextView)tv).setText(String.valueOf(mId));

        iv = v.findViewById(R.id.iv_full_image);


        return v;
    }
}

