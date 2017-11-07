package com.zt.recyclerview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;
import com.zt.recyclerview.R;
import com.zt.recyclerview.global.Utils;

import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    private TextView tv_titles, tv_description;
    private HashMap<String, String> hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("");
        hashMap = (HashMap<String, String>) getIntent().getSerializableExtra("hashMap");

        initUI();

    }

    public void initUI() {
        tv_titles = (TextView) findViewById(R.id.tv_titles);
        tv_description = (TextView) findViewById(R.id.tv_description);

        String title = hashMap.get("title");
        String description = hashMap.get("description");
        //Title
        if (!Utils.isStringNull(title)) {
            String text = "<font color=#F47E02>Title: </font><font color=#000000>" + title + "</font>";
            tv_titles.setText(Html.fromHtml(text));
        }
        //Description
        if (!Utils.isStringNull(description)) {
            String text = "<font color=#F47E02>Description: </font><font color=#000000>" + description + "</font>";
            tv_description.setText(Html.fromHtml(text));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Utils.hideKeyboard(this);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
