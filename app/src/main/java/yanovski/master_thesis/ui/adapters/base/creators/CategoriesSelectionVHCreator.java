package yanovski.master_thesis.ui.adapters.base.creators;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseViewHolder;

/**
 * Created by Samuil on 12/30/2015.
 */
public class CategoriesSelectionVHCreator implements ViewHolderCreator<Category> {
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
    public BaseViewHolder onCreateViewHolder(BaseRecyclerViewAdapter<Category> adapter,
        ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_category_selection, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewAdapter<Category> adapter,
        BaseViewHolder holder, int position) {
        CategoryViewHolder h = (CategoryViewHolder) holder;

        Category item = adapter.getItem(position);
        h.title.setText(item.name);
    }

    @Override
    public boolean canHandle(BaseRecyclerViewAdapter<Category> adapter, int position) {
        return true;
    }

    @Override
    public Class getItemClass() {
        return Category.class;
    }
}
