package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.fragments.TeacherProfileFragment;
import yanovski.master_thesis.ui.utils.NavigationViewListener;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;

/**
 * Created by Samuil on 12/31/2015.
 */
public class TeacherProfileActivity extends BaseDrawerActivity {

    public static final String KEY_TEACHER = "teacher";

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_teacher_profile;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_profile;
    }

    @Override
    protected NavigationViewListener getNavigationViewListener() {
        return new StudentNavigationViewListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        if (!extras.containsKey(KEY_TEACHER)) {
            finish();
            return;
        }
        Teacher teacher = extras.getParcelable(KEY_TEACHER);
        TeacherProfileFragment fragment = new TeacherProfileFragment();
        fragment.setTeacher(teacher);
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();
    }
}
