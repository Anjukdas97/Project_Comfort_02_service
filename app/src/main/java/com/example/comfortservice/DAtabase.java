package com.example.comfortservice;

import android.content.SharedPreferences;

public class DAtabase extends MyService{
    SharedPreferences sh = getSharedPreferences("COMFORT", MODE_PRIVATE);
    SharedPreferences.Editor editor = sh.edit();


}
