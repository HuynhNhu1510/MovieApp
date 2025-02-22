// Generated by view binder compiler. Do not edit!
package com.example.movieapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.movieapp.R;
import com.google.android.exoplayer2.ui.PlayerView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMoviePlayingBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final PlayerView pvVideoPlaying;

  private FragmentMoviePlayingBinding(@NonNull ConstraintLayout rootView,
      @NonNull PlayerView pvVideoPlaying) {
    this.rootView = rootView;
    this.pvVideoPlaying = pvVideoPlaying;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMoviePlayingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMoviePlayingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_movie_playing, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMoviePlayingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.pvVideoPlaying;
      PlayerView pvVideoPlaying = ViewBindings.findChildViewById(rootView, id);
      if (pvVideoPlaying == null) {
        break missingId;
      }

      return new FragmentMoviePlayingBinding((ConstraintLayout) rootView, pvVideoPlaying);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
