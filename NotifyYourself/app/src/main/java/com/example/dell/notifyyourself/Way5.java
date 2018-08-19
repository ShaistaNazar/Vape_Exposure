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

public class Way5 extends AppCompatActivity {
    //ListView lv;
    Button logout,gotomain;
    SessionManager session;
    AlertDialogManager alert;
    BluetoothChatService mChatService;
    //List<Map<String,Object>> list=new LinkedList<>();
//    int [] images={R.drawable.s51,R.drawable.t52,R.drawable.u53};
//    String [] data={"Join a support group.\n" +
//            "Groups like Nicotine Anonymous often meet at community centers, houses of worship, and hospitals. These groups will connect you with others who are struggling to kick their nicotine habit. They can provide advice and support throughout the process of quitting.[24]\n" +
//            "You can find a local Nicotine Anonymous group by going here: https://nicotine-anonymous.org/find-a-meeting.html\n" +
//            "There are also online support groups, such as Voices of Nicotine Recovery and In the Rooms.\n" +
//            "---------","Call a helpline if you need additional support.\n" +
//            "These phone lines are free to use. They will connect you to a speaker who can talk you through your cravings and motivate you to continue\n" +
//            "----------","Reduce stress in your life.\n" +
//            "Quitting vaping may increase irritability or anxiety in your life, both of which can affect your personal relationships and cause new stress. To increase your chances of success, try to eliminate any sources of stress.[26]\n" +
//            "Try to avoid taking on new responsibilities at work or in your social life until you have successfully quit.\n" +
//            "Avoid people and situations that make you feel stressed. For example, if you get nervous at large parties, stick to smaller social events until you have quit.\n" +
//            "Look into relaxation techniques, such as meditation or tai chi. Take some time each day to unwind. You might take a hot bath, get a massage, or read a book.\n" +
//            "----------"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way5);
        //lv=(ListView)findViewById(R.id._dynamic5);
//        gotomain=(Button)findViewById(R.id.button17);
//        logout=(Button)findViewById(R.id.button18);
//        for(int i=0;i<4;i++)
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
//                session.logoutUser();
//                mChatService.stop();
//                alert.showAlertDialog(Way5.this, "service closed..", "unable to receive detector alerts anymore", false);
//
//            }
//        });
//        gotomain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Way5.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }}
