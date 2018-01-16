package hr.air1703.procare;

import org.junit.Test;

import hr.air1703.procare.utils.Hashing;

import static org.junit.Assert.*;
/**
 * Created by pvlahovic on 16.1.2018..
 */

public class HashingTest {

    @Test
    public void returnSHA1_onSHA1() {
        assertEquals(Hashing.SHA1("airProjekt"), "05B83107BA0A7B3471C6F1B99BD6DA5CFB2F248A");
    }

}
