package com.example.caoviethoang_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class LichThiActivity extends AppCompatActivity {
    EditText edid,edname,eddate,edtime;
    CheckBox cb;
    Button btupdate,btdelete,btback;
    String id,name,date,time,thi;
    SQLiteHelperLichThi sqLiteHelperLichThi;
    int year, month, day;
    int hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_thi);
        edid=findViewById(R.id.edID);
        edname = findViewById(R.id.edname);
        eddate = findViewById(R.id.eddate);
        edtime = findViewById(R.id.edtime);
        cb = findViewById(R.id.activate);
        btupdate = findViewById(R.id.btupdate);
        btdelete = findViewById(R.id.btdelete);
        btback = findViewById(R.id.btback);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        date=intent.getStringExtra("date");
        time=intent.getStringExtra("time");
        thi=intent.getStringExtra("thi");
        edname.setText(name); edid.setText(id);
        eddate.setText(date);
        edtime.setText(time);
        cb.setChecked(Boolean.parseBoolean(thi));
        sqLiteHelperLichThi=new SQLiteHelperLichThi(this);
        edid.setEnabled(false);
        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= Integer.parseInt(edid.getText().toString());
                String named=edname.getText().toString();
                String dateed=eddate.getText().toString();
                String time=edtime.getText().toString();
                boolean flag;
                if(cb.isChecked()){
                    flag=true;
                }else{
                    flag=false;
                }
                sqLiteHelperLichThi.update(new LichThi(id,named,dateed,time,flag));
                Intent intent1=new Intent(LichThiActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                day = calender.get(Calendar.DAY_OF_MONTH);
                if(getDay(date)< day){
                    int id= Integer.parseInt(edid.getText().toString());
                    sqLiteHelperLichThi.delete(id);
                    Intent intent1=new Intent(LichThiActivity.this,MainActivity.class);
                    startActivity(intent1);
                }else {
                    Toast.makeText(LichThiActivity.this,"xoa bi loi ",Toast.LENGTH_LONG).show();
                }
            }
        });
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LichThiActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                day = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(LichThiActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        eddate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        edtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                hour = calender.get(Calendar.HOUR_OF_DAY);
                minute = calender.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(LichThiActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtime.setText(""+hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });
    }
    private int getDay(String cn){
        String [] time_spilt=cn.split("/");
        int day=Integer.parseInt(time_spilt[0]);
        int month=Integer.parseInt(time_spilt[1])-1;
        int year=Integer.parseInt(time_spilt[2]);
        return day;
    }
}
