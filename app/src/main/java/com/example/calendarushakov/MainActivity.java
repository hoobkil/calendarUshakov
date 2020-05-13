package com.example.calendarushakov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button chooseStartDate;
    private Button chooseEndDate;
    private CalendarView startDateCalendar;
    private CalendarView endDateCalendar;
    private Button buttonOk;

    private long startDate;
    private String startDateTxt;
    private long endDate;
    private String endDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        chooseStartDate = findViewById(R.id.chooseStartDate);
        chooseEndDate = findViewById(R.id.chooseEndDate);
        startDateCalendar = findViewById(R.id.startDateCalendar);
        endDateCalendar = findViewById(R.id.endDateCalendar);
        buttonOk = findViewById(R.id.buttonOk);

        startDateCalendar.setVisibility(View.GONE);
        endDateCalendar.setVisibility(View.GONE);




        chooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateCalendar.setVisibility(View.VISIBLE);
                endDateCalendar.setVisibility(View.GONE);
            }
        });

        chooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDateCalendar.setVisibility(View.VISIBLE);
                startDateCalendar.setVisibility(View.GONE);
            }
        });

        startDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int dayOfMonth) {
                startDateTxt = year + "-" + month + "-" + dayOfMonth;
                chooseStartDate.setText(getString(R.string.timeStart) + startDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                startDate = gregorianCalendar.getTimeInMillis();
                view.setVisibility(View.GONE);
            }
        });

        endDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                endDateTxt = year + "-" + month + "-" + dayOfMonth;
                chooseEndDate.setText(getString(R.string.timeEnd) + endDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(year, month, dayOfMonth);
                endDate = gregorianCalendar.getTimeInMillis();
                view.setVisibility(View.GONE);
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startDate > endDate) {
                    Toast.makeText(MainActivity.this, R.string.dateError,
                            Toast.LENGTH_SHORT).show();
                    chooseStartDate.setText(R.string.timeStart);
                    chooseEndDate.setText(R.string.timeEnd);
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.start)
                            + startDateTxt + '\n'
                            + getString(R.string.end) + endDateTxt, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
