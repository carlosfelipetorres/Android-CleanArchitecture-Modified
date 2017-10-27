package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImagesAdapter;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.tv_incoming_json) TextView tvlabel;
    @BindView(R.id.rv_images_list) RecyclerView rvImagesList;

    private ImagesAdapter adapter;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void showText(String text) {
        tvlabel.setText(text);
    }

    public void showImageList(Map<Integer, String> imagesUrlsList) {
        adapter = new ImagesAdapter(getActivity(), imagesUrlsList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvImagesList.setLayoutManager(mLayoutManager);
        rvImagesList.setItemAnimator(new DefaultItemAnimator());
        rvImagesList.setAdapter(adapter);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void showError() {
        tvlabel.setText(R.string.connection_error);
    }
}
