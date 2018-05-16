package beadando.game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class start extends AppCompatActivity {

    static TextView tvContent;
    static Easy ujEasy;
    static Medium ujMedium;
    static Hard ujHard;
    public int jo;
    static Handler timer;

    static Button btnA;
    static Button btnB;
    static Button btnC;
    static Button btnD;
    static Kerdesek k;
    public String getDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        k = Kerdesek.getInstance();

        tvContent = findViewById(R.id.tv_content);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        timer = new Handler();

        getDifficulty = getIntent().getStringExtra("difficulty");

        if (getDifficulty.equals("easy")) {
            ujEasy = new Easy();
            jo = ujEasy.jo;
        } else if (getDifficulty.equals("medium")) {
            ujMedium = new Medium();
            jo = ujMedium.jo;
        } else {
            ujHard = new Hard();
            jo = ujHard.jo;
        }

        //kérdés kiírása
        iras(getDifficulty);

        //BUTTONS CLICK EVENTS
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jo == 0) {
                    btnA.setBackgroundColor(Color.GREEN);
                    Wait();
                    //iras("easy");
                } else {
                    btnA.setBackgroundColor(Color.RED);
                    btnA.setText("X");
                    Popup();
                }
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jo == 1) {
                    btnB.setBackgroundColor(Color.GREEN);
                    Wait();
                    //iras("easy");
                } else {
                    btnB.setBackgroundColor(Color.RED);
                    btnB.setText("X");
                    Popup();
                }
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jo == 2) {
                    btnC.setBackgroundColor(Color.GREEN);
                    Wait();
                    //iras("easy");
                } else {
                    btnC.setBackgroundColor(Color.RED);
                    btnC.setText("X");
                    Popup();
                }
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jo == 3) {
                    btnD.setBackgroundColor(Color.GREEN);
                    Wait();
                    //iras("easy");
                } else {
                    btnD.setBackgroundColor(Color.RED);
                    btnD.setText("X");
                    Popup();
                }
            }
        });
        //BUTTONS CLICK EVENTS END
    }

    //Kérdés-válasz generálása
    public void iras(String S) {
        if (S.equals("easy")) {
            ujEasy.feladatKerdes();
            jo = ujEasy.jo;
        } else if (S.equals("medium")) {
            ujMedium.feladatKerdes();
            jo = ujMedium.jo;
        } else {
            ujHard.feladatKerdes();
            jo = ujHard.jo;
        }
    }

    //Rossz válasz után a jó "zöld" kielemése
    public void Popup() {
        if (jo == 0)
            btnA.setBackgroundColor(Color.GREEN);
        else if (jo == 1)
            btnB.setBackgroundColor(Color.GREEN);
        else if (jo == 2)
            btnC.setBackgroundColor(Color.GREEN);
        else if (jo == 3)
            btnD.setBackgroundColor(Color.GREEN);
        startActivity(new Intent(start.this, Pop.class));
    }

    public void PopWin(){
        startActivity(new Intent(start.this,PopWin.class));
    }

    //Uj kérdés előtti timer
    public void Wait() {
        if (k.Empty()) {
            PopWin();
        } else {
            timer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    recreate();
                }
            }, 1000);
        }
    }
}

//Stratégia
interface IKerdes {
    void feladatKerdes();
}

class Easy implements IKerdes {
    public int jo;

    @Override
    public void feladatKerdes() {
        String[] kerdes = start.k.getNextQuestion("easy");

        start.tvContent.setText(kerdes[0]);
        start.btnA.setText(kerdes[1]);
        start.btnB.setText(kerdes[2]);
        start.btnC.setText(kerdes[3]);
        start.btnD.setText(kerdes[4]);

        //jó válasz ID
        jo = Integer.parseInt(kerdes[5]);
    }
}

class Medium implements IKerdes {
    public int jo;

    @Override
    public void feladatKerdes() {
        String[] kerdes = start.k.getNextQuestion("medium");

        start.tvContent.setText(kerdes[0]);
        start.btnA.setText(kerdes[1]);
        start.btnB.setText(kerdes[2]);
        start.btnC.setText(kerdes[3]);
        start.btnD.setText(kerdes[4]);

        //jó válasz ID
        jo = Integer.parseInt(kerdes[5]);
    }
}

class Hard implements IKerdes {
    public int jo;

    @Override
    public void feladatKerdes() {
        String[] kerdes = start.k.getNextQuestion("hard");

        start.tvContent.setText(kerdes[0]);
        start.btnA.setText(kerdes[1]);
        start.btnB.setText(kerdes[2]);
        start.btnC.setText(kerdes[3]);
        start.btnD.setText(kerdes[4]);

        //jó válasz ID
        jo = Integer.parseInt(kerdes[5]);
    }
}
