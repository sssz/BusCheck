package com.sssz.buscheck;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



public class CheckPassanger extends AppCompatActivity {

    private Button addPassanger, finish;
    private ListView listView;
    private TextView currentMoneyTv;

    private int currentMoney;
    private String start = null, end = null;
    private static Handler handler;
    private PassAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_passanger);
        initData();
        initView();
        initHandler();
        initClickListener();
        updateView();
    }
    private void initData(){
        start = getIntent().getStringExtra(ConstVar.start);
        end = getIntent().getStringExtra(ConstVar.end);
        currentMoney = 0;
    }
    private void initView(){
        addPassanger = (Button) findViewById(R.id.addPassanger);
        finish = (Button) findViewById(R.id.finish);
        listView = (ListView) findViewById(R.id.passangers);
        mAdapter = new PassAdapter(this, null);
        listView.setAdapter(mAdapter);
        currentMoneyTv = (TextView) findViewById(R.id.currentMoney);
    }

    private void updateView(){
        currentMoneyTv.setText(ConstVar.currentCheckMoneyName + String.valueOf(currentMoney));

    }
    private void initClickListener(){
        addPassanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(1);

            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(2);
            }
        });


    }
    private void initHandler(){
        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent;
                switch (msg.what) {
                    case 1:
                        intent = new Intent(CheckPassanger.this, AddPassanger.class);
                        intent.putExtra(ConstVar.start, start);
                        intent.putExtra(ConstVar.end, end);
                        startActivityForResult(intent, ConstVar.AddPassangerRequestCode);
                        break;
                    case 2:
                        intent = new Intent();
                        intent.putExtra(ConstVar.currentCheck, String.valueOf(currentMoney));
                        setResult(ConstVar.StartCheckResultCode, intent);
                        finish();
                        break;
                }
            }

        };
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ConstVar.AddPassangerRequestCode && resultCode == ConstVar.AddPassangerResultCode){
            mAdapter.addPassanger(new Passanger(
                    data.getStringExtra(ConstVar.start),
                    data.getStringExtra(ConstVar.end),
                    Integer.valueOf(data.getStringExtra(ConstVar.handIn)),
                    Integer.valueOf(data.getStringExtra(ConstVar.handOut)),
                    Integer.valueOf(data.getStringExtra(ConstVar.should)),
                    Integer.valueOf(data.getStringExtra(ConstVar.realGet)),
                    Integer.valueOf(data.getStringExtra(ConstVar.price)),
                    Integer.valueOf(data.getStringExtra(ConstVar.num))));
            currentMoney += Integer.valueOf(data.getStringExtra(ConstVar.realGet));
            updateView();
        }
    }
}


