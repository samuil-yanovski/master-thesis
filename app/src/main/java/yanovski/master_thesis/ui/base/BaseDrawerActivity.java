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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Account;
import yanovski.master_thesis.ui.CalendarActivity;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.StudentProfileActivity;
import yanovski.master_thesis.ui.TeachersActivity;
import yanovski.master_thesis.ui.ThesesActivity;
import yanovski.master_thesis.ui.utils.CircleTransform;

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

    ImageView profile;
    TextView username;
    TextView email;

    @Inject
    Account currentAccount;
    @Inject
    Picasso picasso;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        username.setText(currentAccount.name);
        email.setText(currentAccount.contacts.email);
        picasso.load(currentAccount.avatar)
            .placeholder(R.drawable.ic_person_white)
            .error(R.drawable.ic_person_white)
            .transform(new CircleTransform())
            .into(profile);
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
    protected void onResume() {
        super.onResume();
        if (null == currentAccount) {
            finish();
            return;
        }
        navigationView.setNavigationItemSelectedListener(null);
        navigationView.setCheckedItem(getCurrentCheckedItemId());
        navigationView.setNavigationItemSelectedListener(this);
        initNavigationView();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ViewStub stub = ButterKnife.findById(this, R.id.content);
        stub.setLayoutResource(getContentLayoutId());
        stub.inflate();

        NavigationView navigationView = ButterKnife.findById(this, R.id.nav_view);
        View header = navigationView.inflateHeaderView(R.layout.nav_header_home);
        header.setOnClickListener((v) -> onProfileClicked());

        profile = ButterKnife.findById(header, R.id.profile);
        username = ButterKnife.findById(header, R.id.username);
        email = ButterKnife.findById(header, R.id.email);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_info) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            onProfileClicked();
        } else if (id == R.id.nav_teachers) {
            Intent intent = new Intent(this, TeachersActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_theses) {
            Intent intent = new Intent(this, ThesesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onProfileClicked() {
        Intent intent = new Intent(this, StudentProfileActivity.class);
        startActivity(intent);
    }
}
