package com.example.movieapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.movieapp.R;

import com.example.movieapp.activity.LoginActivity;
import com.example.movieapp.activity.MainActivity;
import com.example.movieapp.databinding.FragmentMoreBinding;
import com.example.movieapp.model.InfoUser;


public class MoreFragment extends Fragment {

    InfoUser infoUser;

    FragmentMoreBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoreBinding.inflate(inflater, container, false);

        InfoUser infoUser1 = InfoUser.getInstance();
        String imageUrl = infoUser1.getImg() ;
        Log.e("url", imageUrl);
//        String imageUrl;


//        if ( infoUser.getImg() != null) {
//            String imageUrl = "\"" + infoUser.getImg() + "\"";
//            Log.e("url", imageUrl);
//
            // Sử dụng Glide để tải ảnh từ URL vào ImageView

        Glide.with(this)
                .load(imageUrl)
                .into(binding.imvAvatar);
//        } else {
//            Log.e("InfoUser", "InfoUser is null or image URL is null");
//        }

//
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        backButtonOnClick();
    }

    private void backButtonOnClick() {
        binding.imvBack.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.frameLayout, new HomeFragment()).commit();
        });
        binding.lnlAcccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoUser infoUser = InfoUser.getInstance();
                String name = infoUser.getName();
                String email = infoUser.getEmail() ; // Replace with actual email
                String phone = infoUser.getPhone(); // Replace with actual phone

//                EditAccountInfoFragment editAccountInfoFragment = EditAccountInfoFragment.newInstance(name,email, phone);
//
//
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frameLayout, editAccountInfoFragment).commit();
                EditAccountInfoFragment fragment = new EditAccountInfoFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        binding.lnlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finishAffinity();

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Xóa tất cả dữ liệu trong SharedPreferences
                editor.apply();


//                LogoutDialogFragment logoutDialogFragment = new LogoutDialogFragment();
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.frameLayout, logoutDialogFragment).commit();

            }
        });


        binding.lnlWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo Fragment
                WatchListFragment fragment = new WatchListFragment();

                // Truy cập FragmentManager từ FragmentActivity hoặc AppCompatActivity và thực hiện giao dịch
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, fragment); // frameLayout là id của container cho Fragment
                transaction.addToBackStack(null); // (Optional) Đưa Fragment vào Stack để quay lại
                transaction.commit();
            }
        });
    }

}