package com.mwcd.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Preferances extends AppCompatActivity {

    private String[] states = {"Andrapradesh","Kerala","TamilNadu","Karnataka","Orissa","Maharastra"
    ,"Gujarath","West Bengal","Jammu & Kashmir"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferances);
        DatabaseMSWD db = new DatabaseMSWD(this);
        Spinner spin = (Spinner) findViewById(R.id.spinner);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,states);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
           Register register = db.getData();
           String username = register.getUserName();
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInput();
            }
        });
    }

    private void getInput(){

    }
}
