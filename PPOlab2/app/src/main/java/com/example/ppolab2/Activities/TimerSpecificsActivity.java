package com.example.ppolab2.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ppolab2.R;
import com.example.ppolab2.SqlDatabase.SQLiteHelper;
import com.example.ppolab2.Timer;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorShape;

public class TimerSpecificsActivity extends AppCompatActivity implements ColorPickerDialogListener {
    private int timer_id, color;
    Button addTimerbtn, colorPickerbtn;
    EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7;
    private SQLiteHelper sqLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_specifics);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        colorPickerbtn = findViewById(R.id.colorPickerbtn);
        sqLite = new SQLiteHelper(this);
        addTimerbtn = findViewById(R.id.addTimerbtn);
        colorPickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createColorPickerDialog(1);
            }
        });
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            timer_id = bundle.getInt("timer_id");
            Toast.makeText(TimerSpecificsActivity.this, Integer.toString(timer_id), Toast.LENGTH_SHORT).show();
            if (timer_id > 0) {
                sqLite.open();
                Timer timer = sqLite.getOneItem(timer_id);
                sqLite.close();
            editText1.setText(String.valueOf(timer.warmup));
            editText2.setText(String.valueOf(timer.workout));
            editText3.setText(String.valueOf(timer.rest));
            editText4.setText(String.valueOf(timer.cooldown));
            editText5.setText(String.valueOf(timer.cycle));
            editText6.setText(String.valueOf(timer.repeat));
                 editText7.setText(timer.timerName);
                 color = timer.color;
                 if(color!=0)
                colorPickerbtn.setTextColor(color);
                }
            }
            addTimerbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        int warmup = Integer.parseInt(editText1.getText().toString());
                        int workout = Integer.parseInt(editText2.getText().toString());
                        int rest = Integer.parseInt(editText3.getText().toString());
                        int cycle = Integer.parseInt(editText5.getText().toString());
                        int repeat = Integer.parseInt(editText6.getText().toString());
                        int cooldown = Integer.parseInt(editText4.getText().toString());
                        String timerName = editText7.getText().toString();
                        Timer timer = new Timer(timerName, color, warmup, workout, rest, cooldown, repeat, cycle, timer_id);
                        sqLite.open();
                            if(timer_id>0){
                        sqLite.updateAllItems(timer);
                         }
                          else{
                        sqLite.insertOneItem(timer);
                           }
                        sqLite.close();
                        Intent intent = new Intent(TimerSpecificsActivity.this, ActivityWithTimers.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception exception) {
                        Toast.makeText(TimerSpecificsActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private void createColorPickerDialog ( int id){
            ColorPickerDialog.newBuilder()
                    .setColor(Color.RED)
                    .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                    .setAllowCustom(true)
                    .setAllowPresets(true)
                    .setColorShape(ColorShape.SQUARE)
                    .setDialogId(id)
                    .show(this);
        }

        @Override
        public void onColorSelected ( int dialogId, int color){
            this.color = color;
            colorPickerbtn.setTextColor(this.color);
        }

        @Override
        public void onDialogDismissed ( int dialogId){

        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TimerSpecificsActivity.this, ActivityWithTimers.class);
        startActivity(intent);
        finish();
    }
}
