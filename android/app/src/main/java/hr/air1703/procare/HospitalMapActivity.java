package hr.air1703.procare;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.map.MapFragment;

public class HospitalMapActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_map);

        ButterKnife.bind(this);

        MapFragment mapFragment = new MapFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, mapFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @OnClick(R.id.button_hospitalList)
    public void buttonHospitalListClicked(View view){
        Intent hospitalListIntent = new Intent(HospitalMapActivity.this, HospitalListActivity.class);
        HospitalMapActivity.this.startActivity(hospitalListIntent);
    }
}
