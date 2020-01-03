package com.example.alarm.Alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.alarm.API.api;
import com.example.alarm.MainActivity;
import com.example.alarm.getalarm;
import com.example.alarm.getdata.data;
import com.example.alarm.getdata.datadatum;
import com.example.alarm.laststepforalarm.laststepforalarm;
import com.example.alarm.login.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Alarmmanager extends BroadcastReceiver {

    String token;
    api jsonapi;
    BufferedReader oku;
    String readd = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        read();
        login();
        //alarmlisten();
        degerler.add("20");
        degerler.add("30");
        degerler.add("25");

        for (int i = 0; i < 3; i++) {

            if (Integer.parseInt(degerler.get(i))> 10) {
                Toast.makeText(context, "Alarm Çalıyor! Artık Uyan!", Toast.LENGTH_LONG).show();

                Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                if (alarmUri == null) {
                    alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }
                Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
                ringtone.play();
                return;
            }
        }

    }

    public void login(){
        Call<login> call2 = jsonapi.loginsystem(forlogin.get(0), forlogin.get(1));
        call2.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                if (!response.isSuccessful()) {
                    return;
                } else {
                    login posts = response.body();
                    token = posts.getToken();
                }
            }
            @Override
            public void onFailure(Call<login> call, Throwable t) {

            }
        });
    }

    ArrayList<String> forlogin = new ArrayList<String>();

    public void read() {
        try {
            oku = new BufferedReader(new FileReader("data/data/com.example.alarm/token.txt"));
            while ((readd = oku.readLine()) != null) {
                forlogin.add(readd);
            }

        } catch (Exception e) {
            //Toast.makeText(getBaseContext(), "Alarm Kaydı Okunamadı", Toast.LENGTH_SHORT).show();
        }
    }

    ArrayList<String> degerler = new ArrayList<String>();

    public void alarmlisten() {

        String obj = "{\"start_date\":\"2020-01-03 00:00:00\",\"end_date\":\"2020-01-03 23:59:59\",\"device_id\":28}";
        Retrofit retro = new Retrofit.Builder().baseUrl("http://18.197.146.163/api/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonapi = retro.create(api.class);

        Call<laststepforalarm[]> call2 = jsonapi.getdata("false", "gsm_date", "desc", token, obj);
        call2.enqueue(new Callback<laststepforalarm[]>() {
            @Override
            public void onResponse(Call<laststepforalarm[]> call, Response<laststepforalarm[]> response) {
                if (!response.isSuccessful()) {

                    return;
                } else {
                    laststepforalarm[] data = response.body();
                    for (laststepforalarm get : data) {
                        degerler.add(get.getValue());
                    }
                    //return;
                }
            }

            @Override
            public void onFailure(Call<laststepforalarm[]> call, Throwable t) {

            }
        });
    }
}