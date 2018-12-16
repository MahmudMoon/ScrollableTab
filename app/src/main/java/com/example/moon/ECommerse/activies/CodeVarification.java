package com.example.moon.ECommerse.activies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.Tools.Tools;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class CodeVarification extends AppCompatActivity {

AppCompatButton appCompatButton;

    public static String Code;
    TextInputEditText c1,c2,c3,c4,c5,c6;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        initToolbar();

        Intent intent = getIntent();
        Code = intent.getStringExtra("code");


        mAuth = FirebaseAuth.getInstance();

        c1 = (TextInputEditText)findViewById(R.id.firstChar);
        c2 = (TextInputEditText)findViewById(R.id.secondChar);
        c3 = (TextInputEditText)findViewById(R.id.thirdChar);
        c4 = (TextInputEditText)findViewById(R.id.fourthChar);
        c5 = (TextInputEditText)findViewById(R.id.fifthChar);
        c6 = (TextInputEditText)findViewById(R.id.sixthChar);


        c1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(c1.length()>=1){
                   c1.clearFocus();
                   c2.requestFocus();
                   c2.setCursorVisible(true);
               }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        c2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(c2.length()>=1){
                    c2.clearFocus();
                    c3.requestFocus();
                    c3.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        c3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(c3.length()>=1){
                    c3.clearFocus();
                    c4.requestFocus();
                    c4.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        c4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(c4.length()>=1){
                    c4.clearFocus();
                    c5.requestFocus();
                    c5.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        c5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(c5.length()>=1){
                    c5.clearFocus();
                    c6.requestFocus();
                    c6.setCursorVisible(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });






        appCompatButton = (AppCompatButton)findViewById(R.id.btn_varify);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // varify code
                //get the entirecode
                 StringBuilder stringBuilder = new StringBuilder();
                 stringBuilder.append(c1.getText().toString().trim());
                 stringBuilder.append(c2.getText().toString().trim());
                 stringBuilder.append(c3.getText().toString().trim());
                 stringBuilder.append(c4.getText().toString().trim());
                 stringBuilder.append(c5.getText().toString().trim());
                 stringBuilder.append(c6.getText().toString().trim());

                String code =  stringBuilder.toString();
                varifyCode(code);
//
//
//
//                Intent intent = new Intent(CodeVarification.this, Registration.class);
//                startActivity(intent);
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void varifyCode(String code_rec) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(Code, code_rec);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            Intent intent = new Intent(CodeVarification.this,Registration.class);
                            startActivity(intent);

                        } else {

                        }
                    }
                });
    }
}
