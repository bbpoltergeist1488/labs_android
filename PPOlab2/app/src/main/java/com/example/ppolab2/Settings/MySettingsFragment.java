package com.example.ppolab2.Settings;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.ppolab2.Activities.ActivityWithTimers;
import com.example.ppolab2.Activities.MainActivity;
import com.example.ppolab2.R;
import com.example.ppolab2.SqlDatabase.DeleteHelper;
import com.example.ppolab2.SqlDatabase.SQLiteHelper;
import com.example.ppolab2.Timer;

import static android.content.Context.ACTIVITY_SERVICE;

import java.util.List;
import java.util.Locale;

public class MySettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        Preference theme = findPreference(getString(R.string.darktheme_preference));
        theme.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                SharedPreferences prefs = PreferenceManager
                        .getDefaultSharedPreferences(getContext());
                if ((boolean) newValue) {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_NO);
                }
                try{
                changeLanguage(prefs.getString(getString(R.string.language_preference), "Русский язык"));
                changeFontSize(prefs.getString(getString(R.string.font_preference), "1"));}
                catch (Exception ex){

                }
                return true;
            }
        });

        Preference lang = findPreference(getString(R.string.language_preference));
        lang.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                changeLanguage((String) newValue);
                return true;
            }
        });
        Preference font = findPreference(getString(R.string.font_preference));
        font.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                changeFontSize((String) newValue);
                return true;
            }
        });

        Preference delete_data = findPreference(getString(R.string.delete_data_preference));
        delete_data.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                  SharedPreferences prefs = PreferenceManager
                          .getDefaultSharedPreferences(getContext());
                  prefs.edit().clear().apply();
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
                   sqLiteHelper.open();
                sqLiteHelper.clearDatabase();
                sqLiteHelper.close();
                setDafaultSettings();
                getActivity().recreate();
                return true;

            }
        });

    }

    public void changeFontSize(String newValue) {
        Configuration configuration = new Configuration();
        configuration.fontScale = Float.parseFloat(newValue);
        getActivity().getResources().updateConfiguration(configuration, null);
        getActivity().recreate();
    }

   public void setDafaultSettings(){
       AppCompatDelegate.setDefaultNightMode(
               AppCompatDelegate.MODE_NIGHT_NO);
       changeLanguage("Русский язык");
       changeFontSize("1");
   }




    public void changeLanguage(String lang) {
        switch (lang) {
            case "Русский язык":
                setLocale("ru");
                break;
            case "English":
                setLocale("en");
                break;
        }
    }



    public void setLocale(String language) {
        Locale myLocale = new Locale(language);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.locale = myLocale;
        resources.updateConfiguration(config, null);
        getActivity().recreate();
    }

}
