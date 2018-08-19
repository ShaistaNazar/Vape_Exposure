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
import android.widget.TextView;

import java.util.HashMap;

public class ChangePassword extends AppCompatActivity {
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        session = new SessionManager(getApplicationContext());
        final TextView user;
        final EditText  pass,cnfrmPass;
        final CheckBox show_hide_password;
        user = (TextView) findViewById(R.id.theName);
        user.setText(Signup.signName);
        pass = (EditText) findViewById(R.id.changePassword);
        cnfrmPass=(EditText)findViewById(R.id.changePassword2);
        show_hide_password=(CheckBox)findViewById(R.id.hahah);
        ok=(Button)findViewById(R.id.okbtn) ;

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

                            pass.setInputType(InputType.TYPE_CLASS_TEXT);
                            pass.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                            cnfrmPass.setInputType(InputType.TYPE_CLASS_TEXT);
                            cnfrmPass.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText("Show Password");// change
                            // checkbox
                            // text

                            pass.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            pass.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password
                            cnfrmPass.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            cnfrmPass.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=user.getText().toString();
                String password=pass.getText().toString();
                String confermPassword=cnfrmPass.getText().toString();
                // get user data from session
                HashMap<String, String> detalis = session.getUserDetails();
                // name
                final String name = detalis.get(SessionManager.KEY_NAME);
                //email
                final String email = detalis.get(SessionManager.KEY_EMAIL);
                //location
                final String location = detalis.get(SessionManager.KEY_LOCATION);
                //pone No
                final String pone = detalis.get(SessionManager.KEY_MOBILE_NO);
                if( password.trim().length() > 0 && confermPassword.trim().length()>0){
                    if(password.equals(confermPassword)){
                       // session.editor.clear();
                        session.editor.remove(SessionManager.KEY_NAME);
                        session.editor.remove(SessionManager.KEY_EMAIL);
                        session.editor.remove(SessionManager.KEY_LOCATION);
                        session.editor.remove(SessionManager.KEY_MOBILE_NO);
                        session.editor.remove(SessionManager.KEY_PASSWORD);
                        session.editor.commit();
                        session.create_S_Session(name,email,location,pone,password);
                           //other ways to retrieve data
                        //1SessionManager.getUserDetails().get(SessionManager.KEY_MOBILE_NO),
                          // 2 SessionManager.pref.getString(SessionManager.KEY_PASSWORD,null)

                    Intent passwordChanged = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(passwordChanged);
                    finish();
                }else{
                        alert.showAlertDialog(ChangePassword.this, "Password Changing failed..", "Please enter same password in both fields", false);
                    }}
                else{
                    alert.showAlertDialog(ChangePassword.this, "Password changing failed..", "Please fill all fields", false);
                }}
        });
        }
    }
