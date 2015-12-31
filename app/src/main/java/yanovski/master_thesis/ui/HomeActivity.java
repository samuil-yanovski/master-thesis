package yanovski.master_thesis.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;

import yanovski.master_thesis.ui.adapters.InfoAdapter;
import yanovski.master_thesis.ui.base.BaseTabsActivity;

public class HomeActivity extends BaseTabsActivity {

    @Override
    protected PagerAdapter getViewPagerAdapter(FragmentManager manager) {
        return new InfoAdapter(manager);
    }

}
