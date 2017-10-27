package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
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
        final List<String> imagesUrl = new ArrayList<>(imagesMap.values());
        final List<Integer> imagesIds = new ArrayList<>(imagesMap.keySet());
        final String imageUrl = imagesUrl.get(position);
        final Integer imageId = imagesIds.get(position);
        Glide.with(mContext).load(imageUrl).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(imageId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesMap.size();
    }

    private void showPopupMenu(Integer id) {
        DialogFragment customDialog = CustomDialog.newInstance(id);
        AppCompatActivity activity = (AppCompatActivity) mContext;
        customDialog.show(activity.getSupportFragmentManager(), "ImagesFragment");
    }
}
