package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImagesAdapter;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.data.response.ImageDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView implements  LoaderManager.LoaderCallbacks<List<ImageDB>> {

    @BindView(R.id.tv_incoming_json)
    TextView tvlabel;
    @BindView(R.id.rv_images_list)
    RecyclerView rvImagesList;
    @BindView(R.id.fab_refresh)
    FloatingActionButton fabRefresh;

    private ImagesAdapter adapter;

    public ImagesView(AppCompatActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
        activity.getSupportLoaderManager().initLoader(0, null, this).forceLoad();
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
        getActivity().getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    @OnClick(R.id.fab_refresh)
    public void callRefreshBtnPressed() {
        getActivity().getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void showError() {
        tvlabel.setText(R.string.connection_error);
    }

    @Override
    public Loader<List<ImageDB>> onCreateLoader(int id, Bundle args) {
        return new FetchData(getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<ImageDB>> loader, List<ImageDB> data) {
        Map<Integer, String> imagesUrlsList = new HashMap<>();
        for(ImageDB image : data){
            imagesUrlsList.put(image.imageId, image.url);
        }
        showImageList(imagesUrlsList);
    }

    @Override
    public void onLoaderReset(Loader<List<ImageDB>> loader) {
    }

    private static class FetchData extends AsyncTaskLoader<List<ImageDB>> {

        public FetchData(Context context) {
            super(context);
        }

        @Override
        public List<ImageDB> loadInBackground() {
            List<ImageDB> imagesList = ImageDB.getAll();
            return imagesList;
        }

        @Override
        public void deliverResult(List<ImageDB> data) {
            super.deliverResult(data);
        }

    }
}
