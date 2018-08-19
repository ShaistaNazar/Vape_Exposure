package com.example.dell.notifyyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Login_Activity extends AppCompatActivity {
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    String username;
    EditText userN, passN;
    Button login;
    Intent ImLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        session = new SessionManager(getApplicationContext());

        userN = (EditText) findViewById(R.id.usera1);
        passN = (EditText) findViewById(R.id.passa2);
        login = (Button) findViewById(R.id.loginbtna3);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = userN.getText().toString();
                String password = passN.getText().toString();
                // get user data from session
                HashMap<String, String> user = session.getUserDetails();

                // name
                String name = user.get(SessionManager.KEY_NAME);

                // pass
                String passs = user.get(SessionManager.KEY_PASSWORD);
             if ((username.length() > 0) && (password.length() > 0)) {
                   if (username.equals(name) && password.equals(passs)){
                        session.create_L_Session();
                        Toast.makeText(getApplicationContext(),"WELCOME DEAR " +name +" :)",Toast.LENGTH_LONG).show();
                        if(3>2){
                         ImLoggedIn= new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(ImLoggedIn);
                        finish();
                    }}
                    else {
                        alert.showAlertDialog(Login_Activity.this, "Error...", "userName or password doesn't match", false);
                    }}
                else {
                    alert.showAlertDialog(Login_Activity.this, "Login failed..", "Please fill all fields", false);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(session.editor!=null){
            session.create_LO_Session();
            finish();
            System.exit(0);
        }else {
            super.onBackPressed();
        }
    }
}

