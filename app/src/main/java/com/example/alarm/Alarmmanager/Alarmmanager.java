package com.example.alarm.Alarmmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.alarm.API.api;
import com.example.alarm.R;
import com.example.alarm.laststepforalarm.laststep;
import com.example.alarm.laststepforalarm.laststepforalarm;
import com.example.alarm.login.login;

import java.io.BufferedReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.alarm.getalarm.NOTIFICATION_CHANNEL_ID;
import static com.example.alarm.getalarm.default_notification_channel_id;

public class Alarmmanager extends BroadcastReceiver {

    api jsonapi;
    BufferedReader oku;
    String readd = null;
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    //@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        login(context,intent);
    }

    public void login(final Context base,final Intent values) {
        Bundle bundless = values.getExtras();

        Retrofit retro = new Retrofit.Builder().baseUrl("http://18.197.146.163/api/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonapi = retro.create(api.class);
        Call<login> call2 = jsonapi.loginsystem(bundless.get("namess").toString(), bundless.get("passs").toString());
        call2.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                if (!response.isSuccessful()) {
                    return;
                } else {
                    login posts = response.body();
                    alarmlisten(posts.getToken(),base,values);
                }
            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {
            }
        });
    }

    public void alarmlisten(String token,final Context base,final Intent intent) {

        String obj = "{\"facility_id\":6}";
        Retrofit retro = new Retrofit.Builder().baseUrl("http://18.197.146.163/api/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonapi = retro.create(api.class);
        Call<laststep> call2 = jsonapi.getdata(token,obj);
        call2.enqueue(new Callback<laststep>() {
            @Override
            public void onResponse(Call<laststep> call, Response<laststep> response) {
                if (!response.isSuccessful()) {
                    //Toast.makeText(base,response.toString(),Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    List<laststepforalarm> data = response.body().getData();
                    Toast.makeText(base, response.body().getLastPage().toString(), Toast.LENGTH_LONG).show();
                    for (laststepforalarm get : data) {
                       // Toast.makeText(base, get.getValue()+"sdfsdgadgg", Toast.LENGTH_LONG).show();
                        testalarm(get.getSocketValue().toString(),base,intent,getNotification(get.getDeviceTitleName(),get.getSocketValue().toString(),base));
                        return;
                    }
                    //return;
                }
            }

            @Override
            public void onFailure(Call<laststep> call, Throwable t) {
                Toast.makeText(base,t.getMessage().toString(),Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    private Notification getNotification(String devicename,String deger,Context base) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(base, default_notification_channel_id);
        builder.setContentTitle("Tesisinizde Alarm Var");
        builder.setContentText(devicename+ "\n"+deger);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    public void testalarm(String value, Context base,Intent intent,Notification notification){
        for (int i = 0; i < 3; i++) {

            if (Integer.parseInt(value) > 10) {
                Toast.makeText(base, "Alarm Çalıyor! Artık Uyan!", Toast.LENGTH_LONG).show();

                Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                if (alarmUri == null) {
                    alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }
                Ringtone ringtone = RingtoneManager.getRingtone(base, alarmUri);
                ringtone.play();

                NotificationManager notificationManager = (NotificationManager) base.getSystemService(Context. NOTIFICATION_SERVICE ) ;
                //Notification notification = intent.getParcelableExtra(NOTIFICATION) ;
                if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
                    int importance = NotificationManager. IMPORTANCE_HIGH ;
                    NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
                    assert notificationManager != null;
                    notificationManager.createNotificationChannel(notificationChannel) ;
                }
                int id = intent.getIntExtra(NOTIFICATION_ID , 0 ) ;
                assert notificationManager != null;
                notificationManager.notify(id , notification) ;
                return;
            }
        }
    }
}