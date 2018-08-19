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

public class Way4 extends AppCompatActivity {

    //ListView lv;
    Button logout,gotomain;
    SessionManager session;
    AlertDialogManager alert;
    BluetoothChatService mChatService;
    //List<Map<String,Object>> list=new LinkedList<>();
//    int [] images={R.drawable.n41,R.drawable.o42,R.drawable.p43,R.drawable.q44,R.drawable.r45};
//    String [] data={"Prepare for side effects lasting up to a month.\n" +
//            "Everyone experiences withdrawal differently. You may have increased appetite, insomnia, strange dreams, chills, anxiety, heartburn, or a number of other effects. In most cases, however, these symptoms only last up to a month.\n" +
//            "Insomnia usually only lasts for the first week. If you have insomnia after this week, see a doctor.\n" +
//            "You may feel hungrier than normal for the first 2 weeks. Instead of reaching for sugary or processed snacks, try snacking on vegetables and fruits. Some good snacks include carrots and hummus, celery and peanut butter, or apple slices.\n" +
//            "As time goes on, cravings will grow farther and farther apart. You may still feel occasional cravings for up a year after quitting.\n" +
//            "---------","Chew gum or hard foods when you get a craving.\n" +
//            "The act of chewing can distract your brain from the craving. If you don’t like gum, vegetables or fruits—like carrots, apples, or celery—also work well. You can even suck on a piece of hard candy to keep your mouth busy.\n" +
//            "----------","Use nicotine gum, lozenges, or patches to help manage cravings.\n" +
//            "You can get these without a prescription at a drugstore. Over time, you may reduce the dose of nicotine you use until you are free from nicotine entirely. Talk to your doctor to find the best option for you.\n" +
//            "You can chew gum until your mouth tingles. Stick the gum between your cheek and teeth to absorb the nicotine. Choose a flavor of gum that was similar to your favorite e-liquid flavor to help make the switch more successful.\n" +
//            "Lozenges are a type of hardy candy. Suck on them to slowly dissolve nicotine in your mouth.\n" +
//            "Patches are placed on the skin. They give a steady amount of nicotine over time.\n" +
//            "---------","Give yourself rewards when you reach your goals.\n" +
//            "Rewards will teach your brain that good things happen when you avoid vaping. Create small rewards for both little victories and big ones.\n" +
//            "For example, you might eat a small piece of chocolate when you resist a strong craving.\n" +
//            "You might go see a movie or visit a water park after 1 week of no vaping.\n" +
//            "Save the money you used to spend on vaping. You can put it towards a vacation or buy yourself something nice.\n" +
//            "--------","Get some extra sleep.\n" +
//            "Nicotine is a stimulant, which means that it makes you feel alert and awake. Without it, you may feel tired or sleepy. \n" +
//            "Try going to bed earlier in the evening to help prevent this feeling of exhaustion. You may also want to plan daily naps.\n---------"};
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way4);
        //lv=(ListView)findViewById(R.id._dynamic4);
//        gotomain=(Button)findViewById(R.id.button13);
//        logout=(Button)findViewById(R.id.button14);
//        for(int i=0;i<5;i++)
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
//                alert.showAlertDialog(Way4.this, "service closed..", "unable to receive detector alerts anymore", false);
//
//            }
//        });
//        gotomain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Way4.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }}
