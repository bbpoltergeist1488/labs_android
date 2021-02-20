package com.example.ppolab2.Activities;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ppolab2.Adapters.TrainingAdapter;
import com.example.ppolab2.R;
import com.example.ppolab2.Services.TimerService;
import com.example.ppolab2.SqlDatabase.SQLiteHelper;
import com.example.ppolab2.Timer;

import java.util.ArrayList;
import java.util.List;

public class TimerActivity extends AppCompatActivity {

    private TextView trainingNameTV, trainingTimeTV;
    private int timer_id, soundFile;
    private long trainingTimeLeft;
    private int trainingNumber = 0;
    private SoundPool endOfTrainingSound;
    private int amountOfTrainings;
    private Boolean pause = false, started = false, startService = false, isBound = false;
    private Timer timer;
    private CountDownTimer countDownTimer;
    private SQLiteHelper sqLiteHelper;
    private ArrayList<Integer> timeList = new ArrayList<>();
    private List<String> namesList = new ArrayList<>();
    private TrainingAdapter trainingAdapter;
    private ListView trainingLv;
    private Intent serviceIntent;
    private ServiceConnection serviceConnection;
    private Button pauseBtn, prevTrainingBtn, nextTrainingBtn, startBtn;
    private TimerService timerService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        endOfTrainingSound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundFile = endOfTrainingSound.load(this, R.raw.sound, 1);
        setContentView(R.layout.timer_activity);
        Bundle bundle = getIntent().getExtras();
        trainingNameTV = findViewById(R.id.trainingNameTV);
        trainingTimeTV = findViewById(R.id.trainingTimeTV);
        trainingLv = findViewById(R.id.trainingLv);
        pauseBtn = findViewById(R.id.pauseBtn);
        prevTrainingBtn = findViewById(R.id.prevTrainingBtn);
        nextTrainingBtn = findViewById(R.id.nextTrainingBtn);
        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                started = true;
                startBtn.setVisibility(View.GONE);
                nextTrainingBtn.setVisibility(View.VISIBLE);
                prevTrainingBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.VISIBLE);
                startService = true;
                updateTimer();
            }
        });
        sqLiteHelper = new SQLiteHelper(getApplicationContext());
        if (bundle != null) {
            timer_id = bundle.getInt("timer_id");
            sqLiteHelper.open();
            timer = sqLiteHelper.getOneItem(timer_id);
            sqLiteHelper.close();
        }
        timeList.add(timer.warmup);
        namesList.add(getString(R.string.warm_up_time));
        for (int i = 0; i < timer.repeat; i++) {
            for (int j = 0; j < timer.cycle; j++) {
                timeList.add(timer.workout);
                timeList.add(timer.rest);
                namesList.add(getString(R.string.training_time));
                namesList.add(getString(R.string.resting_time));
                amountOfTrainings++;
                amountOfTrainings++;
            }
            timeList.add(timer.cooldown);
            namesList.add(getString(R.string.rest_after_cycle));
            amountOfTrainings++;
        }
        trainingAdapter = new TrainingAdapter(this, R.layout.training_example, namesList);
        trainingLv.setAdapter(trainingAdapter);
        if (!started) {
            pauseBtn.setVisibility(View.GONE);
            nextTrainingBtn.setVisibility(View.GONE);
            prevTrainingBtn.setVisibility(View.GONE);
        } else {
            startBtn.setVisibility(View.GONE);
            isBound = true;
        }
        prevTrainingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trainingNumber > 0) {
                    trainingNumber--;
                    updateTimer();
                }
            }
        });
        nextTrainingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trainingNumber < amountOfTrainings) {
                    trainingNumber++;
                    trainingTimeTV.setText(String.valueOf((int) (timeList.get(trainingNumber))));
                    updateTimer();

                }
            }
        });
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pause) {
                    pause = false;
                    pauseBtn.setText(getString(R.string.pause));
                    createNewTimer();
                } else {
                    pause = true;
                    pauseBtn.setText(getString(R.string.unpause));
                    pauseTimer();
                }
            }
        });
        serviceIntent = new Intent(this, TimerService.class);
        serviceIntent.putExtra("timeList", timeList);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder binder) {
                timerService = ((TimerService.TrainingBinder) binder).getService();
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound = false;
            }
        };
    }

    public void createNewTimer() {
        countDownTimer = new CountDownTimer(trainingTimeLeft, 1000) {
            @Override
            public void onTick(long l) {
                trainingTimeLeft = l;
                trainingTimeTV.setText(String.valueOf((int) (l / 1000)));
            }

            @Override
            public void onFinish() {
                endOfTrainingSound.play(soundFile, 1, 1, 0, 0, 1);
                if (trainingNumber < amountOfTrainings) {
                    trainingNumber++;
                    updateTimer();
                } else {
                    trainingNameTV.setText(getString(R.string.training_ended));
                    trainingTimeTV.setText("0");
                }
            }
        }.start();
    }


    @SuppressLint("SetTextI18n")
    public void updateTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        trainingTimeLeft = timeList.get(trainingNumber) * 1000;
        trainingNameTV.setText(trainingNumber + 1 + ". " + namesList.get(trainingNumber));
        trainingLv.setSelection(trainingNumber);
        if (!pause) {
            createNewTimer();
        }
    }

    public void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void setNewTraining(int number) {
        if (started) {
            trainingNumber = number;

            updateTimer();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pauseTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("pause", pause);
        outState.putBoolean("started", started);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pause = savedInstanceState.getBoolean("pause");
        started = savedInstanceState.getBoolean("started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if ((!isBound) && (!pause) && started) {
            serviceIntent.putExtra("trainingNumber", trainingNumber);
            serviceIntent.putExtra("trainingTimeLeft", trainingTimeLeft);
            startService(serviceIntent);
            bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
            isBound = true;
        }
        // Toast.makeText(TimerActivity.this, "OnPause", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        if (isBound) {
            trainingNumber = timerService.getTrainingNumber();
            trainingTimeLeft = timerService.getTrainingTimeLeft();
            unbindService(serviceConnection);
            stopService(serviceIntent);
            isBound = false;
        }

        if (started) {
            if (pause) {
                pauseBtn.setText(getString(R.string.unpause));
            } else {
                pauseBtn.setText(getString(R.string.pause));
                createNewTimer();
            }

            trainingTimeTV.setText(String.valueOf((int) (trainingTimeLeft / 1000)));
            trainingNameTV.setText(trainingNumber + 1 + ". " + namesList.get(trainingNumber));

            trainingLv.setSelection(trainingNumber);
            if (trainingNumber == amountOfTrainings) {
                trainingNameTV.setText(getString(R.string.training_ended));
                trainingTimeTV.setText("0");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {
            unbindService(serviceConnection);
            stopService(serviceIntent);
            isBound = false;
        }
    }

}
