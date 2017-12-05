package hr.air1703.procare.adapters;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.air1703.database.model.Organizacija;
import hr.air1703.procare.R;

/**
 * Created by Tadija on 25.11.2017..
 */

public class HospitalsViewHolder extends ParentViewHolder {
    @BindView(R.id.hospital_name)
    TextView mHospitalName;
    @BindView(R.id.broj_hitnih_text_view)
    TextView mBrojHitnih;
    @BindView(R.id.broj_nehitnih_text_view)
    TextView mBrojNehitnih;

    View mItemView;
    HospitalsRecycleAdapter mAdapter;

    //constructor binds the ButterKnife library and makes itemView avalible locally
    public HospitalsViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    // when the adapter is implemented this method is used to bind list elements with the recycler-view, here, we populate the Views
    public void bind(Organizacija organizacija){
        mHospitalName.setText(organizacija.getNaziv());
        mBrojHitnih.setText("H: " + String.valueOf(organizacija.getBrojHitnih()));
        mBrojNehitnih.setText(String.valueOf("N: " + organizacija.getBrojNehitnih()));
    }
}
