package yanovski.master_thesis.ui.adapters.base.creators;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Resource;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;
import yanovski.master_thesis.utils.UrlHelper;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ResourcesVHCreator implements ViewHolderCreator<Resource> {
    private static final int VIEW_TYPE_ID = CreatorManager.ID_GENERATOR.getAndIncrement();

    public static class ResourceViewHolder extends BaseViewHolder {
        @Bind(R.id.title)
        public TextView title;
        public String url;
        private UrlHelper urlHelper;

        public ResourceViewHolder(View view) {
            super(view);
            urlHelper = MasterThesisApplication.getMainComponent().getHelper();
        }

        @Override
        public void onClick(View v) {
            super.onClick(v);
            urlHelper.loadDataUrl(url);
        }
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE_ID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<Resource> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_resource, parent, false);
        return new ResourceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<Resource> adapter, BaseViewHolder holder,
        int position) {
        ResourceViewHolder h = (ResourceViewHolder) holder;

        Resource item = adapter.getItem(position);
        h.title.setText(item.title);
        h.url = item.url;
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<Resource> adapter, int position) {
        return true;
    }

    @Override
    public Class getItemClass() {
        return Resource.class;
    }
}
