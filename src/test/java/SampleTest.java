import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oleksandr_Khodakovsk on 9/28/2016.
 */
public class SampleTest {

    @Test(timeout = 3000 /* ,expected = IOException.class */)
    public void testCompareTo() {
        boolean expected =  true ;
        boolean actual = (true ? true : false);
        assertEquals(expected, actual);
    }
}
