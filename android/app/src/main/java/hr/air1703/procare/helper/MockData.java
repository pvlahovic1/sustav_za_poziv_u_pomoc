package hr.air1703.procare.helper;

import hr.air1703.database.model.Korisnik;

/**
 * Created by Tadija on 26.10.2017..
 */

public class MockData {

    public static void writeAll(){
        Korisnik korisnik = new Korisnik();
        korisnik.setOIB("12345678903");
        korisnik.setIme("Korisnik");
        korisnik.setPrezime("Test");
        korisnik.setEmail("mail@mail.com");
        korisnik.setLozinka("SHA-1");
        korisnik.setAdresa("AdresaTest");
        korisnik.setBrojMobitela("0987654321");

        korisnik.save();
    }

}
