package hr.foi.airprojekt.model.wrapper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrganizacijaWrapper {

    private String naziv;
    private double xKoordinata;
    private double yKoordinata;
    private String opis;
    private int brojHitnih;
    private int brojNehitnih;
    private List<OranizacijaTipWrapper> tipOrganizacijeList;

    public OrganizacijaWrapper() {
        tipOrganizacijeList = new ArrayList<>();
    }


}
