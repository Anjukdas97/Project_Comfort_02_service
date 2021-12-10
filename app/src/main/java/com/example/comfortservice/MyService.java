package com.example.comfortservice;

import static java.lang.reflect.Array.getInt;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ServicePackage.ComfortInterface;

public class MyService extends Service {







    public MyService() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        createNotificationChannel();

        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);
        Notification notification = new NotificationCompat.Builder(this, "ChannelId1").setContentTitle("Service application")
                .setContentText("Application Running")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();
        startForeground(1, notification);

        return START_STICKY;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ;
        {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "ChannelId1", "Foreground notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

//    @Override
//    public void onDestroy() {
//        stopForeground(true);
//        stopSelf();
//        super.onDestroy();
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }

    ComfortInterface.Stub stubObject = new ComfortInterface.Stub() {

        @Override
        public boolean AcPressed(boolean acvalue) throws RemoteException {

              SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sh.edit();
              editor.putBoolean("ac", acvalue);
              editor.apply();
              Boolean ac = sh.getBoolean("ac", Boolean.parseBoolean(""));
              return ac;

        }

        @Override
        public boolean MaxPressed(boolean maxvalue) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("maxac", maxvalue);
          //  editor.putInt("fanspeed",7);
           // editor.putInt("temp",10);
            editor.apply();
            //List<object>
            Boolean max = sh.getBoolean("maxac", Boolean.parseBoolean(""));

            return max;



        }
        public    List<String> getmaxList()throws  RemoteException{
            List<String> maxac=new ArrayList<String>();
        SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        String temp=String.valueOf(sh.getInt("temp",0));
        String fan=String.valueOf(sh.getInt("fanspeed",0));
        maxac.add(temp);
        maxac.add(fan);
        Log.i("MAXLIST"," "+temp);

        return maxac;

         //  return MainActivity.maxList();
//
      }


        @Override
        public boolean PowerPressed(boolean powervalue) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("power", powervalue);
            editor.apply();
            Log.i("PRSENTER"," "+powervalue);

            Boolean power = sh.getBoolean("power", Boolean.parseBoolean(""));
            return power;
        }

        @Override
        public boolean AutoValue(boolean autovalue) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("auto", autovalue);

            editor.apply();
            Boolean auto = sh.getBoolean("auto", Boolean.parseBoolean(""));
            return auto;

        }
        public    List<String> getautoList()throws  RemoteException{
            List<String> auto=new ArrayList<String>();
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String temp=String.valueOf(sh.getInt("temp",0));
            String fan=String.valueOf(sh.getInt("fanspeed",0));
            auto.add(temp);
            auto.add(fan);
            Log.i("MAXLIST"," "+temp);

            return auto;

            //  return MainActivity.maxList();
//
        }


        @Override
        public boolean DefrostValue(boolean value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("defrost", value);

            editor.apply();
            Boolean defrost = sh.getBoolean("defrost", Boolean.parseBoolean(""));
            return defrost;
        }

        public    List<String> getdefrost()throws  RemoteException{
            List<String> defrost=new ArrayList<String>();
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String temp=String.valueOf(sh.getInt("temp",0));
            String fan=String.valueOf(sh.getInt("fanspeed",0));
            defrost.add(temp);
            defrost.add(fan);
            Log.i("MAXLIST"," "+temp);

            return defrost;

            //  return MainActivity.maxList();
//
        }


        @Override
        public boolean RearValue(boolean value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("rear", value);

            editor.apply();
            Boolean rear = sh.getBoolean("rear", Boolean.parseBoolean(""));
            return rear;
        }

        @Override
        public void TempValue(int value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt("temp", value);
            editor.apply();
            Integer temp=sh.getInt("temp",0);
            Log.i("TEMPERATURE"," "+temp);

        }

        @Override
        public void SpeedValue(int value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt("fanspeed", value);
            editor.apply();
            Integer fan=sh.getInt("fanspeed",0);
            Log.i("FAN"," "+fan);

        }

//        @Override
//        public boolean MaxPressed(boolean maxvalue,int fanspeed,int temp) throws RemoteException {
//            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sh.edit();
//            editor.putBoolean("maxac", maxvalue);
//            editor.putInt("fanspeed",fanspeed);
//            editor.putInt("temp",temp);
//
//
//            editor.apply();
//            //List<object>
//            Boolean max = sh.getBoolean("maxac", Boolean.parseBoolean(""));
//            int fan=sh.getInt("fanspeed",0);
//            int temp1=sh.getInt("temp",0);
//
//            return max;
//
//
//
//        }


    };

}