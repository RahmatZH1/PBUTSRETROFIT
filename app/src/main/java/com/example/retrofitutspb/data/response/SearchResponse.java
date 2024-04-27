package com.example.retrofitutspb.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("items")
    private List<SearchUser> users;

    public List<SearchUser> getUsers() {
        return users;
    }
}
