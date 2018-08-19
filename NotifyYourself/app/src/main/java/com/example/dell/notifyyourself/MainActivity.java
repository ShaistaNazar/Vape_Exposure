package com.example.dell.notifyyourself;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.media.RingtoneManager.getDefaultUri;


public class MainActivity extends AppCompatActivity {

    //declarations
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    // Session Manager Class
    SessionManager session;
    //widgets
    ListView listView;
    NotificationManager mNotificationManager;
    Context context;
    NavigationView navigationView;
   // Spinner spinner;
    Button discover, stopService, records, finddevice;
    TextView status,headerName,headerEmail;
    ArrayAdapter<String> arrayAdapter;

    BluetoothDevice bluetoothDevice1;
    BluetoothAdapter bluetoothAdapter;
    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 3;
    public static final int MESSAGE_READ = 4;
    public static final int MESSAGE_WRITE = 5;
    public static final int MESSAGE_DEVICE_NAME = 6;
    public static final int MESSAGE_TOAST = 7;
    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "HC-05";
    public static final String TOAST = "toast";
    private static final String APP_Name = "Notify Yourself";
    //  private static  final UUID MY_UUID=UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    private static final boolean D = true;
    BluetoothChatService mChatService = null;
    DatabaseHelper databaseHelper;
    //@RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    private StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {    // Member object for the chat services

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //session initialization
        session = new SessionManager(getApplicationContext());

        session.checkLogin();
        //Toast.makeText(getApplicationContext(),"login",Toast.LENGTH_SHORT).show();
        session.checkSignUp();
       // Toast.makeText(getApplicationContext(),"signup",Toast.LENGTH_SHORT).show();
        //FragmentManager fragmentManager=getSupportFragmentManager();
        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }
        databaseHelper=new DatabaseHelper(getApplicationContext());
        findViewByIds();

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // session.editor.clear();
       // session.editor.commit();

        if(mChatService==null)
            mChatService=new BluetoothChatService(getApplicationContext(),myHandler);

       // getSpinner();
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        setupDrawerContent(navigationView);
        View header = ((NavigationView)findViewById(R.id.nv)).getHeaderView(0);
        HashMap<String, String> user = session.getUserDetails();

        implementation();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//    private void getSpinner() {
//        List<String> list = new ArrayList<>();
//        list.add("Change Password");
//        list.add("Jump To Log_in");
//        //list.add("change user details");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item, list);
//        spinner.setAdapter(dataAdapter);
//    }

    @Override
    public void onStart() {
        super.onStart();
        if(D) Log.e(APP_Name, "++ ON START ++");
        // If BT is not on, request that it be enabled.
        // setupChat() will then be called during onActivityResult
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);


        }
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if(D) Log.e(APP_Name, "+ ON RESUME +");
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.

        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }else {
                Toast.makeText(getApplicationContext(),"not state none",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"service null",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public synchronized void onPause() {
        super.onPause();
        if(D) Log.e(APP_Name, "- ON PAUSE -");
    }
    @Override
    public void onStop() {
        super.onStop();
        if(D) Log.e(APP_Name, "-- ON STOP --");
    }
    private void findViewByIds() {
        finddevice = (Button) findViewById(R.id.finddevicebtn);
        status = (TextView) findViewById(R.id.textview2);
        discover= (Button) findViewById(R.id.discoverbtn);
        records = (Button) findViewById(R.id.recordbtn);
        listView=(ListView)findViewById(R.id.listview);
        //spinner=(Spinner)findViewById(R.id.spinner);
        drawerLayout=(DrawerLayout)findViewById(R.id.l);
        stopService=(Button)findViewById(R.id.stopservicebtn);
        navigationView=(NavigationView)findViewById(R.id.nv);
    }
int chck=0;
    private void implementation() {

        discover.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public void onClick(View v) {if (mChatService == null)
                    mChatService = new BluetoothChatService(getApplicationContext(), myHandler);
               // if (mChatService != null) {
                    // Only if the state is STATE_NONE, do we know that we haven't started already
                   // if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                        // Start the Bluetooth chat services
                        mChatService.start();
                        status.setText("service started");
                   // }
                //}
            }
        });

        finddevice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public void onClick(View v) {
                final Set<BluetoothDevice> bluetoothDevices = bluetoothAdapter.getBondedDevices();

                if (bluetoothDevices.size() > 0) {
                    for (BluetoothDevice bluetoothDevice : bluetoothDevices) {
                        if (bluetoothDevice.getName().equals(DEVICE_NAME)) {
                            bluetoothDevice1 = bluetoothDevice;
                            status.setText(" Device Found");
                            String[] arr=new String[1];
                            arr[0]=bluetoothDevice1.getName();
                            arrayAdapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.layout,R.id.textViews, arr);
                            listView.setAdapter(arrayAdapter);
                            break;
                        }
                        status.setText("Searching for Device");
                    }
                }else {
                    status.setText("Device not found");
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(bluetoothDevice1!=null){
                mChatService.connect(bluetoothDevice1);
                status.setText("connecting");
            }}
        });


