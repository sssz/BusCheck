package com.sssz.buscheck;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sssz on 2018/2/22.
 */

public class PassAdapter extends BaseAdapter {


    private Activity mActivity;
    private ArrayList<Passanger> mData;

    public PassAdapter(Activity activity, ArrayList<Passanger> data) {
        mActivity = activity;
        mData = data;
    }
    public void addPassanger(Passanger p){
        if(mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(p);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if(mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        if(mData == null) {
            return null;
        }
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            view = View.inflate(mActivity, R.layout.passanger_item, null);
            holder = new ViewHolder();
            holder.up = (TextView)view.findViewById(R.id.up);
            holder.down = (TextView)view.findViewById(R.id.down);
            holder.price = (TextView)view.findViewById(R.id.price);
            holder.num = (TextView)view.findViewById(R.id.num);
            holder.shouldGet = (TextView)view.findViewById(R.id.shouldGet);
            holder.handIn = (TextView)view.findViewById(R.id.handIn);
            holder.handOut = (TextView)view.findViewById(R.id.handOut);
            holder.realGet = (TextView)view.findViewById(R.id.realGet);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if(mData != null) {
            holder.up.setText(mData.get(i).getUp());
            holder.down.setText(mData.get(i).getDown());
            holder.price.setText(new Integer(mData.get(i).getPrice()).toString());
            holder.num.setText(new Integer(mData.get(i).getNum()).toString());
            holder.shouldGet.setText(new Integer(mData.get(i).getShouldGet()).toString());
            holder.handIn.setText(new Integer(mData.get(i).getHandIn()).toString());
            holder.handOut.setText(new Integer(mData.get(i).getHandOut()).toString());
            holder.realGet.setText(new Integer(mData.get(i).getRealGet()).toString());
        }
        return view;
    }

    class ViewHolder {
        TextView up, down;
        TextView price, num, shouldGet, handIn, handOut, realGet;
    }
}
