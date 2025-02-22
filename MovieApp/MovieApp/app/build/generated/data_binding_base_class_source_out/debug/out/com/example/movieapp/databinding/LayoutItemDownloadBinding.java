// Generated by view binder compiler. Do not edit!
package com.example.movieapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.movieapp.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutItemDownloadBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView btnPlay;

  @NonNull
  public final MaterialCardView cvMovieDownload;

  @NonNull
  public final ImageView deleteBtn;

  @NonNull
  public final MaterialCardView filterButton;

  @NonNull
  public final ImageView imvMovie;

  @NonNull
  public final LinearLayout lnMovieInfo;

  @NonNull
  public final TextView runtimeTv;

  @NonNull
  public final TextView sizeTV;

  @NonNull
  public final TextView tvTitle;

  private LayoutItemDownloadBinding(@NonNull LinearLayout rootView, @NonNull ImageView btnPlay,
      @NonNull MaterialCardView cvMovieDownload, @NonNull ImageView deleteBtn,
      @NonNull MaterialCardView filterButton, @NonNull ImageView imvMovie,
      @NonNull LinearLayout lnMovieInfo, @NonNull TextView runtimeTv, @NonNull TextView sizeTV,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnPlay = btnPlay;
    this.cvMovieDownload = cvMovieDownload;
    this.deleteBtn = deleteBtn;
    this.filterButton = filterButton;
    this.imvMovie = imvMovie;
    this.lnMovieInfo = lnMovieInfo;
    this.runtimeTv = runtimeTv;
    this.sizeTV = sizeTV;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutItemDownloadBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutItemDownloadBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_item_download, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutItemDownloadBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnPlay;
      ImageView btnPlay = ViewBindings.findChildViewById(rootView, id);
      if (btnPlay == null) {
        break missingId;
      }

      id = R.id.cvMovieDownload;
      MaterialCardView cvMovieDownload = ViewBindings.findChildViewById(rootView, id);
      if (cvMovieDownload == null) {
        break missingId;
      }

      id = R.id.deleteBtn;
      ImageView deleteBtn = ViewBindings.findChildViewById(rootView, id);
      if (deleteBtn == null) {
        break missingId;
      }

      id = R.id.filterButton;
      MaterialCardView filterButton = ViewBindings.findChildViewById(rootView, id);
      if (filterButton == null) {
        break missingId;
      }

      id = R.id.imvMovie;
      ImageView imvMovie = ViewBindings.findChildViewById(rootView, id);
      if (imvMovie == null) {
        break missingId;
      }

      id = R.id.lnMovieInfo;
      LinearLayout lnMovieInfo = ViewBindings.findChildViewById(rootView, id);
      if (lnMovieInfo == null) {
        break missingId;
      }

      id = R.id.runtimeTv;
      TextView runtimeTv = ViewBindings.findChildViewById(rootView, id);
      if (runtimeTv == null) {
        break missingId;
      }

      id = R.id.sizeTV;
      TextView sizeTV = ViewBindings.findChildViewById(rootView, id);
      if (sizeTV == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new LayoutItemDownloadBinding((LinearLayout) rootView, btnPlay, cvMovieDownload,
          deleteBtn, filterButton, imvMovie, lnMovieInfo, runtimeTv, sizeTV, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
