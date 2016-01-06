package yanovski.master_thesis.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import yanovski.master_thesis.data.LocalDataProvider;
import yanovski.master_thesis.data.models.Student;
import yanovski.master_thesis.ui.adapters.StudentsAdapter;
import yanovski.master_thesis.ui.adapters.base.BaseRecyclerViewAdapter;
import yanovski.master_thesis.ui.adapters.base.creators.StudentVHCreator;
import yanovski.master_thesis.ui.base.BaseListFragment;

/**
 * Created by Samuil on 12/30/2015.
 */
public class StudentsFragment extends BaseListFragment implements
    BaseRecyclerViewAdapter.OnItemClickListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StudentsAdapter adapter = new StudentsAdapter();
        adapter.setItems(LocalDataProvider.getAllStudents());
        adapter.setOnItemClickListener(this);
        getList().setAdapter(adapter);
    }

    @Override
    public void onItemClick(BaseRecyclerViewAdapter<?> adapter, RecyclerView.ViewHolder holder,
        int position) {
        Student student = (Student) adapter.getItem(position);
        switch (getMode()) {
            case View: {
                showStudent((StudentVHCreator.StudentViewHolder) holder, student);
                break;
            }
            case Select: {
                returnStudent(student);
                break;
            }
        }

    }

    private void returnStudent(Student student) {
    }

    private void showStudent(StudentVHCreator.StudentViewHolder holder, Student student) {
    }
}
