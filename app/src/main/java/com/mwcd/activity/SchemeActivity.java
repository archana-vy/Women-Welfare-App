package com.mwcd.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
    }

    private class ParseURL extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer buffer = new StringBuffer();
            final StringBuilder builder = new StringBuilder();

            try {
                org.jsoup.nodes.Document doc = Jsoup.connect("http://nari.nic.in/direct-benefits").get();
                String title = doc.title();
                Elements links = doc.select("a[href]");

                builder.append(title).append("\n");

                for (org.jsoup.nodes.Element link : links) {
                    builder.append("\n").append("Link : ").append(link.attr("href"))
                            .append("\n").append("Text : ").append(link.text());
                }
            } catch (IOException e) {
                builder.append("Error : ").append(e.getMessage()).append("\n");
            }

            return builder.toString();
        }


        @Override
        protected void onPostExecute(String s) {
           getData(s);

        }
    }

    private void getData( String myData) {
        String details = myData.substring(myData.indexOf("DBT Bharat Portal"));

        details = details.replace("/", " ");
        String[] dataValue = TextUtils.split(details, "\n");
        List<String> name = new ArrayList<>();
        List<String>link = new ArrayList<>();
        for (String datas : dataValue) {
            if (datas.contains("Link : ")) {
                datas = datas.replace("Link : ", "").trim();
                link.add(datas);
            } else {
                datas = datas.replace("Text : ", "").trim();
                name.add(datas);
            }
        }
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", "default content");
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
        Log.e("Error",myData);
    }
}
