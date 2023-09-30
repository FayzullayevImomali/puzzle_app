package com.example.quize_app_org;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinGamer extends AppCompatActivity {


    private TextView displayTime, displayScore;
    private Button backToGameBtn, saveResult;

    String step;
    String time;

    String converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_gamer);

        loadView();
        backToGameAction();
        saveResultAction();

        step = AppCache.getObject().getStep().toString();
        time = AppCache.getObject().getTime();
        converter = getIntent().getStringExtra("CONVERTER");

        Log.d("TAGaaa", "onCreate: "+time);

        displayScore.setText(step.toString());
        displayTime.setText(time);


    }


    private void saveResultAction() {
        saveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AppCache.getObject().saveTime(time);
//                AppCache.getObject().saveStep(step);
                //SharedPreferences file = getSharedPreferences("puzzle_15", Context.MODE_PRIVATE);
//                file.edit().putInt("step_count", step).apply();
                //file.edit().putString("time", time).apply();

                Intent intent = new Intent(WinGamer.this, SavedResult.class);
//                intent.putExtra("step_count", step);
//                intent.putExtra("time", time);
                startActivity(intent);
                finish();

            }
        });

    }


    private void loadView() {

        displayTime = findViewById(R.id.time_display);
        displayScore = findViewById(R.id.step_score);
        backToGameBtn = findViewById(R.id.back_to_game);
        saveResult = findViewById(R.id.save_result);


    }

    private void backToGameAction(){
        backToGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(WinGamer.this, MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });
    };



}