package com.sssz.buscheck;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;


public class AddPassanger extends AppCompatActivity {

    private OptionsPickerView startOptions, endOptions, numOptions, inOptions, outOptions;
    private ArrayList<String> startItems = new ArrayList<>();
    private ArrayList<String> endItems = new ArrayList<>();
    private ArrayList<Integer> numItems = new ArrayList<>();
    private ArrayList<Integer> handInItems = new ArrayList<>();
    private ArrayList<Integer> handOutItems = new ArrayList<>();

    private TextView start, end, price, num, should, handIn, handOut, real;
    private String s,e;
    private Button addFinish;
    private static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passanger);
        initData();
        initView();
        initOptionPicker();
        initHandler();
        initClickListener();
        price.setText(String.valueOf(getPrice()));
    }
    private void initData(){
        s = getIntent().getStringExtra(ConstVar.start);
        e = getIntent().getStringExtra(ConstVar.end);
        if(ConstVar.locations.get(0).equals(s)) {
            for(int i=0; i<ConstVar.locations.size(); i++){
                startItems.add(ConstVar.locations.get(i));
            }
            for(int i=ConstVar.locations.size()-1; i>=0; i--){
                endItems.add(ConstVar.locations.get(i));
            }
        } else {
            for(int i=0; i<ConstVar.locations.size(); i++){
                endItems.add(ConstVar.locations.get(i));
            }
            for(int i=ConstVar.locations.size()-1; i>=0; i--){
                startItems.add(ConstVar.locations.get(i));
            }
        }
        for(int i=1; i<40; i++){
            numItems.add(i);
        }
        for(int i=1; i<=200; i++){
            handInItems.add(i);
        }
        for(int i=0; i<=200; i++){
            handOutItems.add(i);
        }
    }

    private void initView(){
        start= (TextView) findViewById(R.id.addPassangerStart);
        end= (TextView) findViewById(R.id.addPassangerEnd);
        start.setText(s);
        end.setText(e);
        price= (TextView) findViewById(R.id.addPassangerPrice);
        num= (TextView) findViewById(R.id.addPassangerNum);
        should= (TextView) findViewById(R.id.addPassangerShould);
        handIn= (TextView) findViewById(R.id.addPassangerIn);
        handOut= (TextView) findViewById(R.id.addPassangerOut);
        real= (TextView) findViewById(R.id.addPassangerReal);
        addFinish = (Button) findViewById(R.id.addPassangerFinish);
    }
    private void initOptionPicker() {
        startOptions = pickerBuilder(startItems, start, ConstVar.startName, 1);
        endOptions = pickerBuilder(endItems, end, ConstVar.endName, 2);
        numOptions = pickerBuilder(numItems, num, ConstVar.numName, 3);
        inOptions = pickerBuilder(handInItems, handIn, ConstVar.handInName, 4);
        outOptions = pickerBuilder(handOutItems, handOut, ConstVar.handOutName, 5);
    }
    private void initClickListener(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startOptions.show();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endOptions.show();
            }
        });
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numOptions.show();
            }
        });
        handIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inOptions.show();
            }
        });
        handOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outOptions.show();
            }
        });
        addFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.putExtra(ConstVar.start, start.getText().toString());
                intent.putExtra(ConstVar.end, end.getText().toString());
                intent.putExtra(ConstVar.price, price.getText().toString());
                intent.putExtra(ConstVar.num, num.getText().toString());
                intent.putExtra(ConstVar.should, should.getText().toString());
                intent.putExtra(ConstVar.handIn, handIn.getText().toString());
                intent.putExtra(ConstVar.handOut, handOut.getText().toString());
                intent.putExtra(ConstVar.realGet, real.getText().toString());
                setResult(ConstVar.AddPassangerResultCode, intent);
                finish();
            }
        });


    }
    private void initHandler(){
        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int p,n,i,s,o;
                switch (msg.what) {
                    case 1:
                        start.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        end.setTextColor(Color.BLACK);
                        price.setText(String.valueOf(getPrice()));
                        break;
                    case 3:
                        p = Integer.valueOf(price.getText().toString());
                        n = Integer.valueOf(num.getText().toString());
                        should.setText(String.valueOf(p*n));
                        inOptions.setSelectOptions(p*n);
                        num.setBackgroundColor(price.getDrawingCacheBackgroundColor());
                        break;
                    case 4:
                        i = Integer.valueOf(handIn.getText().toString());
                        s = Integer.valueOf(should.getText().toString());
                        handOut.setText(String.valueOf(i-s));
                        outOptions.setSelectOptions(i-s);
                        handIn.setBackgroundColor(real.getDrawingCacheBackgroundColor());
                        break;
                    case 5:
                        i = Integer.valueOf(handIn.getText().toString());
                        o = Integer.valueOf(handOut.getText().toString());
                        real.setText(String.valueOf(i-o));
                        handOut.setBackgroundColor(real.getDrawingCacheBackgroundColor());
                        break;
                }
            }

        };
    }

    private OptionsPickerView pickerBuilder(final ArrayList<?> items, final TextView show,String title, final int actionCode){
        OptionsPickerView result =  new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = items.get(options1).toString();
                show.setText(tx);
                handler.sendEmptyMessage(actionCode);
            }
        })
                .setTitleText(ConstVar.choose + title)
                .setContentTextSize(25)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setCancelText("取消")
                .setCancelColor(Color.RED)
                .setSubmitText("确认")
                .setSubmitColor(Color.GREEN)
                .setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .isDialog(true)
                .build();
        result.setPicker(items);
        return result;
    }

    private int getPrice(){

        String s = start.getText().toString();
        String e = end.getText().toString();

        for(int i=0; i<ConstVar.priceSet.size(); i+=3){
            if(ConstVar.locations.get(ConstVar.priceSet.get(i)).equals(s) &&
                    ConstVar.locations.get(ConstVar.priceSet.get(i+1)).equals(e)){
                return ConstVar.priceSet.get(i+2);
            }
            if(ConstVar.locations.get(ConstVar.priceSet.get(i+1)).equals(s) &&
                    ConstVar.locations.get(ConstVar.priceSet.get(i)).equals(e)){
                return ConstVar.priceSet.get(i+2);
            }
        }
        return ConstVar.MIN_PRICE;
    }
}
