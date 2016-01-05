package yanovski.master_thesis.ui;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.utils.NavigationViewListener;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;

/**
 * Created by Samuil on 12/31/2015.
 */
public class CalendarActivity extends BaseDrawerActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_calendar;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_calendar;
    }

    @Override
    protected NavigationViewListener getNavigationViewListener() {
        return new StudentNavigationViewListener();
    }

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
            Intent intent = new Intent(this, NewEventActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
