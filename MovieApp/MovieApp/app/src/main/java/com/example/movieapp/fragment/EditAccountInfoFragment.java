package com.example.movieapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.text.IDNA;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieapp.CategoryFragment;
import com.example.movieapp.R;
import com.example.movieapp.activity.LoginActivity;
import com.example.movieapp.activity.ResetPasswordActivity;
import com.example.movieapp.api.ApiResponse.User.ChangeInfoRequest;
import com.example.movieapp.api.ApiResponse.User.ResetPassResponse;
import com.example.movieapp.api.ApiServer;
import com.example.movieapp.databinding.FragmentEditAccountInfoBinding;
import com.example.movieapp.model.Check.Check;
import com.example.movieapp.model.InfoUser;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountInfoFragment extends Fragment {

    FragmentEditAccountInfoBinding binding;
    private ImageView imageView;

    private EditText edtphone;
    String oldphone;







    public EditAccountInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        binding = FragmentEditAccountInfoBinding.inflate(inflater, container, false);
        imageView = binding.imvUserAvatar;
        InfoUser infoUser = InfoUser.getInstance();

        String imageUrl = infoUser.getImg() ;
//
        // Sử dụng Glide để tải ảnh từ URL vào ImageView
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);


        // Lấy dữ liệu từ Bundle
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            InfoUser infoUser = InfoUser.getInstance();
//            String email = bundle.getString("email");
//            String name = bundle.getString("name");
//            String phone = bundle.getString("phone");
//            String email = infoUser.getEmail();
//            String name = infoUser.getName();
//            String phone = infoUser.getPhone();
//            binding.editEmail.setText(email);
//            binding.editPhone.setText(phone);
//            binding.editPassword.setText(name);
////            Toast.makeText(getContext(), email, Toast.LENGTH_SHORT).show();
//
//
//        }else{
//            Toast.makeText(getContext(), "bundle is null", Toast.LENGTH_SHORT).show();
//
//        }
        String email = infoUser.getEmail();
        String name = infoUser.getName();
        String phone = infoUser.getPhone();
        binding.editEmail.setText(email);
        binding.editPhone.setText(phone);
        binding.editPassword.setText(name);
        addEvents();

        return binding.getRoot();


    }

    private void addEvents() {

        binding.imvUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
//                // Inflate layout tùy chỉnh vào dialog
//                View dialogView = getLayoutInflater().inflate(R.layout.dialog_change_avatar, null);
//                builder.setView(dialogView);
//
////                // Khởi tạo các views trong dialog layout
//                ImageView imageView1 = dialogView.findViewById(R.id.imvchangeavt);
//                Button button = dialogView.findViewById(R.id.buttonChangeAvatar);
//
//                imageView1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            edtphone = binding.editPhone;


            String editphone = edtphone.getText().toString().trim();

            if(!Check.isValidPhone(editphone)){
                Toast.makeText(getContext(), "Phone is invalid", Toast.LENGTH_SHORT).show();
                return;
            }
            if(editphone.isEmpty()){
                Toast.makeText(getContext(), "Phone is empty", Toast.LENGTH_SHORT).show();
                return;
            }

            InfoUser infoUser = InfoUser.getInstance();
            String token = infoUser.getAccessToken();
            Log.e("token",infoUser.getAccessToken());


                ChangeInfoRequest changeInfoRequest = new ChangeInfoRequest(token,editphone);


            Call<ResetPassResponse> call = ApiServer.apiServer.changeInfo(changeInfoRequest);
            call.enqueue(new Callback<ResetPassResponse>() {
                @Override
                public void onResponse(Call<ResetPassResponse> call, Response<ResetPassResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ResetPassResponse result = response.body();

                        Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                        infoUser.setPhone(editphone);


                        // Khởi tạo Fragment và truyền dữ liệu nếu cần
                        MoreFragment fragment = new MoreFragment();
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.frameLayout, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    } else if (response.code() == 404 || response.code() == 500) {
                        String errorMessage = null;
                        try {
                            // Attempt to parse the body as a String (may throw exceptions)
                            String bodyString = response.errorBody().string();
                            errorMessage = new JSONObject(bodyString).getString("message"); // Example parsing
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).
                                    show();

                        } catch (Exception e) {
                            // Handle parsing or conversion errors gracefully
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResetPassResponse> call, Throwable throwable) {
                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            }
        });
        binding.tvChangePass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InfoUser infoUser = InfoUser.getInstance();

                String email = infoUser.getEmail() ;
                Intent intent = new Intent(new Intent(getContext(), ResetPasswordActivity.class));
                intent.putExtra("email",email);
                startActivity(intent);


            }
        });
    }

    public static EditAccountInfoFragment newInstance(String name,String email, String phone) {
        EditAccountInfoFragment fragment = new EditAccountInfoFragment();
        Bundle args = new Bundle();
        args.putString("name",name);
        args.putString("email", email);
        args.putString("phone", phone);
        fragment.setArguments(args);
        return fragment;
    }
//    private String getGravatarUrl(InfoUser email) {
////        // Chuyển địa chỉ email sang chữ thường và loại bỏ dấu cách xung quanh
////        email = email.toLowerCase().trim();
////        // Tạo mã MD5 từ địa chỉ email
////        String hashEmail = md5(email);
////        // Xây dựng URL của Gravatar
//////        return "https://www.gravatar.com/avatar/" + hashEmail + "?s=200";
//        return "https://lh3.googleusercontent.com/a/ACg8ocJMVkiEMVcMEisj94-sjJ2vgDJVEdoExU9x9coj9RhSPOI1HRE=s432-c-no";
//    }

//    private String md5(String email) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(email.getBytes());
//            byte[] digest = md.digest();
//            StringBuilder sb = new StringBuilder();
//            for (byte b : digest) {
//                sb.append(String.format("%02x", b & 0xff));
//            }
//            return sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return "";
//        }
//    }
//private void openGallery() {
//    // Tạo Intent để mở ứng dụng bộ sưu tập mặc định
//    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//    intent.setType("image/*");
//
//    // Kiểm tra xem có ứng dụng nào có thể xử lý Intent này hay không
//    if (intent.resolveActivity(packageManager) != null) {
//        // Mở bộ sưu tập và chờ phản hồi từ người dùng
//        startActivityForResult(intent, REQUEST_CODE_OPEN_GALLERY);
//    } else {
//        // Không có ứng dụng nào có thể xử lý Intent này
//        Toast.makeText(getContext(), "No app can handle this request.", Toast.LENGTH_SHORT).show();
//    }
//}
    }