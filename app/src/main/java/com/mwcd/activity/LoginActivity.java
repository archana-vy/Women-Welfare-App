package com.mwcd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkError();
            }
        });

        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
    }


    private void checkError() {
        if (((TextView) findViewById(R.id.input_username)).getText().toString().trim().length() == 0) {
            ((TextInputLayout) findViewById(R.id.input_layout_username)).setError("Username is Empty");
        }

        if (((TextView) findViewById(R.id.input_password)).getText().toString().trim().length() == 0) {
            ((TextInputLayout) findViewById(R.id.input_layout_password)).setError("Password is Empty");
        }
    }
}
