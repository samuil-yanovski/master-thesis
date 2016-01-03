package yanovski.master_thesis.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.ui.TeacherProfileActivity;
import yanovski.master_thesis.ui.adapters.TeachersAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class TeachersFragment extends BaseListFragment implements
    BaseRecyclerViewAdapter.OnItemClickListener {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TeachersAdapter adapter = new TeachersAdapter();
        adapter.setItems(LocalDataProvider.getAllTeachers());
        adapter.setOnItemClickListener(this);
        getList().setAdapter(adapter);
    }

    @Override
    public void onItemClick(BaseRecyclerViewAdapter<?> adapter, RecyclerView.ViewHolder holder,
        int position) {
        Teacher teacher = (Teacher) adapter.getItem(position);
        Intent intent = new Intent(getActivity(), TeacherProfileActivity.class);
        intent.putExtra(TeacherProfileActivity.KEY_TEACHER, teacher);
        startActivity(intent);
    }
}
