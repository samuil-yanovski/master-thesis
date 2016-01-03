package yanovski.master_thesis.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.adapters.InfoAdapter;
import yanovski.master_thesis.ui.base.BaseTabsActivity;
import yanovski.master_thesis.ui.utils.NavigationViewListener;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;

public class HomeActivity extends BaseTabsActivity {

    @Override
    protected PagerAdapter getViewPagerAdapter(FragmentManager manager) {
        return new InfoAdapter(manager);
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_info;
    }

    @Override
    protected NavigationViewListener getNavigationViewListener() {
        return new StudentNavigationViewListener();
    }
}
