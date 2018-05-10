package beadando.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.net.Inet4Address;

public class Option extends AppCompatActivity {

    RadioButton rbEasy;
    RadioButton rbMedium;
    RadioButton rbHard;
    RadioGroup radioGroup;
    Button save;
    public static boolean used = false;
    public int dif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        rbEasy = findViewById(R.id.rbEasy);
        rbMedium = findViewById(R.id.rbMedium);
        rbHard = findViewById(R.id.rbHard);
        radioGroup = findViewById(R.id.radioG);
        save = findViewById(R.id.button);

        dif = 0;
    }

    public void onSave(View v) {
        used = true;
        Intent intent = new Intent(Option.this, beadando.game.MainActivity.class);
        if (rbEasy.isChecked()) {
            intent.putExtra("dif", "easy");
        } else if (rbMedium.isChecked()) {
            intent.putExtra("dif", "medium");
        } else {
            intent.putExtra("dif", "hard");
        }
        startActivity(intent);
    }
}
