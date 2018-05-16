package beadando.game;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class KerdesekTest {

    @Test
    public void empty() {
        boolean varhato = true;
        boolean kapott = false;
        ArrayList<Integer> test = new ArrayList<>();
        if(test.size() == 0)
            kapott = true;
        assertThat(kapott,is(varhato));
    }

    @Test
    public void getkerdes(){
        int id = 1;
        ArrayList<String> test = new ArrayList<>();
        test.add("pista");
        test.add("bela");

        assertThat(test.get(id),is("bela"));
    }
}