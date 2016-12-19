package com.windbridget.w_videoplayer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button m_funix;
    TextView m_name;
    String name;
    Intent m_intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInit();
        getEvent();

    }

    private void getInit(){
        context = this;
        m_funix = (Button) findViewById(R.id.btn_funix);
        m_name = (TextView) findViewById(R.id.tv_main_name);
        name = getIntent().getExtras().getString("Name");
        m_name.setText(name);
    }

    private void getEvent(){
        m_funix.setOnClickListener(new MyButtonEvent());
    }

    private class MyButtonEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btn_funix:
                    reachList();
                    break;
            }
        }
    }

    private void reachList(){
        m_intent = new Intent(context, ListVideo.class);
        startActivity(m_intent);
    }

}
