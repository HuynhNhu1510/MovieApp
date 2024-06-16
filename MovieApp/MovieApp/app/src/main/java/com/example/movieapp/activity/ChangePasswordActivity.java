package com.example.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movieapp.R;
import com.example.movieapp.api.ApiResponse.User.ResetForgotPassRequest;
import com.example.movieapp.api.ApiResponse.User.ResetForgotPassResponse;
import com.example.movieapp.api.ApiServer;
import com.example.movieapp.databinding.ActivityChangePasswordBinding;
import com.example.movieapp.model.Check.Check;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    ActivityChangePasswordBinding binding;
    EditText edtNewPass, edtNewPassAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_change_password);
        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        edtNewPass = binding.newPassword;
        edtNewPassAuth = binding.confirmPassword;

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cofirm = edtNewPassAuth.getText().toString().trim();
                String newPass = edtNewPass.getText().toString().trim();

                if (newPass.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "New Password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }else

                if(!Check.checkPasswordStrength(newPass).equals("Strong password.")){
                    Toast.makeText(ChangePasswordActivity.this, Check.checkPasswordStrength(newPass), Toast.LENGTH_SHORT).show();
                    return;
                }


                if (cofirm.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Confirm password is empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!cofirm.equals(newPass)) {
                    Toast.makeText(ChangePasswordActivity.this, "Confirm password invalid", Toast.LENGTH_SHORT).show();
                    return;
                }


                Intent intent1 = getIntent();
                ResetForgotPassRequest resetForgotPassRequest = new ResetForgotPassRequest(intent1.getStringExtra("email"), newPass);


                Call<ResetForgotPassResponse> call2 = ApiServer.apiServer.resetForgotPass(resetForgotPassRequest);
                call2.enqueue(new Callback<ResetForgotPassResponse>() {
                    @Override
                    public void onResponse(Call<ResetForgotPassResponse> call, Response<ResetForgotPassResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ResetForgotPassResponse result = response.body();
                            Toast.makeText(ChangePasswordActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ChangePasswordActivity.this, ResetPassSuccessActivity.class));
                            finish();
                        } else if (response.code() == 404 || response.code() == 500) {
                            String errorMessage = null;
                            try {
                                // Attempt to parse the body as a String (may throw exceptions)
                                String bodyString = response.errorBody().string();
                                errorMessage = new JSONObject(bodyString).getString("message"); // Example parsing
                                Toast.makeText(ChangePasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                                // Handle parsing or conversion errors gracefully (optional)
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResetForgotPassResponse> call, Throwable throwable) {
                        Toast.makeText(ChangePasswordActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                });
            }

        });


    }
}