package com.example.android.shreygarg_deltatask3;

import com.google.gson.annotations.SerializedName;

public class Description {
    String description;
    @SerializedName("url")
    String link;

    public String getDesc() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
