package com.example.movieapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.api.ApiService;
import com.example.movieapp.fragment.MovieDetailFragment;
import com.example.movieapp.model.WatchList;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListMovieAdapterHolder> {
    List<WatchList> listDetails;
    Context context;

    public ListMovieAdapter(List<WatchList> listDetails, Context context) {
        this.listDetails = listDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public ListMovieAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_imagengangwatchlist_item, parent, false);
        return new ListMovieAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieAdapterHolder holder, @SuppressLint("RecyclerView") int position) {
        WatchList item = listDetails.get(position);

        String imageUrl = item.getThumbUrl();
        Picasso.get().load(imageUrl).into(holder.imvMovieItem);

        holder.tvName.setText(item.getName());

        holder.imvMovieItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MovieDetailFragment fragment = new MovieDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("slug", item.getSlug());
                fragment.setArguments(bundle);

                // Truy cập FragmentManager từ FragmentActivity hoặc AppCompatActivity và thực hiện giao dịch
                FragmentManager manager = ((FragmentActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment); // frameLayout là id của container cho Fragment
                transaction.addToBackStack(null); // (Optional) Đưa Fragment vào Stack để quay lại
                transaction.commit();

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_delete_download, null);
                builder.setView(dialogView);

                ImageView imageView = dialogView.findViewById(R.id.imageView);
                TextView textView = dialogView.findViewById(R.id.titleTv);
                String imageUrl = item.getThumbUrl();
                Picasso.get().load(imageUrl).into(imageView);
                textView.setText(item.getName());

                AlertDialog alertDialog = builder.create();
                // Load animations
                Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.dialog_delete_watchlist_in);
                Animation fadeOut = AnimationUtils.loadAnimation(context, R.anim.dialog_delete_watchlist_out);
                alertDialog.setOnShowListener(dialog -> dialogView.startAnimation(fadeIn));


                Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
                Button btnConfirm = dialogView.findViewById(R.id.btn_xacnhan);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ApiService.apiServiceLocal.deleteWatchList(item.getSlug(),item.getAccountid()).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                listDetails.remove(position);
                                notifyDataSetChanged();
                                alertDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable throwable) {
                                Toast.makeText(context, "API lỗi, xóa watchlist thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                Window window = alertDialog.getWindow();
                if (window != null) {
                    window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    WindowManager.LayoutParams layoutParams = window.getAttributes();
                    layoutParams.gravity = Gravity.BOTTOM;
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    window.setAttributes(layoutParams);
                }

                alertDialog.show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return listDetails.size();
    }

    public static class ListMovieAdapterHolder extends RecyclerView.ViewHolder {
        ImageView imvMovieItem;
        TextView tvName;
        ImageView btnDelete;
        public ListMovieAdapterHolder(@NonNull View itemView) {
            super(itemView);
            imvMovieItem = itemView.findViewById(R.id.imvMovieItem);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
