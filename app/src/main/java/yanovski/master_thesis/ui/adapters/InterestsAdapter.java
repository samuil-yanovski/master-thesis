package yanovski.master_thesis.ui.adapters;

import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.InterestsVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class InterestsAdapter extends BaseRecyclerViewAdapter<Interest> {
    @Override
    protected void init() {
        super.init();
        add(new InterestsVHCreator());
    }
}
