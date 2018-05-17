package beadando.game;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

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





class KerdesAdatB extends SQLiteOpenHelper
{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "KerdesekDB.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_KERDES + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_A + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_B + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_C + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_D + " TEXT, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT + " INTEGER, " +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_DIF + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;


    public KerdesAdatB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long insertKerdes(Kerdes k) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_KERDES, k.nev);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_A, k.a);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_B, k.b);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_C, k.c);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_D, k.d);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT, k.correct);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_DIF, k.Difficulty);
        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        return newRowId;
    }

    public Cursor getKerdesAcCursor(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_KERDES,
                FeedReaderContract.FeedEntry.COLUMN_NAME_A,
                FeedReaderContract.FeedEntry.COLUMN_NAME_B,
                FeedReaderContract.FeedEntry.COLUMN_NAME_C,
                FeedReaderContract.FeedEntry.COLUMN_NAME_D,
                FeedReaderContract.FeedEntry.COLUMN_NAME_CORRECT,
                FeedReaderContract.FeedEntry.COLUMN_NAME_DIF
        };
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArgs = { Integer.toString(id) };
        Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME, projection, selection, selectionArgs, null,null ,"DESC");
        return cursor;
    }
}

final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "kerdesek";
        public static final String COLUMN_NAME_KERDES = "kerdes";
        public static final String COLUMN_NAME_A = "a";
        public static final String COLUMN_NAME_B = "b";
        public static final String COLUMN_NAME_C = "c";
        public static final String COLUMN_NAME_D = "d";
        public static final String COLUMN_NAME_CORRECT = "correct";
        public static final String COLUMN_NAME_DIF = "dif";
    }
}