//       int chck=0;
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position){
//                    case 0:
//                        if(++chck>1) {
//                            Intent intnt = new Intent(MainActivity.this, Confermation.class);
//                            startActivity(intnt);
//                        }
//                        break;
//                    case 1:
//                        if(++chck>1) {
//                            Intent intntt = new Intent(MainActivity.this, Login_Activity.class);
//                            startActivity(intntt);
//                        }
//                        break;
//                }
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2ActivityRecord.class);
                startActivity(intent);
            }
        });

//        logout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // Clear the session data
//                // This will clear all session data and
//                // redirect user to LoginActivity
//                session.logoutUser();
//                if (mChatService != null)
//                //mChatService.mAcceptThread.cancel();
//                mChatService.stop();
//                alert.showAlertDialog(MainActivity.this, "Service Closed..", "message cant be received from detector anymore", false);
//            }
//        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChatService.stop();
                alert.showAlertDialog(MainActivity.this, "Service Closed..", "message cant be received from detector anymore", false);

            }
        });

    }



    android.os.Handler myHandler = new android.os.Handler() {
        //@RequiresApi(api = Build.VERSION_CODES.N)
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if (D) Log.i(APP_Name, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            status.setText("Connected");
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            status.setText("Connecting");
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            status.setText("Not Connected Yet");
                            break;
                    }
                    break;
                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf,0, msg.arg1);
                  // if (readMessage.equals("generate message!")) {
