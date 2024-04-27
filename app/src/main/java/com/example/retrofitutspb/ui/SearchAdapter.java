package com.example.retrofitutspb.ui;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitutspb.R;
import com.example.retrofitutspb.data.response.SearchUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
        private List<SearchUser> users;

        public SearchAdapter(List<SearchUser> users) {
            this.users = users;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchitem, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            SearchUser user = users.get(position);
            holder.usernameTextView.setText(user.getUsername());
            Picasso.get().load(user.getAvatarUrl()).into(holder.avatarImageView);

            holder.itemView.setOnClickListener(click -> {
                Intent intent = new Intent(click.getContext(), detailactivity.class);
                intent.putExtra("Nama ", user.getName());
                intent.putExtra("Nama Pengguna", user.getUsername());
                intent.putExtra("Bio", user.getBio());
                intent.putExtra("gambar", user.getAvatarUrl());
                click.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView avatarImageView;
            TextView usernameTextView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                avatarImageView = itemView.findViewById(R.id.avatar_image_view);
                usernameTextView = itemView.findViewById(R.id.username_text_view);
            }
        }
    }
