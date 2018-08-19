package com.example.dell.notifyyourself;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2ActivityRecord extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText deleteId;
    Button btnviewAll;
    Button btnDelete,btnAllDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_record);
        deleteId=(EditText)findViewById(R.id.deleteEditText);
        myDb = new DatabaseHelper(this);
        btnviewAll = (Button)findViewById(R.id.vd);
        btnDelete= (Button)findViewById(R.id.dd);
        btnAllDelete= (Button)findViewById(R.id.dad);
//       // AddData();
//        viewAll();
//        DeleteData();


        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(deleteId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Main2ActivityRecord.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main2ActivityRecord.this,"no more data to delete",Toast.LENGTH_LONG).show();
                    }
                }
        );
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            Toast.makeText(getApplicationContext(),"ERROR...Nothing Found",Toast.LENGTH_LONG).show();
                            return;
                        }

                        StringBuilder buffer = new StringBuilder();
                        while (res.moveToNext()) {
                            buffer.append("Id :").append(res.getString(0)).append("\n");
                            buffer.append("Date :").append(res.getString(1)).append("\n");
                            buffer.append("Time :").append(res.getString(2)).append("\n");
                            buffer.append("Intensity:").append(res.getString(3)).append("\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
        btnAllDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 boolean bol=myDb.deleteAllData();
                 if(bol){
                     Toast.makeText(Main2ActivityRecord.this,"Done",Toast.LENGTH_LONG).show();
                 }else
                Toast.makeText(Main2ActivityRecord.this,"Done",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}