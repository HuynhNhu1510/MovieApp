package com.example.movieapp.api;

import com.example.movieapp.model.MovieDetail;
import com.example.movieapp.model.MovieList;
import com.example.movieapp.model.MovieSearch;
import com.example.movieapp.model.WatchList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-đd HH:mm:ss").create();

    //api dữ liệu phim từ kkphim.vip
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("https://phimapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("phim/{slug}")
    Call<MovieDetail> getMovieBySlug(@Path("slug") String slug);
    // https://phimapi.com/v1/api/tim-kiem?keyword={Từ khóa}&limit={number}
    @GET("v1/api/tim-kiem")
    Call<MovieSearch> getMovieSearch(@Query("keyword") String keyword, @Query("limit") int limit);
    //https://phimapi.com/danh-sach/phim-moi-cap-nhat?page=1 10 movie/page
    @GET("danh-sach/phim-moi-cap-nhat")
    Call<MovieList> getMovieList(@Query("page") int page);
    //https://phimapi.com/v1/api/danh-sach/phim-le?page=1
    @GET("v1/api/danh-sach/{danhsach}")
    Call<MovieSearch> getMovieListCategory(@Path("danhsach") String danhsach, @Query("page") int page);

    //api du liệu danh sách phim theo tài khoản
    ApiService apiServiceLocal = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @GET("watchlist/{slug}")
    Call<WatchList> getWatchListBySlug(@Path("slug") String slug);
    @GET("watchlist/all")
    Call<List<WatchList>> getAllWatchList();
    @GET("watchlist/all/{accountid}")
    Call<List<WatchList>> getAllWatchListByAccount(@Path("accountid") String accountid);
    @POST("watchlist/")
    Call<WatchList> addWatchList(@Body WatchList watchList);
    @PUT("watchlist/{slug}")
    Call<WatchList> updateWatchList(@Path("slug") String slug, @Body WatchList watchList);
    @DELETE("watchlist/{slug}/{accountid}")
    Call<Void> deleteWatchList(@Path("slug") String slug,@Path("accountid") String accountid);
}
