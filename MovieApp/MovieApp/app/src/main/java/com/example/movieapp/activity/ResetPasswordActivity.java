package com.example.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.movieapp.R;
import com.example.movieapp.api.ApiResponse.User.ForgetResponse;
import com.example.movieapp.api.ApiResponse.User.ForgotRequest;
import com.example.movieapp.api.ApiResponse.User.ResetForgotPassRequest;
import com.example.movieapp.api.ApiResponse.User.ResetForgotPassResponse;
import com.example.movieapp.api.ApiResponse.User.ResetPassResponse;
import com.example.movieapp.api.ApiResponse.User.ResetPassResquest;
import com.example.movieapp.api.ApiResponse.User.UserResponse;
import com.example.movieapp.api.ApiServer;
import com.example.movieapp.databinding.ActivityResetPasswordBinding;
import com.example.movieapp.model.Check.Check;
import com.example.movieapp.model.InfoUser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    ActivityResetPasswordBinding binding;
    SharedPreferences.Editor editor;
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextInputLayout oldPass = binding.okla;
        mPref = getSharedPreferences("token", Context.MODE_PRIVATE);

        EditText edtOldPass = binding.oldPassword;
        EditText edtNewPass = binding.newPassword;
        EditText edtNewPassAuth = binding.confirmPassword;
//        setContentView(R.layout.activity_reset_password);
// check neu email lay tu quen mat khau hay email lay tu doi mat khau
        Intent intent = getIntent();
        String previousActivity = intent.getStringExtra("previous");
        String email = intent.getStringExtra("email");
        InfoUser infoUser = InfoUser.getInstance();

//        String email2 = infoUser.getEmail();
//
//        Log.e("email2",email2);


//            if(previousActivity!=null && previousActivity.equals("Forgot")) {
//                oldPass.setVisibility(View.GONE);
//
//            }

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String oldPasss = edtOldPass.getText().toString().trim();
                String cofirm = edtNewPassAuth.getText().toString().trim();
                String newPass = edtNewPass.getText().toString().trim();
                if (oldPasss.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Your password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPass.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "New Password is empty", Toast.LENGTH_SHORT).show();
                    return;
                }else

                if(!Check.checkPasswordStrength(newPass).equals("Strong password.")){
                    Toast.makeText(ResetPasswordActivity.this, Check.checkPasswordStrength(newPass), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(newPass.equals(oldPasss)){
                    Toast.makeText(ResetPasswordActivity.this, "New password and Old Password are the same ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cofirm.isEmpty()) {
                    Toast.makeText(ResetPasswordActivity.this, "Confirm password is empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!cofirm.equals(newPass)) {
                    Toast.makeText(ResetPasswordActivity.this, "Confirm password invalid", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (mPref.getString("accessToken", null) != null) {
                    String currentToken = mPref.getString("accessToken", null);
                    Log.e("token",currentToken);


                    ResetPassResquest resetPassResquest = new ResetPassResquest(currentToken, oldPasss, newPass);
                    Call<ResetPassResponse> call = ApiServer.apiServer.resetPass(resetPassResquest);

                    call.enqueue(new Callback<ResetPassResponse>() {
                        @Override
                        public void onResponse(Call<ResetPassResponse> call, Response<ResetPassResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                ResetPassResponse result = response.body();
                                Toast.makeText(ResetPasswordActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();


                                startActivity(new Intent(ResetPasswordActivity.this, ResetPassSuccessActivity.class));
                                finish();
                            } else if (response.code() == 404 || response.code() == 500) {
                                String errorMessage = null;
                                try {

                                    String bodyString = response.errorBody().string();
                                    errorMessage = new JSONObject(bodyString).getString("message"); // Example parsing
                                    Toast.makeText(ResetPasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                                } catch (Exception e) {

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResetPassResponse> call, Throwable throwable) {
                            Toast.makeText(ResetPasswordActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

//
                }

            }

        });
    }
}