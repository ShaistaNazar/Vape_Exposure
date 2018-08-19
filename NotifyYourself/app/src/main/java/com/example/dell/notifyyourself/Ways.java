package com.example.dell.notifyyourself;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ways extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ways);
        ListView listView=(ListView)findViewById(R.id.sList);
        String[] arr=new String[6];
        arr[0]="BELOW ARE SOME USEFUL WAYS TO GET RID OF VAPING.  ";
        arr[1]="1:creating plan for quittting.";
        arr[2]="2:weaning off e cigarette.";
        arr[3]="3:Quitting Cold Turkey.";
        arr[4]="4:Dealing with Nicotine Withdrawal.";
        arr[5]="5:Increasing Your Success.";
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.layout,R.id.textViews, arr);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 1:
                         intent=new Intent(getApplicationContext(),Way1.class);
                         startActivity(intent);
                         break;
                    case 2:
                        intent=new Intent(getApplicationContext(),Way2.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent=new Intent(getApplicationContext(),Way3.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent=new Intent(getApplicationContext(),Way4.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent=new Intent(getApplicationContext(),Way5.class);
                        startActivity(intent);
                        break;
                }
            }
        });
       }}
