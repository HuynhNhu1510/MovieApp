package com.example.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



import com.example.movieapp.CategoryFragment;
import com.example.movieapp.R;
import com.example.movieapp.api.ApiResponse.User.UserResponse;
import com.example.movieapp.api.ApiResponse.login.LoginRequest;
import com.example.movieapp.api.ApiResponse.login.LoginResponse;
import com.example.movieapp.api.ApiServer;
import com.example.movieapp.databinding.ActivityLoginBinding;
import com.example.movieapp.fragment.EditAccountInfoFragment;
import com.example.movieapp.model.Check.Check;
import com.example.movieapp.model.InfoUser;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    SharedPreferences.Editor editor;
    SharedPreferences mPref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mPref = getSharedPreferences("token", Context.MODE_PRIVATE);

        editor = mPref.edit();
//        setContentView(R.layout.activity_login);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvents();
        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText usernameEditText = binding.editEmail;

                final EditText passwordEditText = binding.editPassword;
//                ApiServer apiServer = retrofit.create(ApiServer.class);
                String email = usernameEditText.getText().toString().trim();
                String pass = passwordEditText.getText().toString().trim();



                // check empty
                if(email.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                    return;

                }else if(pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                //check email
                if(!Check.isValidEmail(email)){
                    Toast.makeText(LoginActivity.this, "Email is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginRequest loginRequest = new LoginRequest(email,pass);

                Call<LoginResponse> call = ApiServer.apiServer.login(loginRequest);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if ( response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            String accessToken = "";
                            String refreshToken = "";
//                      if (loginResponse!=null) {
                            if(response.code() == 200){
                                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                accessToken = loginResponse.getAccessToken();
                                refreshToken = loginResponse.getRefreshToken();

                                String id = loginResponse.getId();
                                editor.clear();
                                editor.commit();
                                editor.putString("accessToken",accessToken);
                                editor.putString("refreshToken",refreshToken);

                                editor.commit();
//                               Toast.makeText(LoginActivity.this, mPref.getString("accessToken",null), Toast.LENGTH_SHORT).show();
//                               Toast.makeText(LoginActivity.this, id, Toast.LENGTH_SHORT).show();

                                String currentToken = "Baerer " + mPref.getString("accessToken",null);
                                Log.e("accessToken",currentToken);

                               InfoUser infoUser = InfoUser.getInstance();
                               infoUser.setAccessToken(accessToken);
                                getUserData(currentToken,id);






                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finishAffinity();


//                                return;
                            }

//                      }
                        } else if(response.code() ==404 || response.code() ==500) {
                            String errorMessage = null;
                            try {
                                // Attempt to parse the body as a String (may throw exceptions)
                                String bodyString = response.errorBody().string();
                                errorMessage = new JSONObject(bodyString).getString("message"); // Example parsing
                                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                                // Handle parsing or conversion errors gracefully (optional)
                            }

                            return;

                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                        Log.e("error", throwable.getMessage());
                        Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }


    private void addEvents() {

        binding.txtCreateAcc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
//            finish();
        });

//        binding.imvBack.setOnClickListener(v -> {
//            startActivity(new Intent(getApplicationContext(), WelcomeScreenActivity.class));
//            finish();
//        });

        binding.txtForgotPass.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
//            finish();
        });




    }

    private void getUserData(String token, String accountId) {


        if(token!=null){
            Call<UserResponse> call = ApiServer.apiServer.getUser(accountId,token);
            Log.e("loi", accountId);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if ( response.isSuccessful()) {
                        UserResponse userResponse = response.body();
                        if(response.code() == 200){
//                            Toast.makeText(LoginActivity.this, userResponse.getEmail(), Toast.LENGTH_SHORT).show();

                            InfoUser infoUser = InfoUser.getInstance();
                            infoUser.setId(userResponse.getId());
                            infoUser.setName(userResponse.getEmail().split("@")[0]);
                            Log.e("name",infoUser.getName());
                            infoUser.setEmail(userResponse.getEmail());
                            infoUser.setPhone(userResponse.getPhone());
                            infoUser.setImg(userResponse.getImage());

                            Log.e("img",infoUser.getImg());


                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("accountid", userResponse.getId());
                            editor.apply();

//                       sendData(userResponse.getEmail(),userResponse.getPhone());

                        }
                    }else{

                        String errorMessage = null;
                        try {
                            // Attempt to parse the body as a String (may throw exceptions)
                            String bodyString = response.errorBody().string();
                            errorMessage = new JSONObject(bodyString).getString("message"); // Example parsing
                            Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            // Handle parsing or conversion errors gracefully (optional)
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    public void replaceFragmentUI (Fragment fragment) {
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.frameLayout, fragment);
//        transaction.commitNow();
//    }
//    private void sendData(String email, String phone) {
//
////        EditAccountInfoFragment fragment = new EditAccountInfoFragment();
////        Bundle bundle = new Bundle();
////        bundle.putString("email",email);
////        bundle.putString("phone",phone);
////        fragment.setArguments(bundle);
////        Log.e("bundle",bundle.getString("email"));
//
//
////        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        transaction.replace(R.id.frameLayout, fragment);
////        transaction.addToBackStack(null);
////        transaction.commit();
////        EditAccountInfoFragment editAccountFragment = EditAccountInfoFragment.newInstance(email, phone);
////
////
////
////        FragmentManager fragmentManager = getSupportFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.frameLayout, editAccountFragment);
////        fragmentTransaction.addToBackStack(null);
////        fragmentTransaction.commit();
//
//
//    }
}