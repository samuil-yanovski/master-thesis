package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.fragments.StudentsFragment;
import yanovski.master_thesis.ui.utils.UIModes;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (UIModes.MultiSelect.equals(getMode())) {
            getMenuInflater().inflate(R.menu.save, menu);
        }
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
            StudentsFragment fragment =
                (StudentsFragment) getSupportFragmentManager().findFragmentById(
                    R.id.fragment_container);
            fragment.returnStudents();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
