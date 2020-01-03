package com.example.alarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarm.API.api;
import com.example.alarm.getdata.data;
import com.example.alarm.getdata.datadatum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dashboard extends AppCompatActivity {

    Button save, rast;
    EditText alarm;
    TextView value;
    ProgressBar bar;
    String token;
    private api jsonapi;
    String obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        save = findViewById(R.id.save);
        alarm = findViewById(R.id.alarm);
        value = findViewById(R.id.value);
        bar = findViewById(R.id.bar);
        rast = findViewById(R.id.rast);
        bar.setVisibility(View.GONE);

        token = getIntent().getExtras().getString("token");
        read();

        Retrofit retro = new Retrofit.Builder().baseUrl("http://18.197.146.163/api/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonapi = retro.create(api.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarm.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Alarm Değeri Giriniz", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        File file = new File("data/data/com.example.alarm/alarm.txt");
                        FileWriter fileWriter = new FileWriter(file, false);
                        BufferedWriter bWriter = new BufferedWriter(fileWriter);
                        bWriter.write(alarm.getText().toString());
                        bWriter.close();
                        Toast.makeText(getBaseContext(), "Alarm Kaydedildi", Toast.LENGTH_SHORT).show();
                        read();
                    } catch (Exception io) {
                        Toast.makeText(getBaseContext(), "Alarm Kaydedilirken Hata Oluştu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*rast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date timenow = new Date();
                SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
                String strDate= df.format(timenow);

                //sendobj obj = new sendobj("2019-12-27 00:00:00","2019-12-27 23:59:59",28);
                obj = "{\"start_date\":\""+df+" 00:00:00\"," +
                        "\"end_date\":\""+df+" 23:59:59\"," +
                        "\"device_id\":28}";

                bar.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                Call<data> call2 = jsonapi.getdata("false", "gsm_date", "desc", token, obj);
                call2.enqueue(new Callback<data>() {
                    @Override
                    public void onResponse(Call<data> call, Response<data> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_SHORT).show();
                            bar.setVisibility(View.GONE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            return;
                        } else {
                            /*List<datadatum> dat = response.body().getData();
                            for (datadatum gettir : dat) {
                                //value.setText(gettir.getValue());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<data> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                });
            }
        });*/
    }

    BufferedReader oku;
    String readd = null;

    public void read() {
        File file = new File("data/data/com.example.alarm/alarm.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Dosya Oluşturulamadı !", Toast.LENGTH_SHORT).show();
            }
        } else {
            try {
                oku = new BufferedReader(new FileReader("data/data/com.example.alarm/alarm.txt"));
                while ((readd = oku.readLine()) != null) {
                    value.setText(readd);
                }
                if (value.getText().toString().trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Alarm Kaydı Yok", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Alarm Kaydı Okunamadı", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
