package mob.star2dr;


import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import mob.star2dr.fragments.DateFragment;
import mob.star2dr.fragments.RouteDetailFragment;
import mob.star2dr.fragments.StopTimesFragment;
import mob.star2dr.fragments.StopsFragment;
import mob.star2dr.model.StopTimes;

public class MainActivity extends AppCompatActivity implements DateFragment.OnFragmentInteractionListener, StopsFragment.OnFragmentInteractionListener, StopTimesFragment.OnFragmentInteractionListener, RouteDetailFragment.OnFragmentInteractionListener {
    private DateFragment dateFragment;
    private StopsFragment stopsFragment;
    private RouteDetailFragment routeDetailFragment;
    private StopTimesFragment stopTimesFragment;

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = this.getSupportFragmentManager();
        dateFragment = (DateFragment) fm.findFragmentById(R.id.fragment_date);
        stopsFragment = (StopsFragment) fm.findFragmentById(R.id.fragment_stops);
        routeDetailFragment = (RouteDetailFragment) fm.findFragmentById(R.id.fragment_routes_detail);
        stopTimesFragment = (StopTimesFragment) fm.findFragmentById(R.id.fragment_stop_times);
        FragmentTransaction ft =fm.beginTransaction();
        ft.hide(routeDetailFragment);
        ft.hide(stopTimesFragment);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
