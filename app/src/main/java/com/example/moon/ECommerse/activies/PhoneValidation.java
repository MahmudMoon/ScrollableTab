package com.example.moon.ECommerse.activies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.Tools.Tools;
import com.example.moon.ECommerse.data.dataValues;
import com.example.moon.ECommerse.utils.MySingleTone;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PhoneValidation extends AppCompatActivity {

    AppCompatButton appCompatButton;
    TextInputEditText textInputEditText;
    public String code_sent;
    FirebaseAuth mAuth;
    private String TAG = "MYTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_phone);
        textInputEditText = (TextInputEditText)findViewById(R.id.et_phoneNum);
        Tools.setSystemBarColor(this, R.color.grey_20);
        mAuth = FirebaseAuth.getInstance();

        appCompatButton = (AppCompatButton)findViewById(R.id.btn_varify_phone_number);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = textInputEditText.getText().toString().trim();
                if(phone.startsWith("01")&& phone.length()==11){
                    dataValues.setPhone(phone);
                    isPhoneNumberalreadyStored(phone);
                }else {
                    Toast.makeText(getApplicationContext(),"Enter Valid Phone Number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void isPhoneNumberalreadyStored(final String phone) {
        String url_phone_varification = "http://192.168.0.101/E-commerce/VarifyPhoneNumber.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_phone_varification,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i(TAG, "onResponse: "+response);
                        if(TextUtils.equals(response,"NUMBER EXISTS")){
                            Toast.makeText(getApplicationContext(),"Phone Number Already Exists",Toast.LENGTH_SHORT).show();
                        }else if(TextUtils.equals(response,"NEW NUMBER")){
                            getcode(phone);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> map = new HashMap<>();
                map.put("phone",phone);
                return map;
            }
        };

        MySingleTone.getInstance(getApplicationContext()).addrequestToQueue(stringRequest);

    }


    private void getcode(String phone) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks


    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }


        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            code_sent = s;
            Intent intent = new Intent(PhoneValidation.this,CodeVarification.class);
            intent.putExtra("code",s);
            startActivity(intent);
        }
    };


//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's informatio
//
//                            FirebaseUser user = task.getResult().getUser();
//                            Intent intent = new Intent(MainActivity.this,Home.class);
//                            startActivity(intent);
//
//
//
//                            // ...
//                        } else {
//
//                        }
//                    }
//                });
//    }



}