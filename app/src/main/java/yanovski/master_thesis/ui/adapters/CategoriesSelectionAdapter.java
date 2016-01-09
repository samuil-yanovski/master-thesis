package yanovski.master_thesis.ui.adapters;

import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.CategoriesSelectionVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class CategoriesSelectionAdapter extends BaseRecyclerViewAdapter<Category> {
    @Override
    protected void init() {
        super.init();
        add(new CategoriesSelectionVHCreator());
    }
}
