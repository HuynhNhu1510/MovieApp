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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.movieapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDownloadBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView btnSearch;

  @NonNull
  public final ImageView image;

  @NonNull
  public final LinearLayout lnEmptyList;

  @NonNull
  public final TextView message;

  @NonNull
  public final RecyclerView rcvDownload;

  @NonNull
  public final TextView title;

  @NonNull
  public final ConstraintLayout toolbar;

  @NonNull
  public final ImageView tvBackButtonLogo;

  @NonNull
  public final TextView tvPageTitle;

  private FragmentDownloadBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView btnSearch,
      @NonNull ImageView image, @NonNull LinearLayout lnEmptyList, @NonNull TextView message,
      @NonNull RecyclerView rcvDownload, @NonNull TextView title, @NonNull ConstraintLayout toolbar,
      @NonNull ImageView tvBackButtonLogo, @NonNull TextView tvPageTitle) {
    this.rootView = rootView;
    this.btnSearch = btnSearch;
    this.image = image;
    this.lnEmptyList = lnEmptyList;
    this.message = message;
    this.rcvDownload = rcvDownload;
    this.title = title;
    this.toolbar = toolbar;
    this.tvBackButtonLogo = tvBackButtonLogo;
    this.tvPageTitle = tvPageTitle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDownloadBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDownloadBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_download, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDownloadBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnSearch;
      ImageView btnSearch = ViewBindings.findChildViewById(rootView, id);
      if (btnSearch == null) {
        break missingId;
      }

      id = R.id.image;
      ImageView image = ViewBindings.findChildViewById(rootView, id);
      if (image == null) {
        break missingId;
      }

      id = R.id.lnEmptyList;
      LinearLayout lnEmptyList = ViewBindings.findChildViewById(rootView, id);
      if (lnEmptyList == null) {
        break missingId;
      }

      id = R.id.message;
      TextView message = ViewBindings.findChildViewById(rootView, id);
      if (message == null) {
        break missingId;
      }

      id = R.id.rcvDownload;
      RecyclerView rcvDownload = ViewBindings.findChildViewById(rootView, id);
      if (rcvDownload == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      id = R.id.toolbar;
      ConstraintLayout toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.tvBackButtonLogo;
      ImageView tvBackButtonLogo = ViewBindings.findChildViewById(rootView, id);
      if (tvBackButtonLogo == null) {
        break missingId;
      }

      id = R.id.tvPageTitle;
      TextView tvPageTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvPageTitle == null) {
        break missingId;
      }

      return new FragmentDownloadBinding((ConstraintLayout) rootView, btnSearch, image, lnEmptyList,
          message, rcvDownload, title, toolbar, tvBackButtonLogo, tvPageTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
