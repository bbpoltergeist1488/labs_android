package com.example.timer.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.timer.Activities.SettingsActivity.SetFontSize;
import static com.example.timer.Activities.SettingsActivity.SetLanguage;
import static com.example.timer.Activities.SettingsActivity.SetNightTheme;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        SetNightTheme(this);
        SetFontSize(this);
        SetLanguage(this);
        finish();
    }
}