package com.example.retrofitutspb.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitutspb.R;
import com.example.retrofitutspb.data.response.SearchResponse;
import com.example.retrofitutspb.data.response.SearchUser;
import com.example.retrofitutspb.data.retrofit.ApiConfig;
import com.example.retrofitutspb.data.retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view); // Inisialisasi RecyclerView di sini

        ApiService apiService = ApiConfig.getApiService();
        Call<SearchResponse> call = apiService.searchUsers("Rahmat");

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SearchUser> users = response.body().getUsers();
                    if (users.isEmpty()) {
                        Toast.makeText(MainActivity.this, "No users found", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter = new SearchAdapter(users);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Untuk menyimpan data yang penting saat perubahan konfigurasi
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Misalnya, menyimpan posisi item di RecyclerView
        if (recyclerView != null && recyclerView.getLayoutManager() != null) {
            int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            outState.putInt("position", position);
        }
    }

    // Untuk memulihkan data setelah perubahan konfigurasi
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Misalnya, memulihkan posisi item di RecyclerView
        int position = savedInstanceState.getInt("position", 0);
        if (recyclerView != null && recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().scrollToPosition(position);
        }
    }
}