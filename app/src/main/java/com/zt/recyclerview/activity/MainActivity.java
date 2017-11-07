package com.zt.recyclerview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.zt.recyclerview.R;
import com.zt.recyclerview.adapter.EventAdapter;
import com.zt.recyclerview.global.GlobalConstant;
import com.zt.recyclerview.global.RecyclerItemClickListener;
import com.zt.recyclerview.global.Utils;
import com.zt.recyclerview.restapicall.AsyncTaskCompleteListener;
import com.zt.recyclerview.restapicall.ParseController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView tv_event_not_found;
    private RecyclerView recyclerView;
    private ArrayList<HashMap<String, String>> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        initUI();
        getEventList();
    }

    public void initUI() {
        tv_event_not_found = (TextView) findViewById(R.id.tv_event_not_found);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                if (!Utils.isArrayListNull(eventList)) {
                                    HashMap<String, String> hashMap = eventList.get(position);
                                    if (hashMap != null && hashMap.size() != 0) {
                                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                                        intent.putExtra("hashMap", hashMap);
                                        startActivity(intent);
                                    }
                                }
                            }
                        })
        );
    }

    public void getEventList() {
        Utils.hideKeyboard(this);

        Map<String, String> map = new HashMap<>();
        map.put("url", GlobalConstant.URL + GlobalConstant.EVENT_LIST);

        new ParseController(this, ParseController.HttpMethod.POST,
                map, true, "Get event list...", new AsyncTaskCompleteListener() {
            @Override
            public void onSuccess(String response) {
                chkResponse(response);
            }

            @Override
            public void onFailed(int statusCode, String msg) {
                Utils.showToast(MainActivity.this, msg);
            }
        });
    }

    private void chkResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String success = jsonObject.getString("success");
            String msg = jsonObject.getString("msg");
            if (success.equals("1")) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (jsonArray.length() > 0) {
                    eventList.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("id", object.getString("id"));
                        map.put("title", object.getString("title"));
                        map.put("description", object.getString("description"));
                        map.put("date", object.getString("date"));
                        map.put("time", object.getString("time"));
                        eventList.add(map);
                    }
                    setData();
                } else {
                    dataNotFound();
                    Utils.showToast(this,
                            getResources().getString(R.string.data_not_fount));
                }
            } else {
                dataNotFound();
                Utils.showToast(this, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setData() {
        if (!Utils.isArrayListNull(eventList)) {
            tv_event_not_found.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            //
            EventAdapter adapter = new EventAdapter(this, eventList);
            recyclerView.setAdapter(adapter);
        } else {
            dataNotFound();
        }
    }

    public void dataNotFound() {
        tv_event_not_found.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
