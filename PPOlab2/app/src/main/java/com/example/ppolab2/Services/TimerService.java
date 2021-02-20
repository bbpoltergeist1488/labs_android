package com.example.ppolab2.Services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;

import com.example.ppolab2.R;

import java.util.ArrayList;

public class TimerService extends Service {
    TrainingBinder trainingBinder = new TrainingBinder();
    private CountDownTimer countDownTimer;
    private ArrayList<Integer> timeList;
    private int trainingNumber, amountOfTrainings;
    private SoundPool endOfTrainingSound;
    private int soundFile;
    private long trainingTimeLeft;

    public IBinder onBind(Intent intent) {
        return trainingBinder;
    }

    public void onCreate() {
        super.onCreate();
        endOfTrainingSound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundFile = endOfTrainingSound.load(this, R.raw.sound, 1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        trainingTimeLeft = intent.getLongExtra("trainingTimeLeft", 0);
        trainingNumber = intent.getIntExtra("trainingNumber", 0);
        timeList = intent.getIntegerArrayListExtra("timeList");
        amountOfTrainings = timeList.size();
        createNewTimer();
        return super.onStartCommand(intent, flags, startId);
    }

     public void createNewTimer() {
        countDownTimer = new CountDownTimer(trainingTimeLeft, 1000) {
            @Override
            public void onTick(long l) {
                trainingTimeLeft = l;
            }

            @Override
            public void onFinish() {
                endOfTrainingSound.play(soundFile, 1, 1, 0, 0, 1);
                if (trainingNumber < amountOfTrainings) {
                    trainingNumber++;
                    updateTimer();
                }
            }
        }.start();
    }
    public void updateTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        trainingTimeLeft = timeList.get(trainingNumber) * 1000;
        createNewTimer();
    }

    public int getTrainingNumber() {
        return trainingNumber;
    }
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    public long getTrainingTimeLeft() {
        return trainingTimeLeft;
    }

    public class TrainingBinder extends Binder {
        public TimerService getService() {
            return TimerService.this;
        }
    }
}
