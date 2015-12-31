package yanovski.master_thesis.ui.adapters;

import yanovski.master_thesis.data.models.Document;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.DocumentsVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class DocumentsAdapter extends BaseRecyclerViewAdapter<Document> {
    @Override
    protected void init() {
        super.init();
        add(new DocumentsVHCreator());
    }
}
