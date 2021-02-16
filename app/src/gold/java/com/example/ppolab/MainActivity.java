package com.example.ppolab;

import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    DKFragment DKFragment = new DKFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


}
}