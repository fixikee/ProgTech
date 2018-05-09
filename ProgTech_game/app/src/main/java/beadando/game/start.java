package beadando.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class start extends AppCompatActivity {

    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvContent = findViewById(R.id.tv_content);

        String getDifficulty = getIntent().getStringExtra("difficulty");

        iras(getDifficulty);
    }

    public void iras(String S){
        if(S.equals("easy"))
            easy();
        else if(S.equals("medium"))
            tvContent.setText("közepes bástya");
        else if(S.equals("S"))
            tvContent.setText("Ez kemény lesz bástya");
    }
    public void easy(){
        tvContent.setText("easy brada");
    }
}
