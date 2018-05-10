package beadando.game;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.os.Handler;
import android.os.Handler.Callback;

import java.util.concurrent.Delayed;

public class start extends AppCompatActivity {

    static TextView tvContent;
    static Easy uj;
    static int jo;
    static Handler timer;

    static Button btnA;
    static Button btnB;
    static Button btnC;
    static Button btnD;
    static Button btnCorrect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvContent = findViewById(R.id.tv_content);

        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        timer = new Handler();

        String getDifficulty = getIntent().getStringExtra("difficulty");
        uj = new Easy();

        iras(getDifficulty);

        //BUTTONS CLICK EVENTS
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jo == 1){
                    btnA.setBackgroundColor(Color.GREEN);
                    Wait();
                }
                else{
                    btnA.setBackgroundColor(Color.RED);
                    btnA.setText("X");
                    Popup();
                }
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jo == 2){
                    btnB.setBackgroundColor(Color.GREEN);
                    Wait();
                }
                else{
                    btnB.setBackgroundColor(Color.RED);
                    btnB.setText("X");
                    Popup();
                }
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jo == 3){
                    btnA.setBackgroundColor(Color.GREEN);
                    Wait();
                }
                else{
                    btnC.setBackgroundColor(Color.RED);
                    btnC.setText("X");
                    Popup();
                }
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jo == 4){
                    btnD.setBackgroundColor(Color.GREEN);
                    Wait();
                }
                else{
                    btnD.setBackgroundColor(Color.RED);
                    btnD.setText("X");
                    Popup();
                }
            }
        });
        //BUTTONS CLICK EVENTS END
    }

    public void iras(String S) {
        if (S.equals("easy")) {
            uj.feladatKerdes();
        } else if (S.equals("medium"))
            tvContent.setText("közepes bástya");
        else if (S.equals("hard"))
            tvContent.setText("Ez kemény lesz bástya");
    }

    public void Popup(){
        btnA.setBackgroundColor(Color.GREEN);
        startActivity(new Intent(start.this, Pop.class));
    }
    public void Wait(){
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                recreate();
            }
        },1000);

    }
}

interface IKerdes {
    void feladatKerdes();
}

interface IOsszekot {
    void feladatOsszekot();
}

class Easy implements IKerdes {

    @Override
    public void feladatKerdes() {

        start.tvContent.setText("Mennyi 2+3*2?");
        start.btnA.setText("8");
        start.btnB.setText("10");
        start.btnC.setText("7");
        start.btnD.setText("12");

        //átalakítani
        start.jo = 1;
    }

    public void kovFeladat() {
        start.tvContent.setText("könnyű2");
    }
}

class Medium implements IKerdes, IOsszekot {

    @Override
    public void feladatKerdes() {

    }

    @Override
    public void feladatOsszekot() {

    }
}
