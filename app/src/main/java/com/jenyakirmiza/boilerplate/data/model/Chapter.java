package com.jenyakirmiza.boilerplate.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue
public abstract class Chapter implements Parcelable {
    public abstract String title();
    public abstract String content();
    //public abstract String imageUrl();
    //public abstract String authorDescription();
    public abstract int id();


    public static Chapter create(String title,String content, int id) {
        return new AutoValue_Chapter(title,content,id);
    }

    public static TypeAdapter<Chapter> typeAdapter(Gson gson) {
        return new AutoValue_Chapter.GsonTypeAdapter(gson);
    }

}
