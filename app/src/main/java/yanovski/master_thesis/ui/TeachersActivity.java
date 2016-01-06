package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.fragments.TeachersFragment;

/**
 * Created by Samuil on 12/31/2015.
 */
public class TeachersActivity extends BaseDrawerActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_teachers;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_teachers;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        TeachersFragment fragment = new TeachersFragment();
        fragment.setMode(getMode());
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