//                    sb.append(readMessage);                                                // append string
//                    int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
//                    if (endOfLineIndex > 0) {                                            // if end-of-line,
//                        String sbprint = sb.substring(0, endOfLineIndex);               // extract string
//                        sb.delete(0, sb.length());
                        //status.setText(readMessage);
                        showNotification();
                        insertData(readMessage);
                 //  }
                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    String mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(getApplicationContext(), "Connected to "
                            + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //@RequiresApi(api = Build.VERSION_CODES.ECLAIR)

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.d(APP_Name, "onActivityResult " + resultCode);
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                // When DeviceListActivity returns with a device to connect
                if (resultCode == Activity.RESULT_OK) {
                    // Attempt to connect to the device
                    mChatService.connect(bluetoothDevice1);
                }
                break;
            case REQUEST_ENABLE_BT:
                // When the request to enable Bluetooth returns
                if (resultCode == Activity.RESULT_OK) {
                    // Bluetooth is now enabled, so set up a chat session
//                    if (mChatService == null)
//                        mChatService = new BluetoothChatService(getApplicationContext(), myHandler);
                    status.setText("Bluetooth enabled");
                } else {
                    // User did not enable Bluetooth or an error occured
                    Log.d(APP_Name, "BT not enabled");
                    Toast.makeText(this,"Bluetooth is not being enabled", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }



    public void showNotification() {
//        int notificationId = 13;
//        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.hhgc);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setSmallIcon(R.drawable.notificationicon_icon).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notificationicon_icon))
//                .setContentTitle("vape detected").
//                setStyle(new NotificationCompat.BigPictureStyle().bigPicture(picture))
//                    .setAutoCancel(true);
//            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            builder.setSound(path);
//         NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//             notificationManager.notify(notificationId, builder.build());
//2
        //declare an id for your notification 65
       // id is used in many things especially when setting action buttons and their intents 66
//         int notificationId = 10;
//         //init notification and declare specifications 68
//          NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
//                 .setSmallIcon(R.drawable.notificationicon_icon)
//           .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notificationicon_icon))
//                  .setContentTitle("Vape detected")
//           .setContentText("Be aware!!!Vape done somewhere")
//             .setAutoCancel(true)
//           .setDefaults(NotificationCompat.DEFAULT_ALL);
//                     //set a tone when notification appears 77
//           Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            builder.setSound(path);
//        // call notification manager so it can build and deliver the notification to the OS 81
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//           notificationManager.notify(notificationId,builder.build());
//        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        ///for old versions
//        //Notification notification = new Notification(R.drawable.abc_btn_check_material,"abc",System.currentTimeMillis());
//
//        ///after jellybean
//        String id = "my_channel_01";
//        int importance = NotificationManager.IMPORTANCE_LOW;
//        NotificationChannel mChannel = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            mChannel = new NotificationChannel(id, "name", importance);
//            mChannel.enableLights(true);
//            manager.createNotificationChannel(mChannel);
//            Notification.Builder notification = new Notification.Builder(context, id)
//                    .setSmallIcon(R.drawable.notificationicon_icon)
//                    .setVibrate(new long[]{1000, 1000})
//                    .setContentTitle("vape detected")
//                    .setContentText("Be aware!!!Vape done some where")
//                    .setAutoCancel(true);
//            Intent intent = new Intent(MainActivity.this, MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//            notification.setContentIntent(pendingIntent);
//            manager.notify(0, notification.build());
        int notificationId = 10;
        //**add this line**
        int requestID = (int) System.currentTimeMillis();

        Uri alarmSound = getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mNotificationManager  = (NotificationManager) getApplication().getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

//**add this line**
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

//**edit this line to put requestID as requestCode**
        PendingIntent contentIntent = PendingIntent.getActivity(this, requestID,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.notificationicon_icon)
                .setContentTitle("Vape Detected")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Be Aware ! someone vaped."))
                .setContentText("Be Aware ! someone vaped.").setAutoCancel(true);
        mBuilder.setSound(alarmSound);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(notificationId, mBuilder.build());
        }

    public void insertData(String msg){
        String time = getCurrentTime();
        String[] dateTime = time.split("\\s+");
        String obtainedDate = dateTime[0];
        String obtainedTime = dateTime[1];
        String tempValue = msg+"p/m";
        boolean ans=databaseHelper.insertData(obtainedDate, obtainedTime, tempValue);
        if(ans){
            Toast.makeText(getApplicationContext(),"new record added",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext()," record not added",Toast.LENGTH_LONG).show();
        }
    }
    public String getCurrentTime(){

     // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Create an instance of SimpleDateFormat used for formatting
   // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   // Using DateFormat format method we can create a string
    // representation of a date with the defined format.
        String sendingDate = df.format(today);
        return sendingDate;
    }
    public void selectDrawerItem(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.cp:
                intent=new Intent(MainActivity.this,Confermation.class);
                startActivity(intent);
                break;
            case R.id.jtl:
                session.editor.putBoolean(SessionManager.IS_LOGIN,false);
                intent=new Intent(MainActivity.this,Login_Activity.class);
                // Closing all the Activities
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            case R.id.ht:
                intent=new Intent(MainActivity.this,getInformedActivity.class);
                startActivity(intent);
                break;
            case R.id.h:
                intent=new Intent(MainActivity.this,Help.class);
                startActivity(intent);
                break;
            case R.id.lo:
                // Clear the session data
               //This will clear all session data and
              //redirect user to LoginActivity
                session.editor.clear();
                session.editor.commit();
                session.create_SO_Session();
                databaseHelper.deleteAllData();
                //session.create_LO_Session();
                // After logout redirect user to Loing Activity
                Intent i = new Intent(getApplicationContext(), Signup.class);
                // Closing all the Activities
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                // Staring Login Activity
                getApplicationContext().startActivity(i);

                if (mChatService != null) {
                    mChatService.stop();
                    alert.showAlertDialog(MainActivity.this, "Service Closed..", "message cant be received from detector anymore", false);
                }
                finish();
                break;
        }

      item.setChecked(true);
       // setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}










