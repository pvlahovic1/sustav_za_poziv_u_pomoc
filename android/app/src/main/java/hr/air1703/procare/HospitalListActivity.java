package hr.air1703.procare;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.procare.fragments.HospitalListFragment;

public class HospitalListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        ButterKnife.bind(this);

        HospitalListFragment mHospitalListFragment = new HospitalListFragment();
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container, mHospitalListFragment);
        mFragmentTransaction.commit();
    }

    @OnClick(R.id.button_hospitalMap)
    public void buttonHospitalMapClicked(View view){
        Intent hospitalMapIntent = new Intent(HospitalListActivity.this, HospitalMapActivity.class);
        HospitalListActivity.this.startActivity(hospitalMapIntent);
    }
}