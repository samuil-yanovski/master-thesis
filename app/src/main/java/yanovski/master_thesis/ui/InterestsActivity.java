package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import icepick.State;
import yanovski.master_thesis.Constants;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.ui.fragments.InterestsFragment;

/**
 * Created by Samuil on 12/31/2015.
 */
public class InterestsActivity extends BaseActivity {


    @State
    ArrayList<Interest> interests;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inner_generic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState) {
            Bundle extras = getIntent().getExtras();
            if (null != extras && extras.containsKey(Constants.KEY_ITEMS)) {
                interests = extras.getParcelableArrayList(Constants.KEY_ITEMS);
            }
            init();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (null == savedInstanceState) {
            Bundle extras = getIntent().getExtras();
            if (null != extras && extras.containsKey(Constants.KEY_ITEMS)) {
                interests = extras.getParcelableArrayList(Constants.KEY_ITEMS);
            }
            init();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState,
        PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        init();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        init();
    }

    private void init() {
        InterestsFragment fragment = new InterestsFragment();
        fragment.setInterests(interests);
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            InterestsFragment fragment =
                (InterestsFragment) getSupportFragmentManager().findFragmentById(
                    R.id.fragment_container);
            fragment.returnInterests();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
