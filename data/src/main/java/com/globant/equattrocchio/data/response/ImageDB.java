package com.globant.equattrocchio.data.response;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "ImageDB")
public class ImageDB extends Model{
    @Column(name = "imageId")
    public Integer imageId;

    @Column(name = "url")
    public String url;

    @Column(name = "largeUrl")
    public String largeUrl;
}
