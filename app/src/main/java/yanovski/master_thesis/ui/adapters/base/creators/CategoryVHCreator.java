package yanovski.master_thesis.ui.adapters.base.creators;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import yanovski.master_thesis.R;
import yanovski.master_thesis.ui.adapters.ThesesAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;

/**
 * Created by Samuil on 12/30/2015.
 */
public class CategoryVHCreator implements ViewHolderCreator<ThesesAdapter.Item> {
    private static final int VIEW_TYPE_ID = CreatorManager.ID_GENERATOR.getAndIncrement();

    public static class CategoryViewHolder extends BaseViewHolder {
        @Bind(R.id.title)
        public TextView title;

        public CategoryViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemViewType() {
        return VIEW_TYPE_ID;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<ThesesAdapter.Item> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<ThesesAdapter.Item> adapter,
        BaseViewHolder holder, int position) {
        CategoryViewHolder h = (CategoryViewHolder) holder;

        ThesesAdapter.Item item = adapter.getItem(position);
        h.title.setText(item.getCategory().name);
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<ThesesAdapter.Item> adapter, int position) {
        return ThesesAdapter.ItemType.Category.equals(adapter.getItem(position)
            .getType());
    }

    @Override
    public Class getItemClass() {
        return ThesesAdapter.Item.class;
    }
}
