package com.example.retrofitutspb.data.retrofit;


import com.example.retrofitutspb.data.response.SearchResponse;
import com.example.retrofitutspb.data.response.SearchUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

        @Headers({"Authorization: token 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"})
        @GET("search/users")
        Call<SearchResponse> searchUsers(@Query("q") String query);

        @Headers({"Authorization: token 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"})
        @GET("users/{username}")
        Call<SearchUser> getUser(@Path("username") String username);


    }
