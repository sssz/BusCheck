package com.sssz.buscheck;

/**
 * Created by sssz on 2018/2/22.
 */

public class Passanger {
    String up, down;
    int handIn;
    int handOut;
    int shouldGet;
    int realGet;
    int price, num;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Passanger(String up, String down, int handIn, int handOut, int shouldGet, int realGet, int price, int num) {

        this.up = up;
        this.down = down;
        this.handIn = handIn;
        this.handOut = handOut;
        this.shouldGet = shouldGet;
        this.realGet = realGet;
        this.price = price;
        this.num = num;
    }

    public String getUp() {

        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public int getHandIn() {
        return handIn;
    }

    public void setHandIn(int handIn) {
        this.handIn = handIn;
    }

    public int getHandOut() {
        return handOut;
    }

    public void setHandOut(int handOut) {
        this.handOut = handOut;
    }

    public int getShouldGet() {
        return shouldGet;
    }

    public void setShouldGet(int shouldGet) {
        this.shouldGet = shouldGet;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRealGet() {
        return realGet;
    }

    public void setRealGet(int realGet) {
        this.realGet = realGet;
    }
}
