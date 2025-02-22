// Generated by view binder compiler. Do not edit!
package com.example.movieapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.widget.NestedScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.movieapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentMoreBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final ImageView arrowLogout;

  @NonNull
  public final ImageView arrowOpenAcc;

  @NonNull
  public final ImageView arrowOpenNoti;

  @NonNull
  public final ImageView arrowOpenWatch;

  @NonNull
  public final CheckBox btnModeToggle;

  @NonNull
  public final Guideline guideline1;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final ImageView imvAvatar;

  @NonNull
  public final AppCompatImageView imvBack;

  @NonNull
  public final AppCompatImageView imvLogoApp;

  @NonNull
  public final LinearLayout lnlAcccount;

  @NonNull
  public final LinearLayout lnlLogout;

  @NonNull
  public final LinearLayout lnlNotification;

  @NonNull
  public final LinearLayout lnlProfile;

  @NonNull
  public final LinearLayout lnlSettings;

  @NonNull
  public final LinearLayout lnlWatchList;

  @NonNull
  public final ConstraintLayout toolBar;

  @NonNull
  public final TextView tvUserName;

  private FragmentMoreBinding(@NonNull NestedScrollView rootView, @NonNull ImageView arrowLogout,
      @NonNull ImageView arrowOpenAcc, @NonNull ImageView arrowOpenNoti,
      @NonNull ImageView arrowOpenWatch, @NonNull CheckBox btnModeToggle,
      @NonNull Guideline guideline1, @NonNull Guideline guideline2, @NonNull ImageView imvAvatar,
      @NonNull AppCompatImageView imvBack, @NonNull AppCompatImageView imvLogoApp,
      @NonNull LinearLayout lnlAcccount, @NonNull LinearLayout lnlLogout,
      @NonNull LinearLayout lnlNotification, @NonNull LinearLayout lnlProfile,
      @NonNull LinearLayout lnlSettings, @NonNull LinearLayout lnlWatchList,
      @NonNull ConstraintLayout toolBar, @NonNull TextView tvUserName) {
    this.rootView = rootView;
    this.arrowLogout = arrowLogout;
    this.arrowOpenAcc = arrowOpenAcc;
    this.arrowOpenNoti = arrowOpenNoti;
    this.arrowOpenWatch = arrowOpenWatch;
    this.btnModeToggle = btnModeToggle;
    this.guideline1 = guideline1;
    this.guideline2 = guideline2;
    this.imvAvatar = imvAvatar;
    this.imvBack = imvBack;
    this.imvLogoApp = imvLogoApp;
    this.lnlAcccount = lnlAcccount;
    this.lnlLogout = lnlLogout;
    this.lnlNotification = lnlNotification;
    this.lnlProfile = lnlProfile;
    this.lnlSettings = lnlSettings;
    this.lnlWatchList = lnlWatchList;
    this.toolBar = toolBar;
    this.tvUserName = tvUserName;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMoreBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMoreBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_more, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMoreBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.arrowLogout;
      ImageView arrowLogout = ViewBindings.findChildViewById(rootView, id);
      if (arrowLogout == null) {
        break missingId;
      }

      id = R.id.arrowOpenAcc;
      ImageView arrowOpenAcc = ViewBindings.findChildViewById(rootView, id);
      if (arrowOpenAcc == null) {
        break missingId;
      }

      id = R.id.arrowOpenNoti;
      ImageView arrowOpenNoti = ViewBindings.findChildViewById(rootView, id);
      if (arrowOpenNoti == null) {
        break missingId;
      }

      id = R.id.arrowOpenWatch;
      ImageView arrowOpenWatch = ViewBindings.findChildViewById(rootView, id);
      if (arrowOpenWatch == null) {
        break missingId;
      }

      id = R.id.btnModeToggle;
      CheckBox btnModeToggle = ViewBindings.findChildViewById(rootView, id);
      if (btnModeToggle == null) {
        break missingId;
      }

      id = R.id.guideline1;
      Guideline guideline1 = ViewBindings.findChildViewById(rootView, id);
      if (guideline1 == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.imvAvatar;
      ImageView imvAvatar = ViewBindings.findChildViewById(rootView, id);
      if (imvAvatar == null) {
        break missingId;
      }

      id = R.id.imvBack;
      AppCompatImageView imvBack = ViewBindings.findChildViewById(rootView, id);
      if (imvBack == null) {
        break missingId;
      }

      id = R.id.imvLogoApp;
      AppCompatImageView imvLogoApp = ViewBindings.findChildViewById(rootView, id);
      if (imvLogoApp == null) {
        break missingId;
      }

      id = R.id.lnlAcccount;
      LinearLayout lnlAcccount = ViewBindings.findChildViewById(rootView, id);
      if (lnlAcccount == null) {
        break missingId;
      }

      id = R.id.lnlLogout;
      LinearLayout lnlLogout = ViewBindings.findChildViewById(rootView, id);
      if (lnlLogout == null) {
        break missingId;
      }

      id = R.id.lnlNotification;
      LinearLayout lnlNotification = ViewBindings.findChildViewById(rootView, id);
      if (lnlNotification == null) {
        break missingId;
      }

      id = R.id.lnlProfile;
      LinearLayout lnlProfile = ViewBindings.findChildViewById(rootView, id);
      if (lnlProfile == null) {
        break missingId;
      }

      id = R.id.lnlSettings;
      LinearLayout lnlSettings = ViewBindings.findChildViewById(rootView, id);
      if (lnlSettings == null) {
        break missingId;
      }

      id = R.id.lnlWatchList;
      LinearLayout lnlWatchList = ViewBindings.findChildViewById(rootView, id);
      if (lnlWatchList == null) {
        break missingId;
      }

      id = R.id.toolBar;
      ConstraintLayout toolBar = ViewBindings.findChildViewById(rootView, id);
      if (toolBar == null) {
        break missingId;
      }

      id = R.id.tvUserName;
      TextView tvUserName = ViewBindings.findChildViewById(rootView, id);
      if (tvUserName == null) {
        break missingId;
      }

      return new FragmentMoreBinding((NestedScrollView) rootView, arrowLogout, arrowOpenAcc,
          arrowOpenNoti, arrowOpenWatch, btnModeToggle, guideline1, guideline2, imvAvatar, imvBack,
          imvLogoApp, lnlAcccount, lnlLogout, lnlNotification, lnlProfile, lnlSettings,
          lnlWatchList, toolBar, tvUserName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
