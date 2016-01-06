package yanovski.master_thesis.ui.utils;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import yanovski.master_thesis.ui.base.BaseActivity;

/**
 * Created by Samuil on 1/3/2016.
 */
public interface NavigationViewListener {
    void load(NavigationView view);
    void personalize();
    boolean onItemSelected(BaseActivity activity, MenuItem item);
}
