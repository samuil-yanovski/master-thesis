package yanovski.master_thesis.ui.utils;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

/**
 * Created by Samuil on 1/3/2016.
 */
public interface NavigationViewListener {
    void loadHeader(NavigationView view);
    void personalize();
    boolean onItemSelected(Activity activity, MenuItem item);
}
