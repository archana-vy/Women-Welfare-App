package com.mwcd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

public class    RegisterActivity extends AppCompatActivity {

    String[] categories = {"Single", "Married", "Widowed"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner spin = (Spinner) findViewById(R.id.spinner);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                getInput();
            }
        });
    }

    private void getInput() {
        EditText usernameText = (EditText) findViewById(R.id.username);
        String username = usernameText.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText fnameText = (EditText) findViewById(R.id.fname);
        String fname1 = fnameText.getText().toString().trim();
        if (TextUtils.isEmpty(fname1)) {
            Toast.makeText(this, "first name is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText lnameText = (EditText) findViewById(R.id.lname);
        String lname1 = lnameText.getText().toString().trim();
        if (TextUtils.isEmpty(lname1)) {
            Toast.makeText(this, "last name is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText aadharText = (EditText) findViewById(R.id.aadhar);
        String aadhar1 = aadharText.getText().toString().trim();
        if (TextUtils.isEmpty(aadhar1)) {
            Toast.makeText(this, "aadhar No. is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (aadhar1.length() != 12) {
            Toast.makeText(this, "aadhar No. is Invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText phoneText = (EditText) findViewById(R.id.phone);
        String phone1 = phoneText.getText().toString().trim();
        if (TextUtils.isEmpty(phone1)) {
            Toast.makeText(this, "phone no. is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone1.length() != 10) {
            Toast.makeText(this, "phone No. is Invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        EditText passwordText = (EditText) findViewById(R.id.password);
        String password1 = passwordText.getText().toString().trim();
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(this, " password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password1.length() < 6) {
            Toast.makeText(this, "password length is not sufficient", Toast.LENGTH_SHORT).show();
            return;
        }


        Spinner spin = (Spinner) findViewById(R.id.spinner);

        String categoryText = spin.getSelectedItem().toString().trim();
        Register register = new Register();
        register.setUserName(username);
        register.setfName(fname1);
        register.setlName(lname1);
        register.setAadharNumber(aadhar1);
        register.setPhoneNumber(phone1);
        register.setCategories(categoryText);
        register.setPassword(password1);
        DatabaseMSWD msed = new DatabaseMSWD(this);
       long id =  msed.insertUser(register);
        Log.e("Value","Value:"+id);
        Intent intent = new Intent(this,Preferances.class);
        startActivity(intent);
        msed.close();
        finish();
    }
}
