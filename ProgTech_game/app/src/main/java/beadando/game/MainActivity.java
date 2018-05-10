package beadando.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ConcurrentModificationException;

public class MainActivity extends AppCompatActivity {

    Button btnOption;
    Button btnExit;
    String Difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOption = findViewById(R.id.btnOption);
        btnExit = findViewById(R.id.btnExit);
        Difficulty = "easy";

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    public void btnSubmit_OnClick(View v) {
        Intent intent = new Intent(MainActivity.this, beadando.game.Option.class);
        startActivity(intent);
    }

    public void btnStart_OnClick(View v) {
        Difficulty = getIntent().getStringExtra("dif");

        Intent intent = new Intent(MainActivity.this, beadando.game.start.class);
        intent.putExtra("difficulty", Difficulty);
        startActivity(intent);
    }

}

