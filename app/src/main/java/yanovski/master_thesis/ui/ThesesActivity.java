package yanovski.master_thesis.ui;

import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.base.BaseDrawerActivity;

/**
 * Created by Samuil on 12/31/2015.
 */
public class ThesesActivity extends BaseDrawerActivity {
    @Override
    protected int getContentLayoutId() {
        return R.layout.content_theses;
    }

    @Override
    protected int getCurrentCheckedItemId() {
        return R.id.nav_theses;
    }
}
