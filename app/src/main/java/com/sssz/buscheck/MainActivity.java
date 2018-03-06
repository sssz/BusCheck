package com.sssz.buscheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button start1, start2;
    private TextView start, todayMoneyTv;

    private int startLocationNum;

    private static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setClickListener();
        initHandler();
    }
    private void initView(){
        start1 = (Button) findViewById(R.id.start1);
        start2 = (Button) findViewById(R.id.start2);
        start = (TextView)findViewById(R.id.start);
        todayMoneyTv = (TextView) findViewById(R.id.todayMoney);
        Calendar calendar= Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH)+1) + "_" + calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences moneyLog= getSharedPreferences("log", 0);
        String nowMoney = moneyLog.getString(date,null);
        if(nowMoney == null) {
            SharedPreferences.Editor editor = moneyLog.edit();
            editor.putString(date, "0");
            editor.commit();
            updateView(0);
        } else {
            updateView(Integer.valueOf(nowMoney));
        }

    }

    private void updateView(int tm){
        todayMoneyTv.setText(ConstVar.todayMoneyName  + tm);
    }
    private void setClickListener(){
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(1);
            }
        });
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(2);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(3);
            }
        });

    }
    private void initHandler(){
        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        start1.setBackgroundColor(Color.parseColor("#76EE00"));
                        start2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        startLocationNum = 1;
                        break;
                    case 2:
                        start1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        start2.setBackgroundColor(Color.parseColor("#76EE00"));
                        startLocationNum = 2;
                        break;
                    case 3:
                        Intent intent = new Intent(MainActivity.this, CheckPassanger.class);
                        String start = null, end = null;
                        if(startLocationNum == 1){
                            start = start1.getText().toString();
                            end = start2.getText().toString();
                        } else if(startLocationNum == 2){
                            start = start2.getText().toString();
                            end = start1.getText().toString();
                        }
                        intent.putExtra(ConstVar.start, start);
                        intent.putExtra(ConstVar.end, end);
                        startActivityForResult(intent, ConstVar.StartCheckRequestCode);
                        break;
                }
            }

        };
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ConstVar.StartCheckRequestCode && resultCode == ConstVar.StartCheckResultCode){
            String currentCheck = data.getStringExtra(ConstVar.currentCheck);
            Calendar calendar= Calendar.getInstance();
            String date = calendar.get(Calendar.YEAR) + "_" + (calendar.get(Calendar.MONTH)+1) + "_" + calendar.get(Calendar.DAY_OF_MONTH);
            SharedPreferences moneyLog= getSharedPreferences("log", 0);
            String nowMoney = moneyLog.getString(date,"0");
            SharedPreferences.Editor editor = moneyLog.edit();
            if(nowMoney.equals("0")) {
                editor.putString(date, currentCheck);
            } else {
                editor.putString(date, String.valueOf(Integer.valueOf(currentCheck) + Integer.valueOf(nowMoney)));
            }
            editor.commit();
            updateView(Integer.valueOf(currentCheck) + Integer.valueOf(nowMoney));
        }
    }
}
