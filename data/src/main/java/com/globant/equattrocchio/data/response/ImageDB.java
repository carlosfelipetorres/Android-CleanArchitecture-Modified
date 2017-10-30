package com.globant.equattrocchio.data.response;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Table(name = "ImageDB")
public class ImageDB extends Model{
    @Column(name = "imageId")
    public Integer imageId;

    @Column(name = "url")
    public String url;

    @Column(name = "largeUrl")
    public String largeUrl;

    public static List<ImageDB> getAll() {
        // This is how you execute a query
        return new Select()
                .from(ImageDB.class)
                .execute();
    }

    public static List<ImageDB> getImageById(Integer imageId) {
        // This is how you execute a query
        return new Select()
                .from(ImageDB.class)
                .where("imageId = ?", imageId)
                .execute();
    }
}
