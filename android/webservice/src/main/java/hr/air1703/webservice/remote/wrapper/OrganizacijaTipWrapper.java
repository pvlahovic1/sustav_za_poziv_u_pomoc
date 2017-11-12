package hr.air1703.webservice.remote.wrapper;

/**
 * Created by pvlahovic on 12.11.2017..
 */

public class OrganizacijaTipWrapper {

    private String naziv;
    private String slikaURL;

    public OrganizacijaTipWrapper() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlikaURL() {
        return slikaURL;
    }

    public void setSlikaURL(String slikaURL) {
        this.slikaURL = slikaURL;
    }
}
