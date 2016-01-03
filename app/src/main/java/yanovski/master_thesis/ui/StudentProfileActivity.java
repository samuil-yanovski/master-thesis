package yanovski.master_thesis.ui;

import android.view.Menu;
import android.view.MenuItem;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;

/**
 * Created by Samuil on 12/31/2015.
 */
public class StudentProfileActivity extends BaseDrawerActivity {
    @Override
    protected int getContentLayoutId() {
        return R.layout.content_student_profile;
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
