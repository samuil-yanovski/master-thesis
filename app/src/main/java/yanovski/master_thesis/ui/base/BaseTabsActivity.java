package yanovski.master_thesis.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.Bind;
import yanovski.master_thesis.R;

/**
 * Created by Samuil on 12/31/2015.
 */
public abstract class BaseTabsActivity extends BaseDrawerActivity {

    protected abstract PagerAdapter getViewPagerAdapter(FragmentManager manager);

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer_tabs;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_tabs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabs();
    }


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initTabs();
    }

    private void initTabs() {
        viewPager.setAdapter(getViewPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(viewPager);
    }
}
