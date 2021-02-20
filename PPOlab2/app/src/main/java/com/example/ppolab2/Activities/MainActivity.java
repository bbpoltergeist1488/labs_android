package com.example.ppolab2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.ppolab2.Adapters.SpecAdapter;
import com.example.ppolab2.R;
import com.example.ppolab2.SqlDatabase.SQLiteHelper;
import com.example.ppolab2.Timer;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final Runnable r = new Runnable() {
            public void run() {
                Intent intent = new Intent(MainActivity.this, ActivityWithTimers.class);
                startActivity(intent);
                finish();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(r, 3000);
        try{
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this) ;
        if (prefs.getBoolean(getString(R.string.darktheme_preference), false))
        {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        }
        changeLanguage(prefs.getString(getString(R.string.language_preference), "Русский язык"));
        changeFontSize(prefs.getString(getString(R.string.font_preference), "1"));}
        catch (Exception ex){

        }

    }
    public void changeLanguage(String language){
        switch (language)
        {
            case "Русский язык":
                setLocale("ru");
                break;
            case "English":
                setLocale("en");
                break;
        }
    }
    public void setLocale(String language)
    {
        Locale myLocale = new Locale(language);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.locale = myLocale;
        resources.updateConfiguration(config, null);
    }

    public void changeFontSize(String newValue){
        Configuration configuration = new Configuration();
        configuration.fontScale = Float.parseFloat(newValue);
        this.getResources().updateConfiguration(configuration, null);
    }
}