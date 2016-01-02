package yanovski.master_thesis.ui.adapters;

import yanovski.master_thesis.data.models.Event;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.EventsVHCreator;

/**
 * Created by Samuil on 12/30/2015.
 */
public class EventsAdapter extends BaseRecyclerViewAdapter<Event> {
    @Override
    protected void init() {
        super.init();
        add(new EventsVHCreator());
    }
}
