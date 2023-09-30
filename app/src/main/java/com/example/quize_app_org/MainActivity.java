package com.example.quize_app_org;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView stepView , timer, pauseWrapper;

    private ImageView  pauseBTn;

    private Button restartBtn;

    private RelativeLayout buttonGroup;
    private ArrayList<String> numbers = new ArrayList<>();

    private CountDownTimer timerCountDown;

    String timeFormat;

    Integer timerCount = 0;

    Integer emptyI = 3;
    Integer emptyJ = 3;
    Integer stepCount = 0;
    private Boolean isPause = false;

    private TextView[][] buttons = new TextView[4][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadView();
        loadNumber();
        loadDataToView();
        loadAction();



    }


    private void loadAction() {
        for (int i = 0; i < 16; i++) {
            buttons[i/4][i%4].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView button = (TextView) view;
                    String tag = button.getTag().toString();
                    Integer i = Integer.parseInt(tag.split(":")[0]);
                    Integer j = Integer.parseInt(tag.split(":")[1]);
                    Log.d("curBtn","Onclick" + button.getTag());

                    int deltaI = Math.abs(emptyI - i);
                    int deltaJ = Math.abs(emptyJ - j);

                    if(deltaI + deltaJ == 1) {
                        buttons[emptyI][emptyJ].setText(buttons[i][j].getText());
                        buttons[emptyI][emptyJ].setVisibility(View.VISIBLE);
                        buttons[i][j].setText("");
                        buttons[i][j].setVisibility(View.INVISIBLE);

                        emptyI = i;
                        emptyJ = j;
                        stepCount++;
                        stepView.setText(stepCount.toString());

                        if(emptyI == 3 && emptyJ == 3) {
                            checkWin();
                        }


                    }

                }
            });
        }

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerCount = 0;
                stepCount = 0;
                loadNumber();
                emptyI = 3;
                emptyJ = 3;
                timerCountDown.cancel();
                loadDataToView();
            }
        });

        pauseBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isPause) {
                   timerCountDown.start();
                   pauseWrapper.setVisibility(View.INVISIBLE);
                   pauseBTn.setImageResource(R.drawable.baseline_pause_24);
                   isPause =false;
               } else {
                   timerCountDown.cancel();
                   pauseWrapper.setVisibility(View.VISIBLE);
                   pauseBTn.setImageResource(R.drawable.baseline_play_arrow_24);
                   isPause =true;
               }
            }
        });
    };


    private void checkWin() {
        for(int i = 0; i < 14; i++) {
             int current = Integer.parseInt(buttons[i/4][i%4].getText().toString());
             int next = Integer.parseInt(buttons[i/4][i%4].getText().toString());

             if(current > next) {
                 return;
             }
        }

        Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        timerCountDown.cancel();
        Intent intent = new Intent(MainActivity.this, WinGamer.class);
        intent.putExtra("STEP" , stepCount);
        intent.putExtra("TIME" , timeFormat);
        startActivity(intent);
        finish();
        
    }
    private void loadDataToView() {

        stepView.setText("0");
        timer.setText("00:00:00");

//
//        timer.scheduleAtFixedRate(task, 1_000, 1_000);
        timerCountDown = new CountDownTimer(1_000_000L, 1_000) {
            @Override
            public void onTick(long l) {
                timerCount++;
                setTime();
            }

            @Override
            public void onFinish() {

            }
        };

        timerCountDown.start();


        for(int i = 0; i < buttonGroup.getChildCount(); i++) {
            buttons[i/4][i%4].setText(numbers.get(i));
            buttons[i/4][i%4].setVisibility(View.VISIBLE);
        }

        buttons[emptyI][emptyJ].setVisibility(View.INVISIBLE);

    }

    private void setTime() {
        timeFormat = converter(timerCount);
        timer.setText(timeFormat);
    }

    private String converter (Integer n) {
        int hour = n / 3600;
        int minute = n % 3600 / 60;
        int second =  n % 60;

        return String.format("%02d:%02d:%02d",hour, minute, second);
    };
    private void loadNumber() {
        numbers.clear();
      for(Integer i = 1; i <= 16; i++) {
          numbers.add(String.valueOf(i));
      }
      Collections.shuffle(numbers);
    }

    private void loadView() {

      stepView = findViewById(R.id.step);
      timer = findViewById(R.id.timer);
      pauseWrapper = findViewById(R.id.pause_content);
      restartBtn = findViewById(R.id.restart_btn);
      buttonGroup = findViewById(R.id.buttonGroup);
      pauseBTn = (ImageView) findViewById(R.id.pause_btn);

        for (int i = 0; i < buttonGroup.getChildCount(); i++) {
            TextView  textButton = (TextView) buttonGroup.getChildAt(i);
            buttons[i/4][i%4] = textButton;
        }


    }


}