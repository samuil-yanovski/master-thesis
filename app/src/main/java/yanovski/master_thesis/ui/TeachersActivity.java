package yanovski.master_thesis.ui;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;
import yanovski.master_thesis.ui.utils.NavigationViewListener;
import yanovski.master_thesis.ui.utils.StudentNavigationViewListener;

/**
 * Created by Samuil on 12/31/2015.
 */
public class TeachersActivity extends BaseDrawerActivity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.content_teachers;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_teachers;
    }

    @Override
    protected NavigationViewListener getNavigationViewListener() {
        return new StudentNavigationViewListener();
    }
}
