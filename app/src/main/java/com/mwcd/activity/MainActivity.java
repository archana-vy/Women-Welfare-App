package com.mwcd.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomOnClickListener{
    private RecyclerView recyclerView;
    private DashBoardAdapter adapter;
    private List<DashboardItem> albumList;
    Register register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
register = new Register();
try {
    Bundle bundle = getIntent().getExtras();
    String message = bundle.getString("message");
    register = new Gson().fromJson(message,Register.class);
}catch(Exception e){

}
        albumList = new ArrayList<>();
        adapter = new DashBoardAdapter(this, albumList,this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

    }

    @Override
    public void onItemClick(View v, int position) {
        switch(position){
            case 0:
                startActivity(new Intent(this,SchemeActivity.class));
                break;

        }
    }
    private void prepareAlbums(){
        DashboardItem a = new DashboardItem("Preferred Govt Scheme",  R.drawable.ic_launcher_round);
        albumList.add(a);

        a = new DashboardItem("Banking Info",  R.drawable.ic_launcher_round);
        albumList.add(a);

        a = new DashboardItem("Nutrition Tips",  R.drawable.ic_launcher_round);
        albumList.add(a);

        a = new DashboardItem("Hygine Tips",  R.drawable.ic_launcher_round);
        albumList.add(a);

        a = new DashboardItem("Entrepreneurship",  R.drawable.ic_launcher_round);
        albumList.add(a);

        a = new DashboardItem("Interview Tips",  R.drawable.ic_launcher_round);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect(getString(R.string.website)).get();
                    String title = doc.title();
                    Elements links = doc.select("a[href]");

                    builder.append(title).append("n");

                    for (Element link : links) {
                        builder.append("\n").append("Link : ").append(link.attr("href"))
                                .append("\n").append("Text : ").append(link.text());
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }
}
