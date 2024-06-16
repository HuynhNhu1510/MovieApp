package com.example.movieapp.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.adapter.ListMovieAdapter;
import com.example.movieapp.adapter.MovieImageCategoryAdapter;
import com.example.movieapp.api.ApiService;
import com.example.movieapp.databinding.FragmentWatchListBinding;
import com.example.movieapp.model.WatchList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WatchListFragment extends Fragment {

    FragmentWatchListBinding binding;
    MovieImageCategoryAdapter movieImageCategoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentWatchListBinding.inflate(inflater, container,false);

        initUI();

       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initUI() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String accountid = sharedPreferences.getString("accountid", "");



        ApiService.apiServiceLocal.getAllWatchListByAccount(accountid).enqueue(new Callback<List<WatchList>>() {
            @Override
            public void onResponse(Call<List<WatchList>> call, Response<List<WatchList>> response) {
                if (response.isSuccessful()) {
                    List<WatchList> list = response.body();
                    if (list != null && !list.isEmpty()) {
                        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        ListMovieAdapter adapter = new ListMovieAdapter(list, getContext());
                        binding.rcvWatchList.setAdapter(adapter);
                        binding.rcvWatchList.setLayoutManager(manager);
                    } else {
                        binding.tvWatchList.setText("Watch List chưa có phim!");
                    }
                } else {
                    Toast.makeText(getContext(), "API trả về lỗi: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<WatchList>> call, Throwable throwable) {
                Toast.makeText(getContext(), "API lỗi, lấy danh sách watchlist thất bại!", Toast.LENGTH_SHORT).show();
                Log.e("API Failure", "Error: " + throwable.getMessage(), throwable);
            }
        });
    }
}