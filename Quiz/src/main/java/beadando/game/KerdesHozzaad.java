package beadando.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class KerdesHozzaad extends AppCompatActivity {

    static Spinner spinner;
    static Spinner spinner1;
    static Button btnHozzaad;
    static EditText kerdes;
    static EditText a;
    static EditText b;
    static EditText c;
    static EditText d;
    public static ArrayList<Kerdes> kerdesek = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kerdes_hozzaad);

        //view lekérések
        spinner = findViewById(R.id.correct);
        spinner1 = findViewById(R.id.spinnerDiff);
        btnHozzaad = findViewById(R.id.btnHozzaad);
        kerdes = findViewById(R.id.tvKerdes);
        a = findViewById(R.id.tvA);
        b = findViewById(R.id.tvB);
        c = findViewById(R.id.tvC);
        d = findViewById(R.id.tvD);

        //spinnerek létrehozsása
        //spinner1
        ArrayAdapter<String> adapter;
        ArrayList<String> list;

        list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //spinner2
        ArrayAdapter<String> adapter1;
        ArrayList<String> list1;

        list1 = new ArrayList<String>();
        list1.add("easy");
        list1.add("medium");
        list1.add("hard");
        adapter1 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        btnHozzaad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kerdes kerdes2 = null;
                if (spinner.getSelectedItem().equals("A")) {
                    kerdes2 = new Kerdes(kerdes.getText().toString(), a.getText().toString(), b.getText().toString(), c.getText().toString(), d.getText().toString(), 0, spinner1.getSelectedItem().toString());
                }
                if (spinner.getSelectedItem().equals("B")) {
                    kerdes2 = new Kerdes(kerdes.getText().toString(), a.getText().toString(), b.getText().toString(), c.getText().toString(), d.getText().toString(), 1, spinner1.getSelectedItem().toString());
                }
                if (spinner.getSelectedItem().equals("C")) {
                    kerdes2 = new Kerdes(kerdes.getText().toString(), a.getText().toString(), b.getText().toString(), c.getText().toString(), d.getText().toString(), 2, spinner1.getSelectedItem().toString());
                }
                if (spinner.getSelectedItem().equals("D")) {
                    kerdes2 = new Kerdes(kerdes.getText().toString(), a.getText().toString(), b.getText().toString(), c.getText().toString(), d.getText().toString(), 3, spinner1.getSelectedItem().toString());
                }
                kerdesek.add(kerdes2);
                Kerdesek k = Kerdesek.getInstance();
                k.kerdesek.add(kerdes2);
            }
        });
    }
}
