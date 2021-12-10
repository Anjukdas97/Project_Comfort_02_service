package com.example.comfortservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MAXAC  {
    private int temp,speed;
    public MAXAC(){

    }



    public MAXAC(int temp,int speed){
this.temp=temp;
this.speed=speed;
    }
}
