package beadando.game;


import java.util.ArrayList;
import java.util.Random;

class Kerdes {
    String nev;
    String a;
    String b;
    String c;
    String d;
    int correct;
    String Difficulty;

    public Kerdes(String nev, String a, String b, String c, String d, int correct, String difficulty) {
        this.nev = nev;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
        this.Difficulty = difficulty;
    }
}

public class Kerdesek {
    ArrayList<Kerdes> kerdesek = new ArrayList<>();

    private Kerdesek() {

    }

    //SINGLETON
    public static Kerdesek Instance = null;

    public static Kerdesek getInstance() {
        if (Instance == null) Instance = new Kerdesek();
        return Instance;
    }

    public void insertKerdes(String kerdes, String A, String B, String C, String D, int correct, String difficulty) {
        Kerdes k = new Kerdes(kerdes, A, B, C, D, correct, difficulty);
        if (!kerdesek.contains(k))
            kerdesek.add(k);
    }

    public void insertKerdes(Kerdes k) {

        if (!kerdesek.contains(k))
            kerdesek.add(k);
    }

    public void updateKerdes(int id, String kerdes, String A, String B, String C, String D, int correct, String difficulty) {

    }

    public Kerdes getKerdes(int id) {

        return kerdesek.get(id);
    }

    public String[] getKerdesAsStringArray(int id) {
        Kerdes myKerdes = getKerdes(id);
        kerdesek.remove(id);

        String[] myStringArray = new String[7];
        myStringArray[0] = myKerdes.nev;
        myStringArray[1] = myKerdes.a;
        myStringArray[2] = myKerdes.b;
        myStringArray[3] = myKerdes.c;
        myStringArray[4] = myKerdes.d;
        myStringArray[5] = Integer.toString(myKerdes.correct);
        myStringArray[6] = myKerdes.Difficulty;
        return myStringArray;
    }

    public boolean Empty() {
        if (kerdesek.size() == 0) return true;
        else return false;
    }

    public String[] getNextQuestion(String Difficulty) {
        Random rnd = new Random();
        int i;
        while (true) {
            i = rnd.nextInt(kerdesek.size());
            if (!kerdesek.isEmpty() && kerdesek.get(i).Difficulty.equals(Difficulty))
                return getKerdesAsStringArray(i);
        }
    }
}


