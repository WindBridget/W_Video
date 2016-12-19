package com.windbridget.w_videoplayer;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button m_login,m_getacc;
    EditText m_username,m_password;
    String username,password,searchpass,name;
    Intent m_intent;
    Context context;
    DataBaseHelper helper;
    Toast err_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getInit();
        getEvent();

    }

    private void getInit(){
        context = this;
        m_login = (Button) findViewById(R.id.btn_login);
        m_getacc = (Button) findViewById(R.id.btn_get_acc);
        m_username = (EditText) findViewById(R.id.m_user_name);
        m_password = (EditText) findViewById(R.id.m_password);
    }

    private void getEvent(){
        m_login.setOnClickListener(new MyButtonEvent());
        m_getacc.setOnClickListener(new MyButtonEvent());
    }

    private class MyButtonEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btn_login:
                    login();
                    break;
                case R.id.btn_get_acc:
                    getAccount();
                    break;
            }
        }
    }

    private void login(){
        username = m_username.getText().toString();
        password = m_password.getText().toString();
        helper = new DataBaseHelper(context);
        searchpass = helper.searchPassword(username);
        if(password.equals(searchpass)){
            m_intent = new Intent(context, MainActivity.class);
            m_intent.putExtra("Name",username);
            startActivity(m_intent);
        }
        else {
            err_pass = Toast.makeText(context,"Username and Password do not match !!",Toast.LENGTH_SHORT);
            err_pass.show();
        }
    }

    private void getAccount(){
        m_intent = new Intent(context, SignUp.class);
        startActivity(m_intent);
    }
}
