package hr.air1703.webservice.remote.wrapper;

import java.util.List;

/**
 * Created by pvlahovic on 12.11.2017..
 */

public class OrganizacijaWrapper {

    private String naziv;
    private double xkoordinata;
    private double ykoordinata;
    private String opis;
    private int brojHitnih;
    private int brojNehitnih;
    private List<OrganizacijaTipWrapper> tipOrganizacijeList;

    public OrganizacijaWrapper() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getxkoordinata() {
        return xkoordinata;
    }

    public void setxkoordinata(double xkoordinata) {
        this.xkoordinata = xkoordinata;
    }

    public double getykoordinata() {
        return ykoordinata;
    }

    public void setykoordinata(double ykoordinata) {
        this.ykoordinata = ykoordinata;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojHitnih() {
        return brojHitnih;
    }

    public void setBrojHitnih(int brojHitnih) {
        this.brojHitnih = brojHitnih;
    }

    public int getBrojNehitnih() {
        return brojNehitnih;
    }

    public void setBrojNehitnih(int brojNehitnih) {
        this.brojNehitnih = brojNehitnih;
    }

    public List<OrganizacijaTipWrapper> getTipOrganizacijeList() {
        return tipOrganizacijeList;
    }

    public void setTipOrganizacijeList(List<OrganizacijaTipWrapper> tipOrganizacijeList) {
        this.tipOrganizacijeList = tipOrganizacijeList;
    }

}
