package hr.foi.airprojekt.web.model.wrappers;

import hr.foi.airprojekt.web.model.OrganizacijaTip;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrganizacijaEditWrapper {

    private Integer idOrganizacije;
    private String naziv;
    private double x;
    private double y;
    private String opis;
    private int brojHitnih;
    private int brojNehitnih;
    List<OrganizacijaTip> tipoviOrganizacije;
    List<OrganizacijaTip> sviTipoviOrganizacija;
    List<Integer> odabraniTipoviOrganizacije;

    public OrganizacijaEditWrapper() {
        tipoviOrganizacije = new ArrayList<>();
        sviTipoviOrganizacija = new ArrayList<>();
        odabraniTipoviOrganizacije = new ArrayList<>();
    }
}
