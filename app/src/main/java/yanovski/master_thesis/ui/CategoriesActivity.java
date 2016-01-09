package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.ui.fragments.CategorySelectionFragment;

/**
 * Created by Samuil on 12/31/2015.
 */
public class CategoriesActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inner_generic;
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

        CategorySelectionFragment fragment = new CategorySelectionFragment();
        fragment.setMode(getMode());
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
