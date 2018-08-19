package com.example.dell.notifyyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.HashMap;

public class Signup extends AppCompatActivity {
    EditText signUpName,email,phone,location,password,cnfrmPassword;
    Button signUp;
    CheckBox show_hide_password;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    static String signPass;
    static String signName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpName=(EditText)findViewById(R.id.editText8);

        session = new SessionManager(getApplicationContext());

        email=(EditText)findViewById(R.id.editText12);
        phone=(EditText)findViewById(R.id.editText13);
        location=(EditText)findViewById(R.id.editText14);
        password=(EditText)findViewById(R.id.editText15);
        cnfrmPassword=(EditText)findViewById(R.id.editText16);
        signUp=(Button)findViewById(R.id.signupbtn);
        show_hide_password=(CheckBox)findViewById(R.id.checkBox3);



        show_hide_password
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText("Hide Password");// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                            cnfrmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                            cnfrmPassword.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText("Show Password");// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password
                            cnfrmPassword.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            cnfrmPassword.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signName=signUpName.getText().toString();
                 String signEmail=email.getText().toString();
                 String signPhone=phone.getText().toString();
                 String signLoc=location.getText().toString();
                signPass=password.getText().toString();
                final String signCnfrmPass=cnfrmPassword.getText().toString();
         if((signName.length() > 0) && (signPass.length() > 0) && (signEmail.length() > 0) && (signPhone.length() > 0) && (signLoc.length() > 0)){

               if(signPass.equals(signCnfrmPass)){

                       session.create_S_Session(signName,signEmail,signLoc,signPhone,signPass);
                        Intent ImSignedIn = new Intent(getApplicationContext(),Login_Activity.class);
                          startActivity(ImSignedIn);
                     finish();
               }else{
              alert.showAlertDialog(Signup.this, "SignUp failed..", "Please enter same password in both fields", false);
                }}
                 else{
                       alert.showAlertDialog(Signup.this, "SignUp failed..", "Please fill all fields", false);
             }
       }});
    }
    @Override
    public void onBackPressed(){
        //this.finish();
        finish();
        System.exit(0);
    }
}