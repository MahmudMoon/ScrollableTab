package com.example.moon.ECommerse.activies;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.moon.ECommerse.R;
import com.example.moon.ECommerse.Tools.Tools;
import com.example.moon.ECommerse.utils.MySingleTone;

import java.util.HashMap;
import java.util.Map;

public class Login_paze extends AppCompatActivity {

    private View parent_view;
    Button btn_singIn;
    EditText etPhone,etPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_card_overlap);
        btn_singIn = (Button)findViewById(R.id.btn_signin);

        parent_view = findViewById(android.R.id.content);
        etPhone = (EditText)findViewById(R.id.et_phone);
        etPassWord = (EditText)findViewById(R.id.et_password);

        btn_singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SIGN IN CHECK HERE

                loginAttempt(etPhone.getText().toString().trim(),etPassWord.getText().toString().trim());

            }
        });

        ((View) findViewById(R.id.sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Sign Up", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(Login_paze.this,PhoneValidation.class);
                startActivity(intent);

            }
        });

        Tools.setSystemBarColor(this);
    }

    private void loginAttempt(final String phone_, final String pass_) {
        String url_loginPaze = "http://192.168.0.101/E-commerce/loginUsers.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_loginPaze,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      if(TextUtils.equals(response,"SUCCESS")){

                          Intent intent = new Intent(Login_paze.this,MainActivity.class);
                          startActivity(intent);
                      }else{
                          Toast.makeText(getApplicationContext(),"Failed to login",Toast.LENGTH_SHORT).show();
                      }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("phone",phone_);
                map.put("password",pass_);
                return map;
            }
        };
        MySingleTone.getInstance(getApplicationContext()).addrequestToQueue(stringRequest);
    }
}