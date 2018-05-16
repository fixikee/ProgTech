package beadando.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnOption;
    Button btnUj;
    String Difficulty;
    Kerdesek kerdesdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adatbázis
        kerdesdb = Kerdesek.getInstance();

        btnOption = findViewById(R.id.btnOption);
        btnUj = findViewById(R.id.btnUjKerdes);

        btnUj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, beadando.game.KerdesHozzaad.class));
            }
        });
    }

    public void btnSubmit_OnClick(View v) {
        Intent intent = new Intent(MainActivity.this, beadando.game.Option.class);
        startActivity(intent);
    }

    public void btnStart_OnClick(View v) {
        if (Option.used) {
            Difficulty = getIntent().getStringExtra("dif");
            if (Difficulty.equals("medium"))
                initKerdesekMedium();
            else if (Difficulty.equals("hard"))
                initKerdesekHard();
            else if (Difficulty.equals("easy"))
                initKerdesek();
        } else {
            Difficulty = "easy";
            initKerdesek();
        }

        Intent intent = new Intent(MainActivity.this, beadando.game.start.class);
        intent.putExtra("difficulty", Difficulty);
        startActivity(intent);
    }

    private void initKerdesek() {
        kerdesdb.insertKerdes("Mennyi 2*2", "1", "2", "3", "4", 3, "easy");
        kerdesdb.insertKerdes("Mi Magyarország fővárosa?", "Budapest", "Bukarest", "Tápióbicskebattanaladány", "Eger", 0, "easy");
        kerdesdb.insertKerdes("Melyik ország fővárosa Freetown", "Guatemala", "Fidzsi-szigetek", "Guyana", "Sierra Leone", 3, "easy");
        kerdesdb.insertKerdes("Mikor volt a Nándorfehérvári diadal?", "1552", "1456", "1526", "2142", 1, "easy");
        kerdesdb.insertKerdes("Melyik fa levele található Kanada zászlóján?", "Tölgy", "Juhar", "Gyertyán", "Nyír", 1, "easy");
        kerdesdb.insertKerdes("Melyik országban uralkodott a napkirály?", "Spanyolország", "Egyiptom", "Franciaország", "India", 2, "easy");
        kerdesdb.insertKerdes("Mi az angol trónörökös hivatalos címe?", "Windsori herceg", "Walesi herceg", "Lord Mayor", "Infáns", 1, "easy");
        kerdesdb.insertKerdes("Az alábbiak közül mi a nadragulya?", "Nadrágszár", "Szarvasmarha", "Vizi madár", "Mérgező növény", 3, "easy");
        kerdesdb.insertKerdes("Ki zenésítette meg a Himnuszt?", "Liszt Ferenc", "Erkel Ferenc", "Kodály Zoltán", "Bartók Béla", 1, "easy");
        kerdesdb.insertKerdes("Ki volt az apja Mátyás királynak?", "Hunyadi László", "Szilágyi Mihály", "Hunyadi János", "Cillei Ulrik", 2, "easy");
        if (KerdesHozzaad.kerdesek.size() != 0) {
            for (int i = 0; i < KerdesHozzaad.kerdesek.size(); i++) {
                if (KerdesHozzaad.kerdesek.get(i).Difficulty.equals("easy")) {
                    kerdesdb.insertKerdes(KerdesHozzaad.kerdesek.get(i));
                }
            }
        }
    }

    private void initKerdesekMedium() {
        kerdesdb.insertKerdes("Milyen növényrész a barka?", "Levél", "Füzérvirágzat", "Léggyökér", "Termés", 1, "medium");
        kerdesdb.insertKerdes("Hol rendezték meg a 2004-es olimpiát?", "Athén", "Barcelona", "Atlanta", "Párizs", 0, "medium");
        if (KerdesHozzaad.kerdesek.size() != 0) {
            for (int i = 0; i < KerdesHozzaad.kerdesek.size(); i++) {
                if (KerdesHozzaad.kerdesek.get(i).Difficulty.equals("medium")) {
                    kerdesdb.insertKerdes(KerdesHozzaad.kerdesek.get(i));
                }
            }
        }
    }

    private void initKerdesekHard() {
        kerdesdb.insertKerdes("Hová vezet minden út a mondás szerint?", "Rómába", "Tibetbe", "Amerikába", "Párizsba", 0, "hard");
        kerdesdb.insertKerdes("Mi az óriáspöfeteg?", "Medúza", "Darázs", "Béka", "Gomba", 3, "hard");
        if (KerdesHozzaad.kerdesek.size() != 0) {
            for (int i = 0; i < KerdesHozzaad.kerdesek.size(); i++) {
                if (KerdesHozzaad.kerdesek.get(i).Difficulty.equals("hard")) {
                    kerdesdb.insertKerdes(KerdesHozzaad.kerdesek.get(i));
                }
            }
        }
    }
}

