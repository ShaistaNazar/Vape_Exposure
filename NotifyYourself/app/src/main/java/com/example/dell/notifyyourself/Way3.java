package com.example.dell.notifyyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Way3 extends AppCompatActivity {

    //ListView lv;
    Button logout,gotomain;
    SessionManager session;
    AlertDialogManager alert;
    BluetoothChatService mChatService;
    //List<Map<String,Object>> list=new LinkedList<>();
//    int [] images={R.drawable.k31,R.drawable.l32,R.drawable.m33};
//    String [] data={"Schedule the day you want to quit vaping.\n" +
//            "By picking a specific day, you will avoid procrastinating about quitting. It will also give you a little bit of time to prepare for quitting itself. This will increase your chances of success.\n" +
//            "--------","Throw away all of your e-cigarettes.\n" +
//            "The night before you quit, throw away all of your e-cigarettes and liquids. Take the trash out so that you cannot retrieve these supplies when withdrawal kicks in.[15]\n" +
//            "You may start feeling cravings as soon as 1 hour after your last e-cigarette.\n" +
//            "--------","Keep yourself busy during the withdrawal period.\n" +
//            "Withdrawal can be more intense for people quitting cold turkey than those weaning off of nicotine slowly. To be prepared for this, plan to keep yourself active and occupied throughout the withdrawal period.[16]\n" +
//            "Schedule activities for yourself when you usually vape. For example, take a nighttime pottery class instead of sitting at home. It is a good idea to plan more activities than you may have time for, just to avoid any downtime.[17]\n" +
//            "Incorporate some light exercise into your routine. You might go for a run after work or take a brisk walk when you wake up.\n" +
//            "If you have some vacation days saved up, go on a retreat to break yourself entirely of the habit. Don’t bring any e-cigarettes with you.\n--------"};
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way3);
       // lv=(ListView)findViewById(R.id._dynamic3);
//        gotomain=(Button)findViewById(R.id.button10);
//        logout=(Button)findViewById(R.id.button12);
//        for(int i=0;i<3;i++)
//        {
//            Map<String,Object> map=new HashMap<>();
//            map.put("image",images[i]);
//            map.put("data",data[i]);
//            list.add(map);
//        }
//        String [] from={"image","data"};
//        int [] to={R.id.imageView5,R.id.editText};
//        SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,R.layout.adapter_view,from,to);
//        lv.setAdapter(simpleAdapter);
//        CustomAdapter1 customAdapter = new CustomAdapter1(getApplicationContext(),data,images);
//        lv.setAdapter(customAdapter);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                session.logoutUser();
//                mChatService.stop();
//                alert.showAlertDialog(Way3.this, "service closed..", "unable to receive detector alerts anymore", false);
//            }
//        });
//        gotomain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Way3.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }}