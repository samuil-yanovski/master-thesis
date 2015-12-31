package yanovski.master_thesis.ui.adapters;

import yanovski.master_thesis.data.models.Resource;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.ResourcesVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ResourcesAdapter extends BaseRecyclerViewAdapter<Resource> {
    @Override
    protected void init() {
        super.init();
        add(new ResourcesVHCreator());
    }
}
