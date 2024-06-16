package com.example.movieapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.api.ApiResponse.registered.RegisResponse;
import com.example.movieapp.api.ApiResponse.registered.RegisterRes;
import com.example.movieapp.api.ApiServer;
import com.example.movieapp.databinding.ActivityRegisterBinding;
import com.example.movieapp.model.Check.Check;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ActivityRegisterBinding binding;
    private EditText usernameEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;


    // Retrofit service

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_register);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usernameEditText = binding.editEmail;
        passwordEditText = binding.editPassword;
        phoneEditText = binding.editPhone;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tạo tài khoản...");
        progressDialog.setCancelable(false);
        addEvents();
    }

    private void addEvents() {
//        binding.imvBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
//            }
//        });

        binding.txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
            }
        });
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // check empty
                if(email.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                    return;

                }else   //check email & phone
                    if(!Check.isValidEmail(email)){
                        Toast.makeText(RegisterActivity.this, "Email is invalid", Toast.LENGTH_SHORT).show();
                        return;
                    }

                if(phone.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Phone is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Check.isValidPhone(phone)){
                    Toast.makeText(RegisterActivity.this, "Phone is Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Password is Invalid", Toast.LENGTH_SHORT).show();
                    return;
                }else

                if(!Check.checkPasswordStrength(password).equals("Strong password.")){
                    Toast.makeText(RegisterActivity.this, Check.checkPasswordStrength(password), Toast.LENGTH_SHORT).show();
                    return;
                }


                progressDialog.show();
                RegisterRes registerRes = new RegisterRes(email,phone,password);

                ApiServer.apiServer.register(registerRes).enqueue(new Callback<RegisResponse>() {
                    @Override
                    public void onResponse(Call<RegisResponse> call, Response<RegisResponse> response) {

                        RegisResponse result = response.body();

                        if(response.isSuccessful() && response.body()!=null){
                            RegisResponse regisResponse = response.body();
                            if (response.code() == 200) {
                                Toast.makeText(RegisterActivity.this, regisResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                            }

                        }

                        else if (response.code() == 400) {
                            progressDialog.dismiss();
                            String errorMessage = null;
                            try {
                                // Attempt to parse the body as a String (may throw exceptions)
                                String bodyString = response.errorBody().string();
                                errorMessage = new JSONObject(bodyString).getString("message"); // Example parsing
                                Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                                // Handle parsing or conversion errors gracefully (optional)
                            }
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this,"ERRROR", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<RegisResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

            }
        });
    }
}