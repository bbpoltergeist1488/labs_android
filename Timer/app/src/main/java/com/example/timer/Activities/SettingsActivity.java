package com.example.timer.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.timer.Database.Database;
import com.example.timer.R;

import java.util.Locale;

public class SettingsActivity extends PreferenceActivity {
    public static boolean changed;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    SharedPreferences sharedPreferences;
    Preference reset;

    public static void SetNightTheme(Context context) {
        if (PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("night", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            context.setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static void SetFontSize(Context context) {

        Configuration configuration = context.getResources().getConfiguration();
        configuration.fontScale =
                Float.parseFloat(String.valueOf(
                        PreferenceManager.getDefaultSharedPreferences(context)
                                .getString("font_size", "1")));

        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        context.getResources().updateConfiguration(configuration, metrics);

    }

    public static void SetLanguage(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = sp.getString("lang", "ru");

        Locale.setDefault(new Locale(lang));
        Configuration configuration = new Configuration();
        configuration.locale = new Locale(lang);
        context.getResources().updateConfiguration(configuration, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SetNightTheme(this);
        SetLanguage(this);
        SetFontSize(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean("night", false)) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }

        listener = this::onSharedPreferenceChanged;
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        reset = findPreference(getString(R.string.reset_name));
        reset.setOnPreferenceClickListener(this::onResetClick);

    }

    private void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key == null) return;
        if ("lang".equals(key)) {
            SetLanguage(this);
            changed = true;
        }
        if ("night".equals(key)) {
            SetNightTheme(this);
            changed = true;
        }
        if ("font_size".equals(key)) {
            SetFontSize(this);
            changed = true;
        }
        if (changed) {
            recreate();
        }
    }

    private boolean onResetClick(Preference preference) {
        Database.getInstance(this).Dao().Clear();
        sharedPreferences.edit().clear().commit();
        finish();
        return true;
    }
}
