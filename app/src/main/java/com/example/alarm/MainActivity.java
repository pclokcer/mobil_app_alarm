package com.example.alarm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alarm.API.api;
import com.example.alarm.login.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button sendd;
    private api jsonapi;
    private ProgressBar bar;
    EditText idd, passw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idd = findViewById(R.id.idd);
        passw = findViewById(R.id.passww);
        sendd = findViewById(R.id.send);
        bar = findViewById(R.id.barr);
        bar.setVisibility(View.GONE);
        Retrofit retro = new Retrofit.Builder().baseUrl("http://18.197.146.163/api/").addConverterFactory(GsonConverterFactory.create()).build();

        jsonapi = retro.create(api.class);

        sendd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = idd.getText().toString();
                pass = passw.getText().toString();
                CheckLogin baglan = new CheckLogin();
                baglan.execute(new String[]{
                        idd.getText().toString(),
                        passw.getText().toString()
                });
            }
        });

    }

    String name,pass;

    public class CheckLogin extends AsyncTask<String, String, String> {
        String content = null;
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            bar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

        @Override
        protected void onPostExecute(String r) {
            bar.setVisibility(View.GONE);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            /*if (isSuccess) {
                Intent dash = new Intent(MainActivity.this, dashboard.class);
                dash.putExtra("tokenn", content);
                startActivity(dash);
                bar.setVisibility(View.GONE);
            } else {
                bar.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "hatalı şifre veya kullanıcı", Toast.LENGTH_SHORT).show();
                idd.setError("Kullanıcı Adı hatalı");
                passw.setError("Şifre hatalı");
            }*/
        }

        @Override
        protected String doInBackground(String... args) {
            String kul = args[0];
            String sifre = args[1];
            Call<login> call2 = jsonapi.loginsystem(kul, sifre);
            call2.enqueue(new Callback<login>() {
                @Override
                public void onResponse(Call<login> call, Response<login> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getBaseContext(), "Kullanıcı Adı veya Şifre Yanlış", Toast.LENGTH_SHORT).show();
                        isSuccess = false;
                        bar.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        return;
                    } else {
                        login posts = response.body();
                        content = posts.getToken();
                        Intent dash = new Intent(MainActivity.this, getalarm.class);
                        dash.putExtra("token", content);
                        dash.putExtra("name", name);
                        dash.putExtra("pass", pass);
                        startActivity(dash);
                    }
                }
                @Override
                public void onFailure(Call<login> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    bar.setVisibility(View.GONE);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                }
            });
            return content;
        }
    }
}
