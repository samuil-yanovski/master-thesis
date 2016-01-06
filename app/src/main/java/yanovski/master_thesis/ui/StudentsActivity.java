package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.fragments.StudentsFragment;
import yanovski.master_thesis.ui.utils.NavigationViewListener;
import yanovski.master_thesis.ui.utils.TeacherNavigationViewListener;

/**
 * Created by Samuil on 12/31/2015.
 */
public class StudentsActivity extends BaseDrawerActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_students;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_students;
    }

    @Override
    protected NavigationViewListener getNavigationViewListener() {
        return new TeacherNavigationViewListener();
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
        StudentsFragment fragment = new StudentsFragment();
        fragment.setMode(getMode());
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();
    }
}
