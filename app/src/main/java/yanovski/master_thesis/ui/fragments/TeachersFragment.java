package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.view.View;

import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.ui.adapters.TeachersAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class TeachersFragment extends BaseListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TeachersAdapter adapter = new TeachersAdapter();
        adapter.setItems(LocalDataProvider.getAllTeachers());
        getList().setAdapter(adapter);
    }
}
