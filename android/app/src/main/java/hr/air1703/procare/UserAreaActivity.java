package hr.air1703.procare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.air1703.procare.login.TokenApi;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        checkMessageToken();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent settingsIntent = new Intent(UserAreaActivity.this, SettingsActivity.class);
                UserAreaActivity.this.startActivity(settingsIntent);
                return true;
            case R.id.action_account:
                Intent accountIntent = new Intent(UserAreaActivity.this, AccountActivity.class);
                UserAreaActivity.this.startActivity(accountIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.bPostavke)
    public void buttonPostavkeClikced(View view) {
        Intent settingsIntent = new Intent(UserAreaActivity.this, SettingsActivity.class);
        UserAreaActivity.this.startActivity(settingsIntent);
    }

    @OnClick(R.id.bAccount)
    public void buttonAccountClicked(View view) {
        Intent accountIntent = new Intent(UserAreaActivity.this, AccountActivity.class);
        UserAreaActivity.this.startActivity(accountIntent);
    }

    @OnClick(R.id.bPozivPomoc)
    public void buttonPozivPomocClikced(View view) {
        Intent pozivPomocIntent = new Intent(UserAreaActivity.this, HelpCallActivity.class);
        UserAreaActivity.this.startActivity(pozivPomocIntent);
    }

    @OnClick(R.id.bListaHitnaPomoc)
    public void buttonListaHitnaPomocClicked(View view) {
        Intent listaHitnihPomoci = new Intent(UserAreaActivity.this, HospitalListActivity.class);
        UserAreaActivity.this.startActivity(listaHitnihPomoci);
    }

    private void checkMessageToken() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (sharedPreferences.contains("messageToken")) {
            TokenApi tokenApi = new TokenApi(getApplicationContext());
            tokenApi.updateToken(sharedPreferences.getString("messageToken", null));
        }


    }

}
