package com.example.movieapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.api.ApiResponse.User.ForgetResponse;
import com.example.movieapp.api.ApiResponse.User.ForgotRequest;
import com.example.movieapp.api.ApiServer;
import com.example.movieapp.databinding.ActivityForgotPasswordBinding;
import com.example.movieapp.model.Check.Check;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    private ProgressDialog progressDialog;
     EditText edtEmail ;

    @Override
    protected void onPause() {

        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

            edtEmail = binding.editEmail;

        // check empty


     addEvents();


    }

    private void addEvents ()  {

        binding.btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                    return;

                }
//                check email
                if(!Check.isValidEmail(email)){
                    Toast.makeText(ForgotPasswordActivity.this, "Email is invalid", Toast.LENGTH_SHORT).show();
                    return;
                }

                ForgotRequest forgotRequest = new ForgotRequest(email);
                Call<ForgetResponse> call = ApiServer.apiServer.forgotPass(forgotRequest);
                call.enqueue(new Callback<ForgetResponse>() {
                    @Override
                    public void  onResponse(Call<ForgetResponse> call, Response<ForgetResponse> response) {

                        if(response.isSuccessful() && response.body()!=null){


                            ForgetResponse forgetResponse = response.body();
                           int code = forgetResponse.getRandomcode();
                            Toast.makeText(ForgotPasswordActivity.this, "succ", Toast.LENGTH_SHORT).show();
//                             Hiển thị dialog loading
//                            showLoadingDialog();
//
//                            // Ẩn dialog loading sau 3 giây (chỉ là ví dụ)
//                            new android.os.Handler().postDelayed(
//                                    new Runnable() {
//                                        public void run() {
//                                            hideLoadingDialog();
//
//
//
//                                        }
//                                    }, 3000);
                            Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                            intent.putExtra("Activity","Forgot");
                            intent.putExtra("Email",email);
                            intent.putExtra("ResetCode",code);
                            startActivity(intent);
                            finish();

                        }else {
                            String errorMessage = null;
                            try {
                                // Attempt to parse the body as a String (may throw exceptions)
                                String bodyString = response.errorBody().string();
                                errorMessage = new JSONObject(bodyString).getString("message"); // Example parsing
                                Toast.makeText(ForgotPasswordActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

                            } catch (Exception e) {
                                // Handle parsing or conversion errors gracefully (optional)
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgetResponse> call, Throwable throwable) {
                        Toast.makeText(ForgotPasswordActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
//                finish();
            }
        });
    }
    private void showLoadingDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}