package com.example.moon.ECommerse.activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    Button btn_registration;
    AutoCompleteTextView et_fullName,user_Name,et_email;
    EditText et_pass,et_conPass;
    RadioGroup ra_gender;
    String gender_;
    String url_insert = "http://192.168.0.101/E-commerce/insertIntoUsers.php";
    private String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sign_up);
        init_views();

        initToolbar();

        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String f_name = et_fullName.getText().toString().trim();
                final String u_name = user_Name.getText().toString().trim();
                final String email = et_email.getText().toString().trim();
                final String pass = et_pass.getText().toString().trim();
                String conPass = et_conPass.getText().toString().trim();



                Log.i(TAG, "onClick: "+f_name + "\n" + u_name
                        + "\n" + email + "\n" + pass + "\n"
                        + conPass + "\n");

                if(!TextUtils.isEmpty(f_name) && !TextUtils.isEmpty(u_name) && !TextUtils.isEmpty(email) &&
                        !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(conPass)){
                    if(TextUtils.equals(pass,conPass)){
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_insert,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(TextUtils.equals(response,"SUCCESS")){
                                            Toast.makeText(getApplicationContext(),"DATA INSERTED",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Registration.this,Login_paze.class);
                                            startActivity(intent);
                                        }else if(TextUtils.equals(response,"FAILED")){
                                            Toast.makeText(getApplicationContext(),"FAILED TO INSERT",Toast.LENGTH_SHORT).show();
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
                                map.put("fullName",f_name);
                                map.put("userName",u_name);
                                map.put("phone", dataValues.getPhone());
                                map.put("email",email);
                                map.put("password",pass);
                                return map;
                            }
                        };
                        MySingleTone.getInstance(getApplicationContext()).addrequestToQueue(stringRequest);
                    }else {
                        et_conPass.setText("");
                        Toast.makeText(getApplicationContext(),"Password did n't match",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void init_views() {
        btn_registration = (Button)findViewById(R.id.email_sign_in_button);
        et_fullName = (AutoCompleteTextView)findViewById(R.id.et_fullname);
        user_Name = (AutoCompleteTextView)findViewById(R.id.et_userName);
        et_email = (AutoCompleteTextView)findViewById(R.id.et_email);
        et_pass = (EditText)findViewById(R.id.et_pass);
        et_conPass = (EditText)findViewById(R.id.et_conPass);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
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
}

