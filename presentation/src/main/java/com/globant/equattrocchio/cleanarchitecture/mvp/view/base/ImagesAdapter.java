package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {

    private Context mContext;
    private HashMap<Integer, String> imagesMap;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.iv_image);
        }
    }


    public ImagesAdapter(Context mContext, Map<Integer, String> imagesMap) {
        this.mContext = mContext;
        this.imagesMap = (HashMap<Integer, String>) imagesMap;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        List<String> imagesUrl = new ArrayList<>(imagesMap.values());
        String imageUrl = imagesUrl.get(position);
        Glide.with(mContext).load(imageUrl).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return imagesMap.size();
    }
}
