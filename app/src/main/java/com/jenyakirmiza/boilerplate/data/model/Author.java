package com.jenyakirmiza.boilerplate.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue
public abstract class Author implements Parcelable {
    public abstract String name();
    //public abstract String imageUrl();
    //public abstract String authorDescription();
    public abstract int id();


    public static Author create(String name, int id) {
        return new AutoValue_Author(name, id);
    }


    public static TypeAdapter<Author> typeAdapter(Gson gson) {
        return new AutoValue_Author.GsonTypeAdapter(gson);
    }

}
