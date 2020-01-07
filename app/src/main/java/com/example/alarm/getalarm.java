package com.example.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarm.API.api;
import com.example.alarm.Alarmmanager.Alarmmanager;
import com.example.alarm.getalarms.getalarms;
import com.example.alarm.getdata.data;
import com.example.alarm.getdata.datadatum;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getalarm extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String token;
    api jsonapi;
    Handler wait = new Handler();

    Button save, rast;
    EditText alarm;
    TextView value;

    String name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getalarm);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        save = findViewById(R.id.save);
        rast = findViewById(R.id.rast);
        alarm = findViewById(R.id.alarm);
        value = findViewById(R.id.value);

        token = getIntent().getExtras().getString("token");
        name = getIntent().getExtras().getString("name");
        pass = getIntent().getExtras().getString("pass");
        getdevicesforalarm();
        wait.postDelayed(new Runnable() {
            @Override
            public void run() {
                göster();
            }
        }, 300);
        unload();
        //setAlarm();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setalarm();
            }
        });

    }

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static final String default_notification_channel_id = "default";

    public void setalarm() {
        Bundle simple_bundle=new Bundle();
        simple_bundle.putString("namess",name);
        simple_bundle.putString("passs",pass);
        Intent notificationIntent = new Intent(this, Alarmmanager.class);
        notificationIntent.putExtras(simple_bundle);

        notificationIntent.putExtra(Alarmmanager.NOTIFICATION_ID, 1);
        //notificationIntent.putExtra(Alarmmanager.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //long futureInMillis = SystemClock.elapsedRealtime() + 30000;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        //alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis,futureInMillis, pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

    }

    ProgressDialog progressDialog;

    public void loading() {
        progressDialog = new ProgressDialog(getalarm.this);
        progressDialog.setMessage("Lütfen bekleyiniz...");
        progressDialog.show();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void unload() {
        progressDialog.hide();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    //close alarm
    /*public void CancelAlarm(Context context) {
        // Kurulu son alarmı ipta et
        Intent intent = new Intent(context, Alarmmanager.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }*/

    ArrayList<String> mek = new ArrayList<String>();
    ArrayList<String> ids = new ArrayList<String>();

    public void getdevicesforalarm() {
        Retrofit retro = new Retrofit.Builder().baseUrl("http://18.197.146.163/api/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonapi = retro.create(api.class);

        loading();

        Call<data> call2 = jsonapi.getdevices(token);
        call2.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    List<datadatum> dat = response.body().getData();
                    for (datadatum get : dat) {
                        ids.add(get.getId().toString());
                        mek.add(get.getTitleName());
                    }
                    //unload();
                }
            }

            @Override
            public void onFailure(Call<data> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                unload();
            }
        });
    }

    private void göster() {
        NavigationView ekran = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout tas = (DrawerLayout) findViewById(R.id.drawer_layout);
        Menu menue = ekran.getMenu();
        for (int i = 0; i < mek.size(); i++) {

            MenuItem item = menue.add(mek.get(i));
            unload();
            final int count = i;
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    getalarmsfordevice(ids.get(count));
                    // Toast.makeText(getBaseContext(), "THIS IS A TEST" + count, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
        tas.closeDrawers();
    }

    String device;
    int device_id1;
    int alarmvalue;

    public void getalarmsfordevice(final String device_id) {
        Retrofit retro = new Retrofit.Builder().baseUrl("http://18.197.146.163/api/devices/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonapi = retro.create(api.class);

        loading();

        Call<getalarms[]> call2 = jsonapi.getalarmss(Integer.parseInt(device_id), "false", token);
        call2.enqueue(new Callback<getalarms[]>() {
            @Override
            public void onResponse(Call<getalarms[]> call, Response<getalarms[]> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Bağlantı Hatası", Toast.LENGTH_SHORT).show();
                    unload();
                    return;
                } else {

                    getalarms[] alarm = response.body();
                    for (getalarms gets : alarm) {
                        device = gets.getDeviceTitleName();
                        device_id1 = gets.getDeviceId();
                        alarmvalue = gets.getValue();
                    }
                    unload();
                    setTitle(device);
                    value.setText(String.valueOf(alarmvalue));
                }
            }

            @Override
            public void onFailure(Call<getalarms[]> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                unload();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.getalarm, menu);
        return true;
    }

    //sağ üst popup seçenği
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

//Alarm void
    /*private void openPickerDialog(boolean is24hour) {

        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(
                getalarm.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24hour);
        timePickerDialog.setTitle("Alarm Ayarla");

        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {

                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }
    };*/
