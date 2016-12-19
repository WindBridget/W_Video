package com.windbridget.w_videoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ListView listView;
    ArrayList<Video> history;
    VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history = (ArrayList<Video>) getIntent().getExtras().getSerializable("video");


        listView = (ListView) findViewById(R.id.list_history_video);
        adapter = new VideoAdapter(History.this, history);
        listView.setAdapter(adapter);
    }
}
