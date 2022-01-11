package com.example.comfortservice;


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
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }

    ComfortInterface.Stub stubObject = new ComfortInterface.Stub() {
        //Methods to store & retrieve the current state of AC Button
        @Override
        public boolean AcPressed(boolean acvalue) throws RemoteException {

            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("ac", acvalue);
            editor.apply();
            Boolean ac = sh.getBoolean("ac", Boolean.parseBoolean(""));
            return ac;

        }
        //Methods to store & retrieve the current state of MAX AC Button
        @Override
        public boolean MaxPressed(boolean maxvalue) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("maxac", maxvalue);
            editor.apply();
            Boolean max = sh.getBoolean("maxac", Boolean.parseBoolean(""));
            return max;
        }
        //Methods to store & retrieve the current value of temperature and fan speed while Max ac is On

        public List<String> getmaxList() throws RemoteException {
            List<String> maxac = new ArrayList<String>();
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String temp = String.valueOf(sh.getInt("temp", 0));
            String fan = String.valueOf(sh.getInt("fanspeed", 0));
            maxac.add(temp);
            maxac.add(fan);
            Log.i("MAXLIST", " " + temp);

            return maxac;


        }

        //Methods to store & retrieve the current state of Power Button
        @Override
        public boolean PowerPressed(boolean powervalue) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("power", powervalue);
            editor.apply();
            Log.i("PRSENTER", " " + powervalue);

            Boolean power = sh.getBoolean("power", Boolean.parseBoolean(""));
            return power;
        }
        //Methods to store & retrieve the current state of Power Button
        @Override
        public boolean AutoValue(boolean autovalue) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("auto", autovalue);

            editor.apply();
            Boolean auto = sh.getBoolean("auto", Boolean.parseBoolean(""));
            return auto;

        }
        //Methods to store & retrieve the current value of temperature and fan speed while Auto button is On

        public List<String> getautoList() throws RemoteException {
            List<String> auto = new ArrayList<String>();
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String temp = String.valueOf(sh.getInt("temp", 0));
            String fan = String.valueOf(sh.getInt("fanspeed", 0));
            auto.add(temp);
            auto.add(fan);
            Log.i("MAXLIST", " " + temp);

            return auto;


        }

        //Methods to store & retrieve the current state of Defrost Button

        @Override
        public boolean DefrostValue(boolean value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("defrost", value);

            editor.apply();
            Boolean defrost = sh.getBoolean("defrost", Boolean.parseBoolean(""));
            return defrost;
        }

        //Methods to store & retrieve the current value of temperature and fan speed while defrost button is On

        public List<String> getdefrost() throws RemoteException {
            List<String> defrost = new ArrayList<String>();
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            String temp = String.valueOf(sh.getInt("temp", 0));
            String fan = String.valueOf(sh.getInt("fanspeed", 0));
            defrost.add(temp);
            defrost.add(fan);
            Log.i("MAXLIST", " " + temp);

            return defrost;


        }
        //Methods to store & retrieve the current state of Rear Button
        @Override
        public boolean RearValue(boolean value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putBoolean("rear", value);

            editor.apply();
            Boolean rear = sh.getBoolean("rear", Boolean.parseBoolean(""));
            return rear;
        }
        //Methods to store & retrieve the current value of temperature
        @Override
        public void TempValue(int value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt("temp", value);
            editor.apply();
            Integer temp = sh.getInt("temp", 0);
            Log.i("TEMPERATURE", " " + temp);

        }
        //Methods to store & retrieve the current value of fan speed
        @Override
        public void SpeedValue(int value) throws RemoteException {
            SharedPreferences sh = getSharedPreferences("COMFORT", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt("fanspeed", value);
            editor.apply();
            Integer fan = sh.getInt("fanspeed", 0);
            Log.i("FAN", " " + fan);

        }


    };

}