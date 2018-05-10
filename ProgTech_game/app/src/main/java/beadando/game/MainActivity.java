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
    String Difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOption = findViewById(R.id.btnOption);
    }

    public void btnSubmit_OnClick(View v) {
        Intent intent = new Intent(MainActivity.this, beadando.game.Option.class);
        startActivity(intent);
    }

    public void btnStart_OnClick(View v) {
        if (Option.used)
            Difficulty = getIntent().getStringExtra("dif");
        else Difficulty = "easy";

        Intent intent = new Intent(MainActivity.this, beadando.game.start.class);
        intent.putExtra("difficulty", Difficulty);
        startActivity(intent);
    }

}

