package com.example.quize_app_org;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SavedReslut extends AppCompatActivity {


    private TextView scoreData, timeData;
    private Button backToGameBTN;

    Integer score;
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_reslut);

        loadView();
        loadDataToView();
        backtoMainPage();


    }

    private void backtoMainPage() {
        backToGameBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SavedReslut.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadDataToView() {
        score = getIntent().getIntExtra("step_count",0);
        time = getIntent().getStringExtra("time");


        scoreData.setText(score.toString());
        timeData.setText(time);
    }
    private void loadView(){
        scoreData = findViewById(R.id.saved_score);
        timeData = findViewById(R.id.saved_time);
        backToGameBTN = findViewById(R.id.backToGameBTN);
    }


}
