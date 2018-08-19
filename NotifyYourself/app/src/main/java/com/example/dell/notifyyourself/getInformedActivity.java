package com.example.dell.notifyyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;

public class getInformedActivity extends AppCompatActivity {
    ListView listView;

    SessionManager session;
    AlertDialogManager alert;
    ArrayAdapter<String> listAdapter;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_informed);

        listView = (ListView) findViewById(R.id.recycler_view);
        String[] listViewAdapterContent = {"FOLLOWING ARE SOME SIDE EFFECTS OF VAPING AND USEFUL WAYS TO STOP VAPING","Ways To Stop Vaping", "Side Effects Of Vaping  "};
        listAdapter = new ArrayAdapter<>(this, R.layout.layout,R.id.textViews, listViewAdapterContent);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch( position )
                {
                    case 1:
                        Intent newActivity = new Intent(getInformedActivity.this, Ways.class);
                        startActivity(newActivity);
                        break;
                    case 2 :
                        Intent newActivity1 = new Intent(getInformedActivity.this, Effects.class);
                        startActivity(newActivity1);
                        break;
            }
        }});

       }
    }