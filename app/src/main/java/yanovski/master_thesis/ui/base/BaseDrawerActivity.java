package yanovski.master_thesis.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

import butterknife.Bind;
import butterknife.ButterKnife;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.TeachersActivity;
import yanovski.master_thesis.ui.ThesesActivity;

/**
 * Created by Samuil on 12/31/2015.
 */
public abstract class BaseDrawerActivity extends BaseActivity implements
    NavigationView.OnNavigationItemSelectedListener {

    protected abstract int getContentLayoutId();
    protected abstract int getCurrentCheckedItemId();

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(getCurrentCheckedItemId());
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ViewStub stub = ButterKnife.findById(this, R.id.content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_info) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_teachers) {
            Intent intent = new Intent(this, TeachersActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_theses) {
            Intent intent = new Intent(this, ThesesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {

        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
