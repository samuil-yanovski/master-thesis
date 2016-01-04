package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import icepick.State;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.ui.fragments.ThesesFragment;

/**
 * Created by Samuil on 12/31/2015.
 */
public class FilteredThesesActivity extends BaseActivity {

    public static final String KEY_TEACHER = "teacher";

    @State
    Teacher teacher;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_filtered_theses;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState) {
            Bundle extras = getIntent().getExtras();
            if (null != extras && extras.containsKey(KEY_TEACHER)) {
                teacher = extras.getParcelable(KEY_TEACHER);
            }
            init();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if (null == savedInstanceState) {
            Bundle extras = getIntent().getExtras();
            if (null != extras && extras.containsKey(KEY_TEACHER)) {
                teacher = extras.getParcelable(KEY_TEACHER);
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
        if (null == teacher) {
            finish();
            return;
        }

        ThesesFragment fragment = new ThesesFragment();
        fragment.setTeacher(teacher);
        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
