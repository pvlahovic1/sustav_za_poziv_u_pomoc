package hr.air1703.procare.helper;

import hr.air1703.database.model.Korisnik;

/**
 * Created by Tadija on 26.10.2017..
 */

public class MockData {

    public static void writeAll(){
        Korisnik korisnik = new Korisnik();
        korisnik.setOib("12345678903");
        korisnik.setIme("Korisnik");
        korisnik.setPrezime("Test");
        korisnik.setMail("mail@mail.com");
        korisnik.setLozinka("SHA-1");
        korisnik.setAdresa("AdresaTest");
        korisnik.setBrojMob("0987654321");

        korisnik.save();
    }

}
