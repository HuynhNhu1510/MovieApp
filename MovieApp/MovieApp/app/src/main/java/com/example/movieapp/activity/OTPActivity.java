package com.example.movieapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.movieapp.databinding.ActivityOtpactivityBinding;
import com.example.movieapp.databinding.ActivityResetPasswordBinding;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OTPActivity extends AppCompatActivity {

    ActivityOtpactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpactivityBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_otpactivity);
        Intent intent = getIntent();
        String code = String.valueOf(intent.getIntExtra("ResetCode",0));
        String acti = intent.getStringExtra("Activity");
        String email = intent.getStringExtra("Email");
        Log.e("email",email);

        Log.e("rếtOTP",code);
        Log.e("acti",acti);
        final EditText OTPcode = binding.editEmail;

//        Toast.makeText(OTPActivity.this,code, Toast.LENGTH_SHORT).show();
        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the desired format
//                Instant now = Instant.now();
//
//                // Get the time 60 seconds from now
//                Instant later = now.plusSeconds(60);
//
//                // Calculate the duration between now and later
//                Duration timeout = Duration.between(now, later);
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
//                        .withZone(ZoneId.systemDefault());
////                 Instant timeout = Instant.now().plusSeconds(60);
//
////
////                ZonedDateTime zonedDateTime = timeout.atZone(ZoneId.systemDefault());
//                while (true) {
//                    // Calculate the remaining duration
//                    Duration remaining = Duration.between(Instant.now(), later);
//
//                    // Break the loop if the countdown has finished
//                    if (remaining.isNegative() || remaining.isZero()) {
//                       Log.e("ok","Countdown finished!");
//                        break;
//                    }
//
//                    // Print the remaining time in seconds
//
////                    binding.txtResend.setText(remaining.getSeconds());
//
//                    Log.e("Time remaining: ", +remaining.getSeconds() + "");
//
////                    binding.txtResend.setText(text);
//                    // Define a handler outside the onClickListener
//                    Handler handler = new Handler(Looper.getMainLooper());
//
//// Update the UI periodically using a Runnable
//                    Runnable updateUITask = new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                // Calculate the remaining duration
//                                Duration remaining = Duration.between(Instant.now(), later);
//
//                                // Update the text view with the remaining time
//                                String text = String.valueOf(remaining.getSeconds());
//                                binding.time.setText(text);
//
//                                // If the remaining time is positive, post the update again after 1 second
//                                if (!remaining.isNegative() && !remaining.isZero()) {
//                                    handler.postDelayed(this, 1000);
//                                } else {
//                                    // Otherwise, display a message indicating that the countdown has finished
//                                    Log.e("ok", "Countdown finished!");
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    };
//
//// Start updating the UI
//                    handler.post(updateUITask);
//                }

//




                String codeInput = OTPcode.getText().toString().trim();
                if(codeInput.isEmpty()){
                    Toast.makeText(OTPActivity.this, "Code is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(code.equals(codeInput) ){
                    // cung cap accent token de thay doi mat khau

                    Intent intent1 = new Intent(OTPActivity.this, ChangePasswordActivity.class);
                    intent1.putExtra("previous","Forgot");
                    intent1.putExtra("email",email);
                    startActivity(intent1);
                    finish();
                }else{
                    Toast.makeText(OTPActivity.this, "Invalid OTP code", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}