package yanovski.master_thesis.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.ViewStub;

import butterknife.Bind;
import butterknife.ButterKnife;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.utils.NavigationViewListener;

/**
 * Created by Samuil on 12/31/2015.
 */
public abstract class BaseDrawerActivity extends BaseActivity implements
    NavigationView.OnNavigationItemSelectedListener {

    protected abstract int getContentLayoutId();
    protected abstract int getCurrentCheckedItemId();
    protected abstract NavigationViewListener getNavigationViewListener();

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    NavigationViewListener listener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listener = getNavigationViewListener();
        super.onCreate(savedInstanceState);
        initActionBar();
        MasterThesisApplication.getMainComponent()
            .inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initActionBar();
        MasterThesisApplication.getMainComponent()
            .inject(this);
    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(null);
        navigationView.setCheckedItem(getCurrentCheckedItemId());
        navigationView.setNavigationItemSelectedListener(this);
        listener.personalize();
    }

    private void initActionBar() {
        ActionBarDrawerToggle toggle =
            new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(getCurrentCheckedItemId());
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initNavigationView();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ViewStub stub = ButterKnife.findById(this, R.id.content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();

        NavigationView navigationView = ButterKnife.findById(this, R.id.nav_view);
        listener.loadHeader(navigationView);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean handled = listener.onItemSelected(this, item);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return handled;
    }
}
