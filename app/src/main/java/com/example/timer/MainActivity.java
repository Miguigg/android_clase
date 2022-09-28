package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private TextView t1;
    private TextView t2;
    private Button b;
    int hour,minutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.setAlarm);
        t1 = findViewById(R.id.actualDate);
        t2 = findViewById(R.id.alarm);
        SimpleDateFormat f = new SimpleDateFormat("dd.MM.yy");
        String fecha = f.format(new Date());
        t1.setText(fecha);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minutes = c.get(Calendar.MINUTE);
                TimePickerDialog tp = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
                        t2.setText(hour + ":" + minutes);
                    }
                },hour,minutes,false);
            }
        });
    }
}