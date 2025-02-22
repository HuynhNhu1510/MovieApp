// Generated by view binder compiler. Do not edit!
package com.example.movieapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.movieapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSimilarBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final RecyclerView rcvListMovieSimilarMore;

  private FragmentSimilarBinding(@NonNull ConstraintLayout rootView,
      @NonNull RecyclerView rcvListMovieSimilarMore) {
    this.rootView = rootView;
    this.rcvListMovieSimilarMore = rcvListMovieSimilarMore;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSimilarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSimilarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_similar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSimilarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.rcvListMovieSimilar_More;
      RecyclerView rcvListMovieSimilarMore = ViewBindings.findChildViewById(rootView, id);
      if (rcvListMovieSimilarMore == null) {
        break missingId;
      }

      return new FragmentSimilarBinding((ConstraintLayout) rootView, rcvListMovieSimilarMore);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
