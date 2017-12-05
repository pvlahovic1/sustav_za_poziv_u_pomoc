package hr.air1703.procare.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import hr.air1703.database.model.TipOrganizacije;
import hr.air1703.procare.R;

/**
 * Created by Tadija on 25.11.2017..
 */

public class HospitalsRecycleAdapter extends ExpandableRecyclerAdapter<ExpandableHospitalsItem, TipOrganizacije, HospitalsViewHolder, HospitalTypesViewHolder> {
    private LayoutInflater mInflator;

    public HospitalsRecycleAdapter(Context context, @NonNull List<ExpandableHospitalsItem> parentList) {
        super(parentList);
        mInflator = LayoutInflater.from(context);
    }

    //Create ViewHolders for both item types, Organizacija (parent) and TipOrganizacije (child)
    @NonNull
    @Override
    public HospitalsViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View hospitalsView = mInflator.inflate(R.layout.hospitals_list_item, parentViewGroup, false);
        return new HospitalsViewHolder(hospitalsView);
    }

    @NonNull
    @Override
    public HospitalTypesViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View hospitalTypesView = mInflator.inflate(R.layout.hospital_type_list_item, childViewGroup, false);
        return new HospitalTypesViewHolder(hospitalTypesView, this);
    }

    // bind viewHolders to list items
    @Override
    public void onBindParentViewHolder(@NonNull HospitalsViewHolder parentViewHolder, int parentPosition, @NonNull ExpandableHospitalsItem parentListItem) {
        ExpandableHospitalsItem expandableHospitalsItem = (ExpandableHospitalsItem) parentListItem;
        parentViewHolder.bind(expandableHospitalsItem);
    }

    @Override
    public void onBindChildViewHolder(@NonNull HospitalTypesViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull TipOrganizacije childListItem) {
        TipOrganizacije tipOrganizacije = childListItem;
        childViewHolder.bind(tipOrganizacije);
    }
}
