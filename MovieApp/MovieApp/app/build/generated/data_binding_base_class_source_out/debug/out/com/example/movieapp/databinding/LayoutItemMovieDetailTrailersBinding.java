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
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.movieapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutItemMovieDetailTrailersBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CardView cvTrailerMovieMore;

  @NonNull
  public final ImageView imvPlayTrailerVideoBtn;

  @NonNull
  public final TextView tvTrailerDate;

  @NonNull
  public final TextView tvTrailerTitle;

  private LayoutItemMovieDetailTrailersBinding(@NonNull LinearLayout rootView,
      @NonNull CardView cvTrailerMovieMore, @NonNull ImageView imvPlayTrailerVideoBtn,
      @NonNull TextView tvTrailerDate, @NonNull TextView tvTrailerTitle) {
    this.rootView = rootView;
    this.cvTrailerMovieMore = cvTrailerMovieMore;
    this.imvPlayTrailerVideoBtn = imvPlayTrailerVideoBtn;
    this.tvTrailerDate = tvTrailerDate;
    this.tvTrailerTitle = tvTrailerTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutItemMovieDetailTrailersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutItemMovieDetailTrailersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_item_movie_detail_trailers, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutItemMovieDetailTrailersBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cvTrailerMovie_More;
      CardView cvTrailerMovieMore = ViewBindings.findChildViewById(rootView, id);
      if (cvTrailerMovieMore == null) {
        break missingId;
      }

      id = R.id.imvPlayTrailerVideoBtn;
      ImageView imvPlayTrailerVideoBtn = ViewBindings.findChildViewById(rootView, id);
      if (imvPlayTrailerVideoBtn == null) {
        break missingId;
      }

      id = R.id.tvTrailerDate;
      TextView tvTrailerDate = ViewBindings.findChildViewById(rootView, id);
      if (tvTrailerDate == null) {
        break missingId;
      }

      id = R.id.tvTrailerTitle;
      TextView tvTrailerTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTrailerTitle == null) {
        break missingId;
      }

      return new LayoutItemMovieDetailTrailersBinding((LinearLayout) rootView, cvTrailerMovieMore,
          imvPlayTrailerVideoBtn, tvTrailerDate, tvTrailerTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
