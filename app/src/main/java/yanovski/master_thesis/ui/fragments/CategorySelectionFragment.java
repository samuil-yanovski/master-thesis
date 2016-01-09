package yanovski.master_thesis.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import yanovski.master_thesis.Constants;
import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.ui.adapters.CategoriesSelectionAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class CategorySelectionFragment extends BaseListFragment implements
    BaseRecyclerViewAdapter.OnItemClickListener {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CategoriesSelectionAdapter adapter = new CategoriesSelectionAdapter();
        adapter.setItems(LocalDataProvider.getAllCategories());
        adapter.setOnItemClickListener(this);
        getList().setAdapter(adapter);
    }

    @Override
    public void onItemClick(BaseRecyclerViewAdapter<?> adapter, RecyclerView.ViewHolder holder,
        int position) {
        Category category = (Category) adapter.getItem(position);
        switch (getMode()) {
            case Select: {
                returnCategory(category);
                break;
            }
        }
    }

    private void returnCategory(Category category) {
        FragmentActivity activity = getActivity();
        Intent data = new Intent();
        data.putExtra(Constants.KEY_ITEM, category);
        activity.setResult(Activity.RESULT_OK, data);
        activity.finish();
    }
}
