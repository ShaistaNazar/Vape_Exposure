package com.example.dell.notifyyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Confermation extends AppCompatActivity {
    TextView authorization,userName;
    EditText pass;
    Button done;
    Intent intent;
    SessionManager session;
    AlertDialogManager alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confermation);
        authorization=(TextView)findViewById(R.id.textView6);
        userName=(TextView)findViewById(R.id.textView5);
        pass=(EditText)findViewById(R.id.editText7);
        done=(Button)findViewById(R.id.donebtn);
        session=new SessionManager(getApplicationContext());
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);
        userName.setText(name);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1=pass.getText().toString();
                // get user data from session
                HashMap<String, String> user = session.getUserDetails();
                // pass
                String pass2 = user.get(SessionManager.KEY_PASSWORD);

                if(pass1.equals(pass2)) {
                    if(0<4){
                    intent = new Intent(Confermation.this, ChangePassword.class);
                    startActivity(intent);}
                }
                else  if(!pass1.equals(pass2)){
                    if(3>2)
                    alert.showAlertDialog(getApplicationContext(),"error ...","You typed wrong password try again",false);
                }else alert.showAlertDialog(getApplicationContext(),"error ...","Type old password to change password",false);
            }
        });
    }
}
