package com.example.retrofitutspb.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.retrofitutspb.R;
import com.example.retrofitutspb.data.response.SearchUser;
import com.example.retrofitutspb.data.retrofit.ApiConfig;
import com.example.retrofitutspb.data.retrofit.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class detailactivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailactivity);

        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String username = extras.getString("username");
            ApiService apiService = ApiConfig.getApiService();
            Call<SearchUser> userCall = apiService.getUser(username);

            TextView textView = findViewById(R.id.nama);
            TextView textView2 = findViewById(R.id.username);
            TextView textView3 = findViewById(R.id.bio);
            ImageView imageView = findViewById(R.id.gambar);

            showLoading(true);
            userCall.enqueue(new Callback<SearchUser>() {
                @Override
                public void onResponse(Call<SearchUser> call, Response<SearchUser> response) {
                    if (response.isSuccessful()){
                        showLoading(false);
                        SearchUser user = response.body();
                        if (user != null){
                            String name = "Name: " + user.getName();
                            String usernames = "Username: " + user.getUsername();
                            String bio = "Bio: " + user.getBio();
                            String gambar = user.getAvatarUrl();

                            textView.setText(name);
                            textView2.setText(usernames);
                            textView3.setText(bio);
                            Picasso.get().load(gambar).into(imageView);
                        }else {
                            Toast.makeText(detailactivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SearchUser> call, Throwable t) {
                    Toast.makeText(detailactivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}