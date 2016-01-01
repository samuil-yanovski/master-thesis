package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.ui.adapters.ThesesAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ThesesFragment extends BaseListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ThesesAdapter adapter = new ThesesAdapter();
        adapter.setCategories(LocalDataProvider.getAllCategories());
        getList().setAdapter(adapter);
    }

    @Override
    protected void addDecorations(RecyclerView list) {
    }
}
