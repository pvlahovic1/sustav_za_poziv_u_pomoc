package hr.air1703.procare.adapters;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

import hr.air1703.database.model.Organizacija;
import hr.air1703.database.model.TipOrganizacije;

/**
 * Created by Tadija on 25.11.2017..
 */

public class ExpandableHospitalsItem extends Organizacija implements Parent<TipOrganizacije> {

    private List<TipOrganizacije> tipOrganizacije;

    public ExpandableHospitalsItem(Organizacija organizacija) {
        super(organizacija);
        this.tipOrganizacije = organizacija.getTipOrganizacijeList();
    }

    @Override
    public List<TipOrganizacije> getChildList(){
        return tipOrganizacije;
    }

    @Override
    public boolean isInitiallyExpanded(){
        return false;
    }
}
