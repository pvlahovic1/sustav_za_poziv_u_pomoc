package hr.air1703.map;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import hr.air1703.core.OrganizacijaDataLoadedListener;
import hr.air1703.core.OrganizacijeDataLoader;
import hr.air1703.database.model.Organizacija;
import hr.air1703.webservice.remote.loaders.OrganizacijaLocalDBDataLoader;

public class MapFragment extends Fragment implements OnMapReadyCallback, OrganizacijaDataLoadedListener {

    private com.google.android.gms.maps.MapFragment mapFragment;
    private GoogleMap map = null;
    private List<Organizacija> organizacijeList = null;

    // Default location Varaždin
    LatLng defaultLocation = new LatLng(46.307, 16.33);
    // Default zoom
    int defaultZoom = 12;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, container, false);
        mapFragment = new com.google.android.gms.maps.MapFragment();
        getFragmentManager().beginTransaction().add(R.id.frame, mapFragment).commit();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dohvatiOrganizacije();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (!(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation));
            map.moveCamera(CameraUpdateFactory.zoomTo(defaultZoom));

            if (organizacijeList != null) {
                LatLng position = null;
                for (Organizacija o : organizacijeList) {
                    position = new LatLng(o.getX_koordinata(), o.getY_koordinata());
                    googleMap.addMarker(new MarkerOptions()
                            .position(position)
                            .title(o.getNaziv())
                            .snippet(o.getOpis()));
                }
            }
        }
    }

    private void dohvatiOrganizacije() {
        OrganizacijeDataLoader odl;
        odl = new OrganizacijaLocalDBDataLoader();
        odl.loadOrganizacije(this);
    }

    @Override
    public void onDataLoaded(List<Organizacija> organizacije) {
        organizacijeList = organizacije;
    }

    @Override
    public void onFailure(int messageCode) {
        Log.i("service", getString(messageCode));
    }
}
