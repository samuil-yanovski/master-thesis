package yanovski.master_thesis.ui;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.fragments.StudentProfileFragment;
import yanovski.master_thesis.ui.fragments.TeacherProfileFragment;

/**
 * Created by Samuil on 12/31/2015.
 */
public class MyProfileActivity extends BaseDrawerActivity {

    ImageView avatar;
    TextView name;

    @Inject
    @Nullable
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MasterThesisApplication.getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        MasterThesisApplication.getMainComponent().inject(this);
        super.onCreate(savedInstanceState, persistentState);
        init();
    }


    private void init() {
        if (null == person) {
            finish();
            return;
        }

        switch (person.getType()) {
            case STUDENT: {
                initStudent();
                break;
            }
            case TEACHER: {
                initTeacher();
                break;
            }
        }
    }

    private void initTeacher() {
        TeacherProfileFragment fragment = new TeacherProfileFragment();
        fragment.setTeacher((Teacher) person);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();
    }

    private void initStudent() {
        StudentProfileFragment fragment = new StudentProfileFragment();
        fragment.setStudent((Student) person);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_generic;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_profile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            avatar = ButterKnife.findById(this, R.id.avatar);
            name = ButterKnife.findById(this, R.id.name);

            Intent intent = getEditIntent();
            startIntent(intent, null != name && null != avatar);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startIntent(Intent intent, boolean animated) {
        if (animated) {
            Pair<View, String> avatarPair =
                Pair.create(avatar, getString(R.string.transition_avatar));
            Pair<View, String> namePair = Pair.create(name, getString(R.string.transition_name));
            ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, avatarPair, namePair);

            ActivityCompat.startActivity(this, intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @NonNull
    private Intent getEditIntent() {
        Intent intent = null;
        if (null != person) {
            switch (person.getType()) {
                case STUDENT: {
                    intent = new Intent(this, EditStudentProfileActivity.class);
                    break;
                }
                case TEACHER: {
                    intent = new Intent(this, EditTeacherProfileActivity.class);
                    break;
                }
            }
        } else {
            intent = new Intent(this, EditStudentProfileActivity.class);
        }
        return intent;
    }

    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
        if (null != person) {
            switch (person.getType()) {
                case STUDENT: {
                    builder.addParentStack(HomeActivity.class);
                    break;
                }
                case TEACHER: {
                    builder.addParentStack(StudentsActivity.class);
                    break;
                }
            }
        } else {
            builder.addParentStack(HomeActivity.class);
        }
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        Intent intent = null;
        if (null != person) {
            switch (person.getType()) {
                case STUDENT: {
                    intent = new Intent(this, HomeActivity.class);
                    break;
                }
                case TEACHER: {
                    intent = new Intent(this, StudentsActivity.class);
                    break;
                }
            }
        } else {
            intent = new Intent(this, HomeActivity.class);
        }
        return intent;
    }
}
