package com.example.caoviethoang_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edname, eddate, edtime;
    CheckBox cb;
    Button btadd,btgetALl;
    SQLiteHelperLichThi sqLiteHelperLichThi;
    RecyclerView recyclerView;
    SearchView searchView;
    LichThiAdapter adapter;
    int year, month, day;
    int hour, minute;
    TextView txtsize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname = findViewById(R.id.edname);
        eddate = findViewById(R.id.eddate);
        edtime = findViewById(R.id.edtime);
        cb = findViewById(R.id.activate);
        txtsize = findViewById(R.id.txtds);
        btadd = findViewById(R.id.btadd);
        recyclerView = findViewById(R.id.recyleview);
        searchView = findViewById(R.id.searchview);
        btgetALl=findViewById(R.id.btall);
        adapter=new LichThiAdapter();
        sqLiteHelperLichThi = new SQLiteHelperLichThi(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edname.getText().toString();
                String date = eddate.getText().toString();
                String time = edtime.getText().toString();
                boolean thi;
                if (cb.isChecked()) {
                    thi = true;
                } else {
                    thi = false;
                }
                sqLiteHelperLichThi.add(new LichThi(name, date, time, thi));
                loadData();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<LichThi> list = sqLiteHelperLichThi.getByname(newText);
                adapter.getAll(list);
                recyclerView.setAdapter(adapter);
                txtsize.setText(String.valueOf(adapter.getItemCount()));
                return true;
            }
        });
        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                day = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtime.setText(""+hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });
        btgetALl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatathi();
            }
        });
    }
    public void loadData(){
        List<LichThi> list=sqLiteHelperLichThi.getAll();
        adapter.getAll(list);
        recyclerView.setAdapter(adapter);
        txtsize.setText(String.valueOf(adapter.getItemCount()));
    }
    public void loadDatathi(){
        List<LichThi> list=sqLiteHelperLichThi.getByThi();
        adapter.getAll(list);
        recyclerView.setAdapter(adapter);
        txtsize.setText(String.valueOf(adapter.getItemCount()));
    }
}