package com.example.timer.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.timer.Database.Database;
import com.example.timer.Models.ItemModel;
import com.example.timer.R;
import com.example.timer.Viewmodels.CreateViewModel;

import codes.side.andcolorpicker.hsl.HSLColorPickerSeekBar;
import codes.side.andcolorpicker.model.IntegerHSLColor;

public class CreateActivity extends AppCompatActivity {
    CreateViewModel createViewModel;

    HSLColorPickerSeekBar color;
    EditText TimerName;
    ItemModel item;
    ImageButton PrepareInc;
    ImageButton PrepareDec;
    ImageButton WorkInc;
    ImageButton WorkDec;
    ImageButton RestInc;
    ImageButton RestDec;
    ImageButton CycleInc;
    ImageButton CycleDec;
    ImageButton SetsInc;
    ImageButton SetsDec;
    Button ConfirmButton;
    Button CancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        createViewModel = ViewModelProviders.of(this).get(CreateViewModel.class);
        LoadViews();
        LoadData();
    }

    private void LoadData() {
        item = (ItemModel) getIntent().getSerializableExtra("item");
        if (item != null) {
            createViewModel.setCycle(item.Cycle);
            createViewModel.setPrepare(item.PrepareTime);
            createViewModel.setRest(item.RestTime);
            createViewModel.setSets(item.Sets);
            createViewModel.setWork(item.WorkTime);
            TimerName.setText(item.ItemName);
            color.setPickedColor(convertToIntegerHSLColor(item.Color));
        }
    }

    private void LoadViews() {
        TimerName = findViewById(R.id.item_name);
        color = findViewById(R.id.color_picker);

        //incs
        PrepareInc = findViewById(R.id.prepare_inc);
        PrepareInc.setOnClickListener(i -> createViewModel.PrepareInc());
        WorkInc = findViewById(R.id.work_inc);
        WorkInc.setOnClickListener(i -> createViewModel.WorkInc());
        RestInc = findViewById(R.id.rest_inc);
        RestInc.setOnClickListener(i -> createViewModel.RestInc());
        SetsInc = findViewById(R.id.sets_inc);
        SetsInc.setOnClickListener(i -> createViewModel.SetsInc());
        CycleInc = findViewById(R.id.cycle_inc);
        CycleInc.setOnClickListener(i -> createViewModel.CycleInc());
        //decs
        PrepareDec = findViewById(R.id.prepare_dec);
        PrepareDec.setOnClickListener(i -> createViewModel.PrepareDec());
        WorkDec = findViewById(R.id.work_dec);
        WorkDec.setOnClickListener(i -> createViewModel.WorkDec());
        RestDec = findViewById(R.id.rest_dec);
        RestDec.setOnClickListener(i -> createViewModel.RestDec());
        SetsDec = findViewById(R.id.sets_dec);
        SetsDec.setOnClickListener(i -> createViewModel.SetsDec());
        CycleDec = findViewById(R.id.cycle_dec);
        CycleDec.setOnClickListener(i -> createViewModel.CycleDec());
        //observers
        createViewModel.getPrepare().observe(this, v -> ((TextView) findViewById(R.id.prepare_value)).setText(v.toString()));
        createViewModel.getWork().observe(this, v -> ((TextView) findViewById(R.id.work_value)).setText(v.toString()));
        createViewModel.getRest().observe(this, v -> ((TextView) findViewById(R.id.rest_value)).setText(v.toString()));
        createViewModel.getSets().observe(this, v -> ((TextView) findViewById(R.id.sets_value)).setText(v.toString()));
        createViewModel.getCycle().observe(this, v -> ((TextView) findViewById(R.id.cycle_value)).setText(v.toString()));
        ConfirmButton = findViewById(R.id.confirm_button);
        ConfirmButton.setOnClickListener(this::Confirm);
        CancelButton = findViewById(R.id.cancel_button);
        CancelButton.setOnClickListener(v -> {
            finish();
        });
    }


    private IntegerHSLColor convertToIntegerHSLColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        IntegerHSLColor integerHSLColor = new IntegerHSLColor();
        integerHSLColor.setFloatH(hsv[0]);
        integerHSLColor.setFloatL(hsv[1]);
        integerHSLColor.setFloatS(hsv[2]);
        return integerHSLColor;
    }

    private void Confirm(View view) {
        IntegerHSLColor ii = color.getPickedColor();
        ItemModel timer = new ItemModel();
        timer.ItemName = TimerName.getText().toString();
        timer.PrepareTime = createViewModel.getPrepare().getValue();
        timer.WorkTime = createViewModel.getWork().getValue();
        timer.RestTime = createViewModel.getRest().getValue();
        timer.Sets = createViewModel.getSets().getValue();
        timer.Cycle = createViewModel.getCycle().getValue();
        timer.Color = Color.HSVToColor(new float[]{ii.getFloatH(), ii.getFloatL(), ii.getFloatS()});
        if (item != null) {
            timer.id = item.id;
            Database.getInstance(view.getContext()).Dao().Update(timer);
        } else {
            Database.getInstance(view.getContext()).Dao().Insert(timer);
        }
        finish();
    }
}