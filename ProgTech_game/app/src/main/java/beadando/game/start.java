package beadando.game;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.concurrent.Delayed;

public class start extends AppCompatActivity {

    static TextView tvContent;
    static Easy uj;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvContent = findViewById(R.id.tv_content);
        btnNext = findViewById(R.id.btnNext);

        String getDifficulty = getIntent().getStringExtra("difficulty");
        uj = new Easy();

        iras(getDifficulty);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uj.kovFeladat();
            }
        });
    }

    public void iras(String S){
        if(S.equals("easy")){
            uj.feladatKerdes();
        }

        else if(S.equals("medium"))
            tvContent.setText("közepes bástya");
        else if(S.equals("hard"))
            tvContent.setText("Ez kemény lesz bástya");
    }
}

interface IKerdes{
    void feladatKerdes();
}
interface IOsszekot{
    void feladatOsszekot();
}
class Easy implements IKerdes{

    @Override
    public void feladatKerdes() {
        start.tvContent.setText("könnyű");
    }
    public void kovFeladat(){
        start.tvContent.setText("könnyű2");
    }
}
class Medium implements IKerdes,IOsszekot{

    @Override
    public void feladatKerdes() {

    }

    @Override
    public void feladatOsszekot() {

    }
}
