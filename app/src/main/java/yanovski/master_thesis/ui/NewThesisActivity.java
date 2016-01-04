package yanovski.master_thesis.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseActivity;
import yanovski.master_thesis.ui.fragments.NewThesisFragment;

/**
 * Created by Samuil on 12/31/2015.
 */
public class NewThesisActivity extends BaseActivity {

    public static final String KEY_TEACHER = "teacher";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            NewThesisFragment fragment =
                (NewThesisFragment) getSupportFragmentManager().findFragmentById(
                    R.id.fragment_new_thesis);
            fragment.send();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_thesis;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
