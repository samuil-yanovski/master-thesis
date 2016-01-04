package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.adapters.ThesesAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class ThesesFragment extends BaseListFragment {

    Teacher teacher;

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Category> categories = LocalDataProvider.getAllCategories(teacher);
        ThesesAdapter adapter = new ThesesAdapter();
        adapter.setCategories(categories);
        getList().setAdapter(adapter);
    }

    @Override
    protected void addDecorations(RecyclerView list) {
    }
}
