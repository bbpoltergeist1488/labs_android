package com.example.timer.Activities;

import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timer.Adapters.RoundAdapter;
import com.example.timer.Models.ItemModel;
import com.example.timer.Models.RoundModel;
import com.example.timer.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlayActivity extends AppCompatActivity {

    ArrayList<RoundModel> rounds = new ArrayList<>();
    RecyclerView roundList;
    ImageButton pause;
    ImageButton skip;
    TextView roundTime;
    TextView roundName;

    ItemModel item;
    int tickSound;
    int roundSound;
    SoundPool soundPool;
    RoundAdapter adapter = new RoundAdapter();
    Boolean paused = false;
    Boolean skiped = false;
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadViews();
        LoadSound();
        LoadRounds();

        player.execute(rounds.toArray(new RoundModel[0]));
    }

    private void LoadRounds() {
        item = (ItemModel) getIntent().getSerializableExtra("item");

        if (item.PrepareTime > 0) {
            rounds.add(new RoundModel(10, getString(R.string.prepare)));
        }
        for (int j = 0; j < item.Sets; j++) {
            for (int i = 0; i < item.Cycle; i++) {
                rounds.add(new RoundModel(item.WorkTime, getString(R.string.work)));
                rounds.add(new RoundModel(item.RestTime, getString(R.string.rest)));
            }
            if (item.Sets > 1 && j + 1 != item.Sets)
                rounds.add(new RoundModel(item.PrepareTime, getString(R.string.prepare)));
        }
        adapter.AddAll(rounds);
    }


    void LoadViews() {
        setContentView(R.layout.activity_play);
        roundList = findViewById(R.id.round_list);
        roundTime = findViewById(R.id.round_time);
        roundName = findViewById(R.id.round_name);
        pause = findViewById(R.id.pause_button);

        pause.setOnClickListener(v -> {
            paused = !paused;
        });

        skip = findViewById(R.id.skip_button);
        skip.setOnClickListener(v -> {
            skiped = !skiped;
        });

        roundList = findViewById(R.id.round_list);
        roundList.setLayoutManager(new LinearLayoutManager(this));
        roundList.setAdapter(adapter);
    }

    private void LoadSound() {
        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        tickSound = soundPool.load(this, R.raw.short_sound, 1);
        roundSound = soundPool.load(this, R.raw.long_soung, 1);
    }

    @Override
    protected void onDestroy() {
        player.cancel(true);
        super.onDestroy();
    }

    class Player extends AsyncTask<RoundModel, RoundModel, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            soundPool.release();
            finish();
        }

        @Override
        protected Void doInBackground(RoundModel... array) {
            try {
                for (int roundCounter = 0; roundCounter < array.length; roundCounter++) {
                    RoundModel round = array[roundCounter];
                    int abc = round.roundTime;
                    runOnUiThread(() -> adapter.DeleteByIndex(0));
                    for (int i = round.roundTime; i > 0; i--) {
                        publishProgress(new RoundModel(--abc, round.roundName));

                        TimeUnit.SECONDS.sleep(1);
                        if (paused) {
                            synchronized (this) {
                                wait();
                            }
                        }
                        if (skiped) {
                            skiped = false;
                            break;
                        }
                        if (isCancelled())
                            return null;
                    }

                }
            } catch (Exception ignored) {
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(RoundModel... values) {
            super.onProgressUpdate(values);
            roundTime.setText(String.valueOf(values[0].roundTime));
            roundName.setText(values[0].roundName);
            switch (values[0].roundTime) {
                case 3:
                case 2:
                case 1:
                    soundPool.play(tickSound, 1, 1, 0, 0, 1);
                    break;
                case 0:
                    soundPool.play(roundSound, 1, 1, 0, 0, 1);
                    break;
            }
        }
    }

}
