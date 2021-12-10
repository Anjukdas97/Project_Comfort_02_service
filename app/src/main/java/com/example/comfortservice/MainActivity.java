package com.example.comfortservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private static List<Integer> maxac;
public  static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,MyService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            startForegroundService(intent);
        }else{
            this.startService(intent);

        }

    }
//    public static List<Integer> maxList(){
////        List<Integer> maxac=new ArrayList<>();
////        SharedPreferences sh = context.getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
////        SharedPreferences.Editor editor = sh.edit();
////        int temp=sh.getInt("temp",0);
////        int fan=sh.getInt("fanspeed",0);
////        maxac.add(temp);
////        maxac.add(fan);
////        return maxac;
//    }
}