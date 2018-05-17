package beadando.game;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class popSub extends Activity {

    Button btnMenu;
    EditText nev;
    EditText email;
    Szerver sz;
    Handler timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_sub);
        timer = new Handler();

        nev = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);

        sz = new Szerver();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .7), (int) (height * .5));

        btnMenu = findViewById(R.id.btnFel);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nev.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                    Recreate();
                } else {
                    Feliratkozok f = new Feliratkozok(nev.getText().toString(), email.getText().toString());
                    sz.Subscribe(f);
                    sz.NewTask("Update: Subscribe feature is finally working!");
                    Wait();
                }
            }
        });
    }

    public void Wait() {
        btnMenu.setBackgroundColor(Color.GREEN);
        btnMenu.setText("\u2714");
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(popSub.this, Option.class));
            }
        }, 1000);
    }
    public void Recreate() {
        btnMenu.setBackgroundColor(Color.RED);
        btnMenu.setText("Hiányzó adat");
        timer.postDelayed(new Runnable() {
            @Override
            public void run() {
                recreate();
            }
        }, 2000);
    }
}

//OBSERVER
class Feliratkozok implements Observer {
    private String nev;
    private String email;

    public Feliratkozok(String nev, String email) {
        this.nev = nev;
        this.email = email;
    }

    @Override
    public void update(Observable o, Object temp) {
        System.out.println(temp);
    }
}

class Szerver extends Observable {
    String message = null;
    ArrayList<Feliratkozok> clients = new ArrayList<>();

    public void Subscribe(Feliratkozok client) {
        clients.add(client);
    }

    public void UnSubscribe(Feliratkozok client) {
        clients.remove(client);
    }

    public void Notify() {
        for (Feliratkozok c : clients) {
            c.update(this, message);
        }
    }

    public void NewTask(String task) {
        message = task;
        Notify();
    }
}
