package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import icepick.State;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.ui.fragments.StudentProfileFragment;

/**
 * Created by Samuil on 12/31/2015.
 */
public class StudentProfileActivity extends BaseActivity {

    public static final String KEY_STUDENT = "student";

    @State
    Student student;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inner_generic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState) {
            Bundle extras = getIntent().getExtras();
            if (null != extras && extras.containsKey(KEY_STUDENT)) {
                student = extras.getParcelable(KEY_STUDENT);
            }
            init();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (null == savedInstanceState) {
            Bundle extras = getIntent().getExtras();
            if (null != extras && extras.containsKey(KEY_STUDENT)) {
                student = extras.getParcelable(KEY_STUDENT);
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
        if (null == student) {
            finish();
            return;
        }

        StudentProfileFragment fragment = new StudentProfileFragment();
        fragment.setStudent(student);
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
