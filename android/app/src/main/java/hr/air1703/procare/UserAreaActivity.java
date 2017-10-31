package hr.air1703.procare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

    }

    @OnClick(R.id.bPostavke)
    public void buttonPostavkeClikced(View view) {
        Intent settingsIntent = new Intent(UserAreaActivity.this, SettingsActivity.class);
        UserAreaActivity.this.startActivity(settingsIntent);
    }

}
