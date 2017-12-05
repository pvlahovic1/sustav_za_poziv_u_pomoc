package hr.air1703.procare.adapters;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air1703.database.model.TipOrganizacije;
import hr.air1703.procare.R;

/**
 * Created by Tadija on 25.11.2017..
 */

public class HospitalTypesViewHolder extends ChildViewHolder{

    @BindView(R.id.tip_organizacije_list)
    TextView mTipOrganizacijeList;

    private TipOrganizacije mTipOrganizacije;
    View mItemView;
    HospitalsRecycleAdapter mAdapter;

    public HospitalTypesViewHolder(View itemView, HospitalsRecycleAdapter adapter) {
        super(itemView);
        mItemView = itemView;
        mAdapter = adapter;
        ButterKnife.bind(this, itemView);
    }

    public void bind(TipOrganizacije tipOrganizacije){
        mTipOrganizacije = tipOrganizacije;
        mTipOrganizacijeList.setText(tipOrganizacije.getNaziv()); //potrebno doraditi
    }
}
