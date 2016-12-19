package com.windbridget.w_videoplayer;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText m_name, m_email, m_username, m_pass1, m_pass2;
    String name, email, username, pass1, pass2;
    Button m_signup;
    Toast err_pass;
    Context context;
    Contact contact;
    DataBaseHelper helper;
    Intent m_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getInit();
        getEvent();
    }

    private void getInit(){
        context = this;
        m_name = (EditText)findViewById(R.id.m_su_name);
        m_username = (EditText)findViewById(R.id.m_su_user_name);
        m_pass1 = (EditText)findViewById(R.id.m_su_password);
        m_pass2 = (EditText)findViewById(R.id.m_su_cf_password);
        m_signup = (Button)findViewById(R.id.btn_singup);

    }

    private void getEvent(){
        m_signup.setOnClickListener(new MyButtonEvent());
    }

    private class MyButtonEvent implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_singup:
                    signUp();
                    break;
            }
        }
    }
    private void signUp(){
        name = m_name.getText().toString();
        username = m_username.getText().toString();
        pass1 = m_pass1.getText().toString();
        pass2 = m_pass2.getText().toString();

        if(! pass1.equals(pass2)){
            err_pass = Toast.makeText(context,"Password does not match !",Toast.LENGTH_SHORT);
            err_pass.show();
            m_intent = new Intent(context,Login.class);
            startActivity(m_intent);


        }
        else {
            contact = new Contact();
            contact.setName(name);
            contact.setUsername(username);
            contact.setPassword(pass1);

            helper = new DataBaseHelper(context);

            helper.insertContact(contact);
            err_pass = Toast.makeText(context,"Sign up successful !",Toast.LENGTH_SHORT);
            err_pass.show();
        }
    }
}
