package yanovski.master_thesis.ui.adapters.base.creators;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Event;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;

/**
 * Created by Samuil on 12/30/2015.
 */
public class EventsVHCreator implements ViewHolderCreator<Event> {
    private static final int VIEW_TYPE_ID = CreatorManager.ID_GENERATOR.getAndIncrement();

    public static class EventViewHolder extends BaseViewHolder {
        @Bind(R.id.name)
        public TextView name;

        public EventViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE_ID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<Event> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<Event> adapter, BaseViewHolder holder,
        int position) {
        EventViewHolder h = (EventViewHolder) holder;

        Event item = adapter.getItem(position);
        h.name.setText(item.name);
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<Event> adapter, int position) {
        return true;
    }

    @Override
    public Class getItemClass() {
        return Event.class;
    }
}
