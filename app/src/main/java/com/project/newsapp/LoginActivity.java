package com.project.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.project.newsapp.home.HomeActivity;
import com.project.newsapp.model.LoginRequest;
import com.project.newsapp.model.LoginResponse;
import com.project.newsapp.viewmodel.LoginVM;
import com.project.newsapp.viewmodel.NewsVM;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout username, password;
    private LoginVM loginVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        checkSession();

        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);


        //findViewById(R.id.button_login).setOnClickListener(v -> validate(username, password));
        findViewById(R.id.button_login).setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
    }

    private void validate(TextInputLayout username, TextInputLayout password) {
        String uname = username.toString();
        String pass = password.toString();
//        LoginRequest userInput = new LoginRequest(uname, pass);

        if (TextUtils.isEmpty(uname) && TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Username and Password cannot be blank!", Toast.LENGTH_SHORT).show();
        }

 /*       loginVM.getRetrofitInstance()
                .getUserApi()
                .login(userInput)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Log.d(Constants.TAG, "onResponse: "+ response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });*/

    }

    private void checkSession() {
        if (SessionManagerUtil.getInstance().isSessionActive(getApplicationContext(), new Date())){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        } else setContentView(R.layout.activity_login);
    }

    private void hideTitleBar() {
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
    }
}